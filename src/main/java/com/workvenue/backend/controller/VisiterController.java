package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.RestMessage;
import com.workvenue.backend.core.exception.custom.BusinessControllerException;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.data.dto.other.RestHeader;
import com.workvenue.backend.data.dto.request.RegisterVisiterControllerRequest;
import com.workvenue.backend.data.dto.response.RegisterVisiterControllerResponse;
import com.workvenue.backend.service.VisiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visiters")
public class VisiterController {

    private final VisiterService visiterService;

    @Autowired
    public VisiterController(VisiterService visiterService) {
        this.visiterService = visiterService;
    }

    @PostMapping()
    public ResponseEntity<RegisterVisiterControllerResponse> registerVisiter(@RequestBody RegisterVisiterControllerRequest registerVisiterControllerRequest) throws BusinessControllerException {
        RegisterVisiterControllerResponse registerVisiterControllerResponse;
        try {
            RegisterVisiterControllerResponse response=visiterService.registerVisiter(registerVisiterControllerRequest);
            registerVisiterControllerResponse=response;
            registerVisiterControllerResponse.setHeader(new RestHeader(true, MessageUtil.getMessage("Visiter", RestMessage.CREATED), null));
        } catch (Exception ex) {
            throw new BusinessControllerException("mert");
        }
        return new ResponseEntity(registerVisiterControllerResponse, HttpStatus.CREATED);
    }

    /*
    //TODO LİST
    - Response içinde ne dönmeliyiz?
    - Servisten ne dönmeliyiz?
    - Response Entity ne dönüyor?
    - Custom Exception içinde ne olmalı?
    */
}
