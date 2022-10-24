package com.workvenue.backend.service;


import com.workvenue.backend.data.dto.GetVisiterDTO;
import com.workvenue.backend.data.entity.User;
import com.workvenue.backend.data.entity.Visiter;
import com.workvenue.backend.data.request.RegisterVisiterControllerRequest;
import com.workvenue.backend.data.response.GetAllVisiterControllerResponse;
import com.workvenue.backend.data.response.RegisterVisiterControllerResponse;
import com.workvenue.backend.repository.UserRepository;
import com.workvenue.backend.repository.VisiterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//            visiter.setCreatedDate(LocalDateTime.now());
            visiter.setDescription(request.getDescription());
            visiter.setLink(request.getLink());
            visiter.setActive(true);
            visiterRepository.save(visiter);

            registerVisiterControllerResponse.setFirstName(visiter.getFirstName());
            registerVisiterControllerResponse.setLastName(visiter.getLastName());
        } else {
            throw new Exception("Kullanıcı maili sistemde kayıtlı.");
        }
        return registerVisiterControllerResponse;
    }

    public GetAllVisiterControllerResponse getAllVisiter() throws Exception {
        GetAllVisiterControllerResponse getAllVisiterControllerResponse=new GetAllVisiterControllerResponse();

        try {
            List<Visiter> visiterList;
            List<GetVisiterDTO> getVisiterDTOList=new ArrayList<>();

            visiterList = visiterRepository.findAll();

            Iterator<Visiter> visiter = visiterList.iterator();
            GetVisiterDTO getVisiterDTO=new GetVisiterDTO();

            while (visiter.hasNext()) {
                BeanUtils.copyProperties(visiter.next(), getVisiterDTO);
                getVisiterDTOList.add(getVisiterDTO);
            }
            getAllVisiterControllerResponse.setGetVisiterDTOList(getVisiterDTOList);
        } catch (Exception ex) {
            throw new Exception("Liste getirilirken hata oluştu.");
        }
        return getAllVisiterControllerResponse;
    }
}
//TODO LİST
/* Controllera hatayı aktarması lazım hata mesajını, gerekli hataların fırlatılması lazım
* çok fazla aynı nesne varsa bean oluşturulmalı: çalış
* gerekli yerlere equalsandhascode koy
* gerekli yerlere toString import et
* unit test
*
* */