package com.workvenue.backend.service;

import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.data.dto.VisiterDTO;
import com.workvenue.backend.data.entity.User;
import com.workvenue.backend.data.entity.Visiter;
import com.workvenue.backend.data.request.RegisterVisiterControllerRequest;
import com.workvenue.backend.data.response.GetAllVisiterControllerResponse;
import com.workvenue.backend.data.response.RegisterVisiterControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.repository.UserRepository;
import com.workvenue.backend.repository.VisiterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisiterService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final VisiterRepository visiterRepository;

    @Autowired
    public VisiterService(ModelMapper modelMapper, UserRepository userRepository, VisiterRepository visiterRepository) {
        this.modelMapper = modelMapper;
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

    public GetAllVisiterControllerResponse getAllVisiters() throws Exception {
        try {
            GetAllVisiterControllerResponse getAllVisiterControllerResponse = new GetAllVisiterControllerResponse();
            List<Visiter> allVisiters = visiterRepository.findAll();
            if (allVisiters.isEmpty() || allVisiters == null)
                throw new ControllerException(ErrorMessage.GET_USER_NULL_ERROR);

            List<VisiterDTO> visiterDTOList = allVisiters
                    .stream()
                    .map(visiter -> modelMapper.map(visiter, VisiterDTO.class))
                    .collect(Collectors.toList());

            getAllVisiterControllerResponse.setGetVisiterDTOList(visiterDTOList);
            return getAllVisiterControllerResponse;
        } catch (Exception ex) {
            throw new ControllerException("Liste getirilirken hata oluştu. " + ex.getMessage());
        }
    }

}
//TODO LİST
/* Controllera hatayı aktarması lazım hata mesajını, gerekli hataların fırlatılması lazım
 * çok fazla aynı nesne varsa bean oluşturulmalı: çalış
 * gerekli yerlere equals and hascode koy
 * gerekli yerlere toString import et
 *
 * */