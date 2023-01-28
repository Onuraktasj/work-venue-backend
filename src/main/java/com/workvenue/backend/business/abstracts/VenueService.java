package com.workvenue.backend.business.abstracts;

import com.workvenue.backend.data.request.venue.CreateVenueControllerRequest;
import com.workvenue.backend.data.request.venue.UpdateVenueControllerRequest;
import com.workvenue.backend.data.response.venue.CreateVenueControllerResponse;
import com.workvenue.backend.data.response.venue.GetAllVenueControllerResponse;
import com.workvenue.backend.data.response.venue.UpdateVenueControllerResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface VenueService {

    CreateVenueControllerResponse createVenue(CreateVenueControllerRequest request) throws Exception;

    UpdateVenueControllerResponse updateVenue(UpdateVenueControllerRequest request) throws Exception;

    GetAllVenueControllerResponse getAllVenues() throws Exception;
}
