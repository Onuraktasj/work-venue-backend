package com.workvenue.backend.service;

import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.model.Venue;
import com.workvenue.backend.data.request.venue.CreateVenueControllerRequest;
import com.workvenue.backend.data.request.venue.UpdateVenueControllerRequest;
import com.workvenue.backend.data.response.venue.CreateVenueControllerResponse;
import com.workvenue.backend.data.response.venue.GetAllVenueControllerResponse;
import com.workvenue.backend.data.response.venue.UpdateVenueControllerResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VenueService {

    CreateVenueControllerResponse createVenue(CreateVenueControllerRequest request) throws ControllerException;

    UpdateVenueControllerResponse updateVenue(UpdateVenueControllerRequest request) throws ControllerException;

    GetAllVenueControllerResponse findAllByIsActive() throws ControllerException;

    Optional<Venue> getVenueByName(String name) throws ControllerException;

    void saveVenue(Venue venue) throws ControllerException;
}
