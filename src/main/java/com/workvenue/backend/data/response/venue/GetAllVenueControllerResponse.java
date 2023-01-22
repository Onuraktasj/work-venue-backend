package com.workvenue.backend.data.response.venue;

import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.response.BaseControllerResponse;

import java.util.Set;

public class GetAllVenueControllerResponse extends BaseControllerResponse {

    private Set<VenueDTO> venueDTOSet;

    public GetAllVenueControllerResponse() {
    }
    public GetAllVenueControllerResponse(RestHeader header) {
        super(header);
    }

    public GetAllVenueControllerResponse(RestHeader header, Set<VenueDTO> venueDTOSet) {
        super(header);
        this.venueDTOSet = venueDTOSet;
    }

    public Set<VenueDTO> getVenueDTOSet() {
        return venueDTOSet;
    }

    public void setVenueDTOSet(Set<VenueDTO> venueDTOSet) {
        this.venueDTOSet = venueDTOSet;
    }
}
