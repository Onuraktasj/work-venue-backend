package com.workvenue.backend.controller;

import com.workvenue.backend.data.dto.request.CreateVisiterControllerRequest;
import com.workvenue.backend.data.entity.Visiter;
import com.workvenue.backend.service.VisiterService;
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

    public VisiterController(VisiterService visiterService) {
        this.visiterService = visiterService;
    }

    @PostMapping
    public ResponseEntity createVisiterProfile(@RequestBody CreateVisiterControllerRequest createVisiterRequest){
        return new ResponseEntity(visiterService.createVisiterProfile(createVisiterRequest), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Visiter visiter){
        return new ResponseEntity(visiterService.visiterRegister(visiter), HttpStatus.CREATED);
    }
}
