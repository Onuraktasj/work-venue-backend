package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.RestMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.request.RegisterVisiterControllerRequest;
import com.workvenue.backend.data.response.GetAllVisiterControllerResponse;
import com.workvenue.backend.data.response.RegisterVisiterControllerResponse;
import com.workvenue.backend.service.VisiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visiters")
public class VisiterController {

    private final VisiterService visiterService;

    @Autowired
    public VisiterController(VisiterService visiterService) {
        this.visiterService = visiterService;
    }

    @PostMapping()
    public ResponseEntity<RegisterVisiterControllerResponse> registerVisiter(@RequestBody RegisterVisiterControllerRequest registerVisiterControllerRequest) throws Exception {
        RegisterVisiterControllerResponse registerVisiterControllerResponse;
        try {
            RegisterVisiterControllerResponse response = visiterService.registerVisiter(registerVisiterControllerRequest);
            registerVisiterControllerResponse = response;
            registerVisiterControllerResponse.setHeader(new RestHeader(true, MessageUtil.getMessage("Visiter", RestMessage.CREATED), null));
        } catch (Exception ex) {
            throw new Exception("Register Visiter Exception: " + ex);
        }
        return new ResponseEntity(registerVisiterControllerResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<GetAllVisiterControllerResponse> getAllVisiter() throws Exception {
        GetAllVisiterControllerResponse listOfGetAllVisiterControllerResponse;
        try {
            listOfGetAllVisiterControllerResponse = visiterService.getAllVisiter();
            listOfGetAllVisiterControllerResponse.setHeader(new RestHeader(true, MessageUtil.getMessage("Visiter", RestMessage.FOUND), null));
        } catch (Exception ex) {
//            throw new Exception("Get All Visiter Exception: " + ex);
            throw new IllegalStateException();
        }
        return new ResponseEntity<>(listOfGetAllVisiterControllerResponse, HttpStatus.CREATED);
    }
}