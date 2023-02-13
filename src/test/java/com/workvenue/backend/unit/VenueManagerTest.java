package com.workvenue.backend.unit;

import com.workvenue.backend.business.concretes.VenueManager;
import com.workvenue.backend.data.entity.Venue;
import com.workvenue.backend.data.enums.Category;
import com.workvenue.backend.data.enums.Network;
import com.workvenue.backend.data.response.venue.GetAllVenueControllerResponse;
import com.workvenue.backend.repository.VenueRepository;
import com.workvenue.backend.repository.VisitorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@DisplayName("Unit Test Methods for VenueManager Class")
@ExtendWith(MockitoExtension.class)
class VenueManagerTest {
    @InjectMocks
    private VenueManager venueManager;
    @Mock
    private VenueRepository venueRepository;
    @Mock
    private VisitorRepository visitorRepository;
    @Mock
    private ModelMapper modelMapper;


    @Test
    public void testGetAllVenues_givenCorrectRequest_thenReturnSuccessResponse() throws Exception {

        // Given
        Set<Venue> allVenues = new HashSet<>();
        OffsetDateTime offsetDT2 = OffsetDateTime.now();
        Venue venue = new Venue("XRestorant","Ankara",offsetDT2,offsetDT2, Category.COFFEE, Network.PRIVATE);
        Venue venue2 = new Venue("YRestorant","İstanbul",offsetDT2,offsetDT2, Category.LIBRARY, Network.PRIVATE);
        Venue venue3 = new Venue("ZRestorant","İzmir",offsetDT2,offsetDT2, Category.RESTAURANT, Network.PRIVATE);
        Venue venue4 = new Venue("RRestorant","İzmir",offsetDT2,offsetDT2, Category.RESTAURANT, Network.PRIVATE);

        allVenues.add(venue);
        allVenues.add(venue2);
        allVenues.add(venue3);
        allVenues.add(venue4);

        // When
        Mockito.when(venueRepository.getAllVenues()).thenReturn(allVenues);
        GetAllVenueControllerResponse object = venueManager.getAllVenues();

        // Then

    }
}