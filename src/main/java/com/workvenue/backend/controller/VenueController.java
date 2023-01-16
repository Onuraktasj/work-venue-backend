package com.workvenue.backend.controller;

import com.sun.net.httpserver.Authenticator;
import com.workvenue.backend.core.constant.ControllerName;
import com.workvenue.backend.core.constant.SuccessMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.request.CreateVenueControllerRequest;
import com.workvenue.backend.data.request.UpdateVenueControllerRequest;
import com.workvenue.backend.data.response.CreateVenueControllerResponse;
import com.workvenue.backend.data.response.UpdateVenueControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.service.VenueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
public class VenueController {
    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping()
    @ApiOperation(value = "Create New Venue For Visitor")
    public ResponseEntity<CreateVenueControllerResponse> createVenue(@RequestBody CreateVenueControllerRequest createVenueControllerRequest) throws Exception {
        try {
            CreateVenueControllerResponse response=venueService.createVenue(createVenueControllerRequest);
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
            UpdateVenueControllerResponse response=venueService.updateVenue(updateVenueControllerRequest);
            response.setHeader(new RestHeader(true,  MessageUtil.getMessage("Venue", SuccessMessage.CREATED), null));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ControllerException(ex, ControllerName.UPDATE_VENUE);
        }
    }
}
