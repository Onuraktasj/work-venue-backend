package com.workvenue.backend.service;

import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.entity.Venue;
import com.workvenue.backend.data.entity.Visitor;
import com.workvenue.backend.data.enums.Status;
import com.workvenue.backend.data.request.CreateVenueControllerRequest;
import com.workvenue.backend.data.request.UpdateVenueControllerRequest;
import com.workvenue.backend.data.response.CreateVenueControllerResponse;
import com.workvenue.backend.data.response.UpdateVenueControllerResponse;
import com.workvenue.backend.exception.custom.DatabaseException;
import com.workvenue.backend.repository.VenuRepository;
import com.workvenue.backend.repository.VisitorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class VenueService {

    private final VenuRepository venuRepository;
    private final VisitorRepository visitorRepository;

    @Autowired
    public VenueService(VenuRepository venuRepository, VisitorRepository visitorRepository) {
        this.venuRepository = venuRepository;
        this.visitorRepository = visitorRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public CreateVenueControllerResponse createVenue(CreateVenueControllerRequest request) throws Exception {
        Optional<Venue> optionalVenue = venuRepository.getVenueByName(request.getVenueDTO().getName());
        CreateVenueControllerResponse createVenueControllerResponse = new CreateVenueControllerResponse();

        if (optionalVenue.isEmpty()) {
            Venue venue = new Venue();
            venue.setName(request.getVenueDTO().getName());
            venue.setAddress(request.getVenueDTO().getAddress());
            venue.setCategory(request.getVenueDTO().getCategory());
            venue.setNetwork(request.getVenueDTO().getNetwork());
            venue.setClosingTime(request.getVenueDTO().getClosingTime());
            venue.setOpeningTime(request.getVenueDTO().getOpeningTime());
            venue.setStatus(Status.ACTIVE);
            try {
                venuRepository.save(venue);
            } catch (Exception ex) {
                throw new DatabaseException("Venue", "save");
            }
            VenueDTO venueDTO = new VenueDTO();
            BeanUtils.copyProperties(venue, venueDTO);
            createVenueControllerResponse.setVenueDTO(venueDTO);
        }

        return createVenueControllerResponse;
    }

    @Transactional(rollbackFor = Exception.class)
    public UpdateVenueControllerResponse updateVenue(UpdateVenueControllerRequest request) throws Exception {

        Optional<Venue> optionalVenue = findByName(request.getVenueDTO().getName());
        UpdateVenueControllerResponse updateVenueControllerResponse = new UpdateVenueControllerResponse();

        if (optionalVenue.isPresent()) {
            Venue venue = optionalVenue.get();
            venue.setOpeningTime(request.getVenueDTO().getOpeningTime());
            venue.setClosingTime(request.getVenueDTO().getClosingTime());
            venue.setAddress(request.getVenueDTO().getAddress());
            venue.setName(request.getVenueDTO().getName());
            venue.setNetwork(request.getVenueDTO().getNetwork());
            venue.setCategory(request.getVenueDTO().getCategory());
            try {
                venuRepository.save(venue);
            } catch (Exception ex) {

            }
            VenueDTO venueDTO = new VenueDTO();
            BeanUtils.copyProperties(venue, venueDTO);
            updateVenueControllerResponse.setVenueDTO(venueDTO);
        } else {
            throw new DatabaseException("Venue", "Update");
        }
        return updateVenueControllerResponse;

    }

    private Optional<Venue> findByName(String name) throws Exception {
        try {
            Optional<Venue> venue = venuRepository.getVenueByName(name);
            return venue;
        } catch (Exception ex) {
            throw new DatabaseException("Venue", "Find");
        }

    }
}
