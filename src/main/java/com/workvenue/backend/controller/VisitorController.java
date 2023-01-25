package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.ControllerName;
import com.workvenue.backend.core.constant.SuccessMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.request.visitor.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.request.visitor.UpdateVisitorControllerRequest;
import com.workvenue.backend.data.response.visitor.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.UpdateVisitorControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.business.concretes.VisitorManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorManager visitorManager;

    @Autowired
    public VisitorController(VisitorManager visitorManager) {
        this.visitorManager = visitorManager;
    }

    @PostMapping()
    @ApiOperation(value = "Register New Visitor Account For User.")
    public ResponseEntity<RegisterVisitorControllerResponse> registerVisitor(@RequestBody RegisterVisitorControllerRequest registerVisitorControllerRequest) throws Exception {

        try {
            RegisterVisitorControllerResponse response = visitorManager.registerVisitor(registerVisitorControllerRequest);
            response.setHeader(new RestHeader(true, MessageUtil.getMessage("Kullanıcı", SuccessMessage.CREATED), null));
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.REGISTER_VISITOR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Get All Visitors For Admin", notes = "Must adding authorization for access just admin.")
    public ResponseEntity<GetAllVisitorControllerResponse> getAllVisitors() throws Exception {
        try {
            GetAllVisitorControllerResponse setOfGetAllVisitorControllerResponse;
            setOfGetAllVisitorControllerResponse = visitorManager.getAllVisitors();
            setOfGetAllVisitorControllerResponse.setHeader(new RestHeader(true, MessageUtil.getMessage("Kullanıcı", SuccessMessage.FOUND), null));
            return new ResponseEntity<>(setOfGetAllVisitorControllerResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.GET_ALL_VISITOR);
        }
    }

    @PutMapping()
    @ApiOperation(value = "Update Spesific Visitor For Admin and User.")
    public ResponseEntity<UpdateVisitorControllerResponse> updateVisitor(@RequestBody UpdateVisitorControllerRequest updateVisitorControllerRequest) throws Exception {
        try {
            UpdateVisitorControllerResponse updateVisitorControllerResponse;
            updateVisitorControllerResponse = visitorManager.updateVisitor(updateVisitorControllerRequest);
            updateVisitorControllerResponse.setHeader(new RestHeader(true, MessageUtil.getMessage("Kullanıcı", SuccessMessage.UPDATED), null));
            return new ResponseEntity<>(updateVisitorControllerResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.GET_ALL_VISITOR);
        }
    }

}