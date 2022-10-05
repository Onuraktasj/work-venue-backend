package com.workvenue.backend.service;


import com.workvenue.backend.core.exception.custom.BusinessControllerException;
import com.workvenue.backend.data.dto.request.RegisterVisiterControllerRequest;
import com.workvenue.backend.data.dto.response.RegisterVisiterControllerResponse;
import com.workvenue.backend.data.entity.User;
import com.workvenue.backend.data.entity.Visiter;
import com.workvenue.backend.repository.UserRepository;
import com.workvenue.backend.repository.VisiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisiterService {

    private final UserRepository userRepository;
    private final VisiterRepository visiterRepository;

    @Autowired
    public VisiterService(UserRepository userRepository,VisiterRepository visiterRepository) {
        this.userRepository = userRepository;
        this.visiterRepository=visiterRepository;
    }

    public RegisterVisiterControllerResponse registerVisiter(RegisterVisiterControllerRequest request) throws BusinessControllerException {
        Optional<User> userOptional = userRepository.getUserByEmail(request.getEmail());
        RegisterVisiterControllerResponse registerVisiterControllerResponse=new RegisterVisiterControllerResponse();

        if (!userOptional.isPresent()) {
            Visiter visiter = new Visiter();
            visiter.setEmail(request.getEmail());
            visiter.setPassword(request.getPassword()); //TODO: parola çevirimi ve kontrolü yapılcak.
            visiter.setFirstName(request.getFirstName());
            visiter.setLastName(request.getLastName());
            visiter.setCreatedDate(LocalDateTime.now());
            visiter.setDescription(request.getDescription());
            visiter.setLink(request.getLink());
            visiter.setIsActive(true);
            visiterRepository.save(visiter);

            registerVisiterControllerResponse.setFirstName(visiter.getFirstName());
            registerVisiterControllerResponse.setLastName(visiter.getLastName());
        } else {
            //TODO: Burada kullanıcı maili kayıtlı hatası fırlatacak!
            throw new BusinessControllerException("mert");
        }
        return registerVisiterControllerResponse;
    }
}