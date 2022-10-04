package com.workvenue.backend.service;


import com.workvenue.backend.data.dto.request.CreateVisiterControllerRequest;
import com.workvenue.backend.data.dto.response.CreateVisiterControllerResponse;
import com.workvenue.backend.data.entity.User;
import com.workvenue.backend.data.entity.Visiter;
import com.workvenue.backend.repository.VisiterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisiterService {

    private final VisiterRepository visiterRepository;
    private final UserService userService;

    public VisiterService(VisiterRepository visiterRepository, UserService userService) {
        this.visiterRepository = visiterRepository;
        this.userService = userService;
    }

    public CreateVisiterControllerResponse createVisiterProfile(CreateVisiterControllerRequest createVisiterRequest){
        CreateVisiterControllerResponse visiterCreateResponse=new CreateVisiterControllerResponse();
        Optional<User> user = userService.findByUserId(createVisiterRequest.getId());
            if(user.isPresent()){
                Visiter visiter = new Visiter(
                        user.get().getId(),
                        createVisiterRequest.getDescription(),
                        createVisiterRequest.getLink(),
                        true,
                        createVisiterRequest.getImage()
                );
                try{
                   Visiter visiterRes= visiterRepository.save(visiter);
                   BeanUtils.copyProperties(visiterRes,visiterCreateResponse);
                }
                catch (Exception ex){
                    System.out.println(ex.getMessage());
                }

                return visiterCreateResponse;

                        //new ResponseEntity(visiterRepository.save(visiter), HttpStatus.CREATED);
            }
        return new CreateVisiterControllerResponse();
                //new ResponseEntity(HttpStatus.NOT_FOUND);
    }
      public Visiter visiterRegister(Visiter visiter){
        return visiterRepository.save(visiter);
      }
}
