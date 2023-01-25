package com.workvenue.backend.data.response.venue;

import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.response.BaseControllerResponse;

public class CreateVenueControllerResponse extends BaseControllerResponse {

    private VenueDTO venueDTO;

    public CreateVenueControllerResponse(){
    }

    public CreateVenueControllerResponse(RestHeader header, VenueDTO venueDTO) {
        super(header);
        this.venueDTO = venueDTO;
    }

    public VenueDTO getVenueDTO() {
        return venueDTO;
    }

    public void setVenueDTO(VenueDTO venueDTO) {
        this.venueDTO = venueDTO;
    }
}
