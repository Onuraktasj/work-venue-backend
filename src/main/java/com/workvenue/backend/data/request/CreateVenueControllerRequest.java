package com.workvenue.backend.data.request;

import com.workvenue.backend.data.dto.VenueDTO;

public class CreateVenueControllerRequest {

    private VenueDTO venueDTO;

    public CreateVenueControllerRequest() {
    }

    public CreateVenueControllerRequest(VenueDTO venueDTO) {
        this.venueDTO = venueDTO;
    }

    public VenueDTO getVenueDTO() {
        return venueDTO;
    }

    public void setVenueDTO(VenueDTO venueDTO) {
        this.venueDTO = venueDTO;
    }
}
