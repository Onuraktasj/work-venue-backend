package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.core.constant.SuccessMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.request.RegisterVisiterControllerRequest;
import com.workvenue.backend.data.response.GetAllVisiterControllerResponse;
import com.workvenue.backend.data.response.RegisterVisiterControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.service.VisiterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visiters")
public class VisiterController {

    private final VisiterService visiterService;

    @Autowired
    public VisiterController(VisiterService visiterService) {
        this.visiterService = visiterService;
    }

    @PostMapping()
    @ApiOperation(value = "Register New Visiter Account For User")
    public ResponseEntity<RegisterVisiterControllerResponse> registerVisiter(@RequestBody RegisterVisiterControllerRequest registerVisiterControllerRequest) throws Exception {

        try {
            RegisterVisiterControllerResponse response = visiterService.registerVisiter(registerVisiterControllerRequest);
            response.setHeader(new RestHeader(true, MessageUtil.getMessage("Visiter", SuccessMessage.CREATED), null));
            return new ResponseEntity(response, HttpStatus.CREATED);

        } catch (Exception ex) {
            throw new Exception("Register Visiter Exception: " + ex);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Get All Visiters For Admin", notes = "Must adding authorization for access just admin.")
    public ResponseEntity<GetAllVisiterControllerResponse> getAllVisiters() throws Exception {
        try {
            GetAllVisiterControllerResponse listOfGetAllVisiterControllerResponse;
            listOfGetAllVisiterControllerResponse = visiterService.getAllVisiters();
            listOfGetAllVisiterControllerResponse.setHeader(new RestHeader(true, MessageUtil.getMessage("Visiter", SuccessMessage.FOUND), null));
            return new ResponseEntity<>(listOfGetAllVisiterControllerResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ControllerException(ex.getMessage());
        }
    }
}