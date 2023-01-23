package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.ControllerName;
import com.workvenue.backend.core.constant.SuccessMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.request.venue.CreateVenueControllerRequest;
import com.workvenue.backend.data.request.venue.UpdateVenueControllerRequest;
import com.workvenue.backend.data.response.venue.CreateVenueControllerResponse;
import com.workvenue.backend.data.response.venue.GetAllVenueControllerResponse;
import com.workvenue.backend.data.response.venue.UpdateVenueControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.business.concretes.VenueManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
public class VenueController {
    private final VenueManager venueManager;

    @Autowired
    public VenueController(VenueManager venueManager) {
        this.venueManager = venueManager;
    }

    @PostMapping()
    @ApiOperation(value = "Create New Venue For Visitor")
    public ResponseEntity<CreateVenueControllerResponse> createVenue(@RequestBody CreateVenueControllerRequest createVenueControllerRequest) throws Exception {
        try {
            CreateVenueControllerResponse response= venueManager.createVenue(createVenueControllerRequest);
            response.setHeader(new RestHeader(true,  MessageUtil.getMessage("Venue", SuccessMessage.CREATED), null));
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.CREATE_VENUE);
        }
    }
    @PutMapping()
    @ApiOperation(value = "Update Venue By Name")
    public ResponseEntity<CreateVenueControllerResponse> updateVenue(@RequestBody UpdateVenueControllerRequest updateVenueControllerRequest) throws Exception {
        try {
            UpdateVenueControllerResponse response= venueManager.updateVenue(updateVenueControllerRequest);
            response.setHeader(new RestHeader(true,  MessageUtil.getMessage("Venue", SuccessMessage.CREATED), null));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.UPDATE_VENUE);
        }
    }
    @GetMapping()
    @ApiOperation(value ="Get All Venues")
    public ResponseEntity<GetAllVenueControllerResponse> getAllVenues() throws Exception {
        try {
            GetAllVenueControllerResponse response = venueManager.getAllVenues();
            response.setHeader(new RestHeader(true,MessageUtil.getMessage("Venue",SuccessMessage.FOUND),null));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception exception){
            throw  new ControllerException(exception,ControllerName.GET_ALL_VENUES);
        }
    }
}
