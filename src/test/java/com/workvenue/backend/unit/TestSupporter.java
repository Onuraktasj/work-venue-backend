package com.workvenue.backend.unit;

import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.entity.Venue;
import com.workvenue.backend.data.enums.Category;
import com.workvenue.backend.data.enums.Network;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestSupporter {


    public static List<Venue> generateVenues(){
        OffsetDateTime offsetDT2 = OffsetDateTime.now(Clock.systemUTC());
        Venue venue = new Venue("XRestorant","Ankara",offsetDT2,offsetDT2, Category.COFFEE, Network.PRIVATE);
        Venue venue2 = new Venue("YRestorant","İstanbul",offsetDT2,offsetDT2, Category.LIBRARY, Network.PRIVATE);
        Venue venue3 = new Venue("ZRestorant","İzmir",offsetDT2,offsetDT2, Category.RESTAURANT, Network.PRIVATE);

        List<Venue> venues = new ArrayList<>();
        venues.add(venue);
        venues.add(venue2);
        venues.add(venue3);
        return venues;
    }

    public static List<VenueDTO> generateVenueDto(List<Venue> venues){
    return venues.stream()
            .map(from-> new VenueDTO(from.getName(),from.getAddress(),
                    from.getOpeningTime(),
                    from.getClosingTime(),from.getCategory(),from.getNetwork(),
                    from.getStatus())).collect(Collectors.toList());
    }
}
