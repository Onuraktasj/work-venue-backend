package com.workvenue.backend.data.request.venue;

import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.request.BaseControllerRequest;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class CreateVenueControllerRequest extends BaseControllerRequest {
    private VenueDTO venueDTO;
}
