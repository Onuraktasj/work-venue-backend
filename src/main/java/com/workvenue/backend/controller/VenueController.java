package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.SuccessMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.core.util.RestHeader;
import com.workvenue.backend.core.util.exception.custom.ControllerException;
import com.workvenue.backend.data.request.venue.CreateVenueControllerRequest;
import com.workvenue.backend.data.request.venue.UpdateVenueControllerRequest;
import com.workvenue.backend.data.response.venue.CreateVenueControllerResponse;
import com.workvenue.backend.data.response.venue.GetAllVenueControllerResponse;
import com.workvenue.backend.data.response.venue.UpdateVenueControllerResponse;
import com.workvenue.backend.service.impl.VenueManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("DENEME")
@RestController
@RequiredArgsConstructor
@RequestMapping("/venues")
public class VenueController {
    private final VenueManager venueManager;

    @PostMapping
    @ApiOperation(value = "Create New Venue For Visitor")
    public ResponseEntity<CreateVenueControllerResponse> createVenue(
            @RequestBody CreateVenueControllerRequest createVenueControllerRequest) throws ControllerException {
        CreateVenueControllerResponse response = venueManager.createVenue(createVenueControllerRequest);
        response.setHeader(new RestHeader(true, MessageUtil.getMessage("Venue", SuccessMessage.CREATED), null));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Update Venue By Name")
    public ResponseEntity<UpdateVenueControllerResponse> updateVenue(
            @RequestBody UpdateVenueControllerRequest updateVenueControllerRequest) throws ControllerException {
        UpdateVenueControllerResponse response = venueManager.updateVenue(updateVenueControllerRequest);
        response.setHeader(new RestHeader(true, MessageUtil.getMessage("Venue", SuccessMessage.UPDATED), null));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Get All Venues")
    public ResponseEntity<GetAllVenueControllerResponse> findAllByIsActive() throws ControllerException {
        GetAllVenueControllerResponse response = venueManager.findAllByIsActive();
        response.setHeader(new RestHeader(true, MessageUtil.getMessage("Venue", SuccessMessage.FOUND), null));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
