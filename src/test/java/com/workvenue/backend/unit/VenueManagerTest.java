package com.workvenue.backend.unit;

import com.workvenue.backend.service.impl.VenueManager;
import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.model.Venue;
import com.workvenue.backend.core.enums.Category;
import com.workvenue.backend.core.enums.Network;
import com.workvenue.backend.core.enums.Status;
import com.workvenue.backend.data.response.venue.GetAllVenueControllerResponse;
import com.workvenue.backend.repository.VenueRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit Test for the VenueManager Class")
@ExtendWith(MockitoExtension.class)
class VenueManagerTest {
    @InjectMocks
    private VenueManager venueManager;
    @Mock
    private VenueRepository venueRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    void testGetAllVenues_givenCorrectRequest_thenReturnSuccessResponse() throws Exception {

        // Given
        List<Venue> allVenues = new ArrayList<>();
        Venue venue = new Venue().setName("name");
        allVenues.add(venue);

        List<VenueDTO> venueDTOList = new ArrayList<>();
        VenueDTO venueDTO = new VenueDTO(venue.getName(), venue.getAddress(), venue.getOpeningTime(),
                                         venue.getClosingTime(), venue.getCategory(), venue.getNetwork(),
                                         venue.getStatus());
        venueDTOList.add(venueDTO);

        // When
        when(venueRepository.findAll()).thenReturn(allVenues);
        when(modelMapper.map(any(), any())).thenReturn(venueDTO);
        GetAllVenueControllerResponse response = venueManager.findAllByIsActive();

        // Then
        assertEquals(response.getVenueDTOList().get(0), venueDTOList.get(0));
    }
    //passive testi için pasif mock ver null bekle
    //allvenues ve herhangi bir mock verme assertThrows() içinde hata doğru mu fırlatılıyor kontrol et.

}