package com.workvenue.backend.service.impl;

import com.workvenue.backend.data.model.Visitor;
import com.workvenue.backend.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private VisitorRepository visitorRepository; //TODO: NPE

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Visitor> optionalVisitor = visitorRepository.findByUsername(username);
        Visitor visitor = optionalVisitor.get();
        List<SimpleGrantedAuthority> authorities = visitor.getRoles().stream()
                                                          .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                                                          .collect(Collectors.toList());
        return new User(visitor.getUsername(), visitor.getPassword(), authorities); //parola encode
    }
}
