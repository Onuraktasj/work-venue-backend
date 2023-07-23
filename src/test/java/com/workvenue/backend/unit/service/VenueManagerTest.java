package com.workvenue.backend.unit.service;

import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.request.venue.CreateVenueControllerRequest;
import com.workvenue.backend.data.response.venue.CreateVenueControllerResponse;
import com.workvenue.backend.service.impl.VenueManager;
import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.model.Venue;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("VenueManager Unit Test")
@ExtendWith(MockitoExtension.class)
class VenueManagerTest {

    @InjectMocks
    private VenueManager venueManager;
    @Mock
    private VenueRepository venueRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    void testFindAllByIsActive_givenCorrectRequest_thenReturnSuccessResponse() throws Exception {
        //given
        List<Venue> allVenues = new ArrayList<>();
        Venue venue = new Venue().setName("name");
        allVenues.add(venue);

        List<VenueDTO> venueDTOList = new ArrayList<>();
        VenueDTO venueDTO = new VenueDTO(venue.getName(), venue.getAddress(), venue.getOpeningTime(),
                                         venue.getClosingTime(), venue.getCategory(), venue.getNetwork(),
                                         venue.getStatus());
        venueDTOList.add(venueDTO);
        doReturn(allVenues).when(venueRepository).findAll();
        doReturn(venueDTO).when(modelMapper).map(any(), any());

        //when
        GetAllVenueControllerResponse response = venueManager.findAllByIsActive();

        //then
        assertEquals(venueDTOList.get(0), response.getVenueDTOList().get(0));
    }

    @Test
    void testCreateVenue_givenCorrectRequest_thenReturnSuccessResponse() throws Exception {
        //given
        Venue venue = new Venue().setName("name");
        VenueDTO venueDTO = new VenueDTO(venue.getName(), venue.getAddress(), venue.getOpeningTime(),
                                         venue.getClosingTime(), venue.getCategory(), venue.getNetwork(),
                                         venue.getStatus());
        CreateVenueControllerRequest request = new CreateVenueControllerRequest().setVenueDTO(venueDTO);
        doReturn(venueDTO).when(modelMapper).map(any(), any());
        doReturn(Optional.empty()).when(venueRepository).findVenueByName(venueDTO.getName());

        //when
        CreateVenueControllerResponse response = venueManager.createVenue(request);

        //then
        assertEquals(venueDTO, response.getVenueDTO());
    }

    @Test
    void testCreateVenue_givenCorrectRequest_thenThrowAnException() {
        //given
        Venue venue = new Venue().setName("name");
        VenueDTO venueDTO = new VenueDTO(venue.getName(), venue.getAddress(), venue.getOpeningTime(),
                                         venue.getClosingTime(), venue.getCategory(), venue.getNetwork(),
                                         venue.getStatus());
        CreateVenueControllerRequest request = new CreateVenueControllerRequest().setVenueDTO(venueDTO);
        doReturn(Optional.of(venue)).when(venueRepository).findVenueByName(venueDTO.getName());

        //when
        Exception exception = assertThrows(ControllerException.class, () -> venueManager.createVenue(request));

        //then
        assertNotNull(exception);
    }
}