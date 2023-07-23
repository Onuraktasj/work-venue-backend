package com.workvenue.backend.service.impl;

import com.workvenue.backend.data.model.Visitor;
import com.workvenue.backend.repository.VisitorRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final VisitorRepository visitorRepository;

    public UserService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Visitor> existVisitor = visitorRepository.findByUsername(username);
        if (existVisitor.isEmpty()) {
            return null;
        }
        Visitor visitor = existVisitor.get();
        List<SimpleGrantedAuthority> authorities = visitor.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
        return new User(visitor.getUsername(), visitor.getPassword(), authorities);
        //TODO: parola encode
    }
}
