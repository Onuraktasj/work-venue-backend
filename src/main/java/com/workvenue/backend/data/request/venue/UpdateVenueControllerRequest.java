package com.workvenue.backend.data.request.venue;

import com.workvenue.backend.data.dto.VenueDTO;

public class UpdateVenueControllerRequest {

    private VenueDTO venueDTO;

    public UpdateVenueControllerRequest(VenueDTO venueDTO) {
        this.venueDTO = venueDTO;
    }

    public UpdateVenueControllerRequest() {

    }

    public VenueDTO getVenueDTO() {
        return venueDTO;
    }

    public void setVenueDTO(VenueDTO venueDTO) {
        this.venueDTO = venueDTO;
    }
}
