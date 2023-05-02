package com.workvenue.backend.controller;

import com.workvenue.backend.core.enums.ControllerName;
import com.workvenue.backend.core.constant.SuccessMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.core.util.RestHeader;
import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.request.visitor.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.request.visitor.UpdateVisitorControllerRequest;
import com.workvenue.backend.data.response.visitor.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.UpdateVisitorControllerResponse;
import com.workvenue.backend.service.impl.VisitorServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Visitor")
@RestController
@RequestMapping("/visitors")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorServiceImpl visitorServiceImpl;

    @PostMapping
    @ApiOperation(value = "Register New Visitor Account For BaseUser.")
    public ResponseEntity<RegisterVisitorControllerResponse> register(
            @RequestBody RegisterVisitorControllerRequest registerVisitorControllerRequest) throws Exception {
        RegisterVisitorControllerResponse response = visitorServiceImpl.register(registerVisitorControllerRequest);
        response.setHeader(new RestHeader(true, MessageUtil.getMessage("Kullanıcı", SuccessMessage.CREATED), null));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") //TODO: constant and @customAnnotation - usera role tanımlarsak
    // otomatik managerdaki metod buraya rolü getiriyor. - class seviyesinde de olabilir
    @GetMapping()
    @ApiOperation(value = "Get All Visitors - Admin", notes = "Must adding " + "authorization for access just admin.")
    public ResponseEntity<GetAllVisitorControllerResponse> findAll() throws Exception {
        try {
            GetAllVisitorControllerResponse setOfGetAllVisitorControllerResponse;
            setOfGetAllVisitorControllerResponse = visitorServiceImpl.findAll();
            setOfGetAllVisitorControllerResponse.setHeader(
                    new RestHeader(true, MessageUtil.getMessage("Kullanıcı", SuccessMessage.FOUND), null));
            return new ResponseEntity<>(setOfGetAllVisitorControllerResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.GET_ALL_VISITOR);
        }
    }

    @PutMapping
    @ApiOperation(value = "Update Spesific Visitor")
    public ResponseEntity<UpdateVisitorControllerResponse> update(
            @RequestBody UpdateVisitorControllerRequest updateVisitorControllerRequest) throws Exception {
        try {
            UpdateVisitorControllerResponse updateVisitorControllerResponse;
            updateVisitorControllerResponse = visitorServiceImpl.update(updateVisitorControllerRequest);
            updateVisitorControllerResponse.setHeader(
                    new RestHeader(true, MessageUtil.getMessage("Kullanıcı", SuccessMessage.UPDATED), null));
            return new ResponseEntity<>(updateVisitorControllerResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.GET_ALL_VISITOR);
        }
    }

}