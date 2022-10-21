package com.workvenue.backend.service;


import com.workvenue.backend.data.dto.response.GetAllVisiterControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.data.dto.request.RegisterVisiterControllerRequest;
import com.workvenue.backend.data.dto.response.RegisterVisiterControllerResponse;
import com.workvenue.backend.data.entity.User;
import com.workvenue.backend.data.entity.Visiter;
import com.workvenue.backend.repository.UserRepository;
import com.workvenue.backend.repository.VisiterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class VisiterService {

    private final UserRepository userRepository;
    private final VisiterRepository visiterRepository;

    @Autowired
    public VisiterService(UserRepository userRepository, VisiterRepository visiterRepository) {
        this.userRepository = userRepository;
        this.visiterRepository = visiterRepository;
    }

    public RegisterVisiterControllerResponse registerVisiter(RegisterVisiterControllerRequest request) throws Exception {
        Optional<User> userOptional = userRepository.getUserByEmail(request.getEmail());
        RegisterVisiterControllerResponse registerVisiterControllerResponse = new RegisterVisiterControllerResponse();

        if (!userOptional.isPresent()) {
            Visiter visiter = new Visiter();
            visiter.setEmail(request.getEmail());
            visiter.setPassword(request.getPassword());
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
            throw new Exception("Kullanıcı maili sistemde kayıtlı.");
        }
        return registerVisiterControllerResponse;
    }

    public List<GetAllVisiterControllerResponse> getAllVisiter() throws Exception {

        List<GetAllVisiterControllerResponse> getAllVisiterControllerResponseList = Collections.emptyList();
        List<Visiter> visiterList;
        try{
            visiterList=visiterRepository.findAll();
            Iterator<Visiter> visiter = visiterList.iterator();

            while (visiter.hasNext()){
                GetAllVisiterControllerResponse response = new GetAllVisiterControllerResponse();
                BeanUtils.copyProperties(visiter.next(), response);
                getAllVisiterControllerResponseList.add(response);
            }
        } catch (Exception ex){
            throw new Exception("Liste getirilirken hata oluştu.");
        }
        return getAllVisiterControllerResponseList;
    }
}