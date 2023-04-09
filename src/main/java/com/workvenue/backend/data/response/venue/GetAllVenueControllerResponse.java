package com.workvenue.backend.data.response.venue;

import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.exception.util.RestHeader;
import com.workvenue.backend.data.response.BaseControllerResponse;

import java.util.List;


public class GetAllVenueControllerResponse extends BaseControllerResponse {

    private List<VenueDTO> venueDTOList;

    public GetAllVenueControllerResponse() {
    }

    public GetAllVenueControllerResponse(RestHeader header) {
        super(header);
    }

    public GetAllVenueControllerResponse(RestHeader header, List<VenueDTO> venueDTOList) {
        super(header);
        this.venueDTOList = venueDTOList;
    }

    public List<VenueDTO> getVenueDTOList() {
        return venueDTOList;
    }

    public void setVenueDTOList(List<VenueDTO> venueDTOList) {
        this.venueDTOList = venueDTOList;
    }
}
