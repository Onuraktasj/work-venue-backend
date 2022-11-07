package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.ControllerName;
import com.workvenue.backend.core.constant.SuccessMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.request.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.response.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.RegisterVisitorControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.service.VisitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping()
    @ApiOperation(value = "Register New Visitor Account For User")
    public ResponseEntity<RegisterVisitorControllerResponse> registerVisitor(@RequestBody RegisterVisitorControllerRequest registerVisitorControllerRequest) throws Exception {

        try {
            RegisterVisitorControllerResponse response = visitorService.registerVisitor(registerVisitorControllerRequest);
            response.setHeader(new RestHeader(true, MessageUtil.getMessage("Visitor", SuccessMessage.CREATED), null));
            return new ResponseEntity(response, HttpStatus.CREATED);

        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.REGISTER_VISITOR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Get All Visitors For Admin", notes = "Must adding authorization for access just admin.")
    public ResponseEntity<GetAllVisitorControllerResponse> getAllVisitors() throws Exception {
        try {
            GetAllVisitorControllerResponse listOfGetAllVisitorControllerResponse;
            listOfGetAllVisitorControllerResponse = visitorService.getAllVisitors();
            listOfGetAllVisitorControllerResponse.setHeader(new RestHeader(true, MessageUtil.getMessage("Visitor", SuccessMessage.FOUND), null));
            return new ResponseEntity<>(listOfGetAllVisitorControllerResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.GET_ALL_VISITOR);
        }
    }
}