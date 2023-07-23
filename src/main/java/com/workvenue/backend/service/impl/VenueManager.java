package com.workvenue.backend.service.impl;

import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.core.enums.Status;
import com.workvenue.backend.core.util.ValidationUtil;
import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.model.Venue;
import com.workvenue.backend.data.request.venue.CreateVenueControllerRequest;
import com.workvenue.backend.data.request.venue.UpdateVenueControllerRequest;
import com.workvenue.backend.data.response.venue.CreateVenueControllerResponse;
import com.workvenue.backend.data.response.venue.GetAllVenueControllerResponse;
import com.workvenue.backend.data.response.venue.UpdateVenueControllerResponse;
import com.workvenue.backend.repository.VenueRepository;
import com.workvenue.backend.service.VenueService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueManager implements VenueService {

    private final VenueRepository venueRepository;
    private final ModelMapper modelMapper;

    @Override
    public CreateVenueControllerResponse createVenue(CreateVenueControllerRequest request) throws ControllerException {
        Optional<Venue> optionalVenue = venueRepository.findVenueByName(request.getVenueDTO().getName());
        CreateVenueControllerResponse createVenueControllerResponse = new CreateVenueControllerResponse();
        if (optionalVenue.isEmpty()) {
            Venue venue = new Venue().setName(request.getVenueDTO().getName())
                                     .setAddress(request.getVenueDTO().getAddress())
                                     .setCategory(request.getVenueDTO().getCategory())
                                     .setNetwork(request.getVenueDTO().getNetwork())
                                     .setClosingTime(request.getVenueDTO().getClosingTime())
                                     .setOpeningTime(request.getVenueDTO().getOpeningTime()).setStatus(Status.ACTIVE);
            saveVenue(venue);
            VenueDTO venueDTO = modelMapper.map(venue, VenueDTO.class);
            createVenueControllerResponse.setVenueDTO(venueDTO);
        } else {
            throw new ControllerException("Venue name exist in the system.");
        }
        return createVenueControllerResponse;
    }

    @Override
    public UpdateVenueControllerResponse updateVenue(UpdateVenueControllerRequest request) throws ControllerException {
        Optional<Venue> optionalVenue = getVenueByName(request.getVenueDTO().getName());
        UpdateVenueControllerResponse updateVenueControllerResponse = new UpdateVenueControllerResponse();
        if (optionalVenue.isPresent() && request.getVenueDTO() != null) {
            Venue venue = optionalVenue.get();
            venue.setOpeningTime(request.getVenueDTO().getOpeningTime());
            venue.setClosingTime(request.getVenueDTO().getClosingTime());
            venue.setAddress(request.getVenueDTO().getAddress());
            venue.setName(request.getVenueDTO().getName());
            venue.setNetwork(request.getVenueDTO().getNetwork());
            venue.setCategory(request.getVenueDTO().getCategory());
            venue.setStatus(request.getVenueDTO().getStatus());
            saveVenue(venue);
            VenueDTO venueDTO = modelMapper.map(venue, VenueDTO.class);
            updateVenueControllerResponse.setVenueDTO(venueDTO);
        } else {
            throw new ControllerException("Venue could not find.");
        }
        return updateVenueControllerResponse;

    }

    @Override
    public GetAllVenueControllerResponse findAllByIsActive() throws ControllerException {
        try {
            GetAllVenueControllerResponse getAllVenueControllerResponse = new GetAllVenueControllerResponse();
            List<Venue> allVenues = venueRepository.findAll();
            ValidationUtil.validateIsListEmpty(allVenues);
            List<VenueDTO> venueDTOList = allVenues.stream().map(venue -> modelMapper.map(venue, VenueDTO.class))
                                                   .collect(Collectors.toList());
            getAllVenueControllerResponse.setVenueDTOList(venueDTOList);
            return getAllVenueControllerResponse;
        } catch (Exception exception) {
            throw new ControllerException("Error occured in the Venue-findAllByIsActive: " + exception.getMessage());
        }
    }

    @Override
    public Optional<Venue> getVenueByName(String name) throws ControllerException {
        return Optional.ofNullable(venueRepository.findVenueByName(name).orElseThrow(
                () -> new ControllerException(ErrorMessage.VenueError.GET_VENUE_BY_NAME_ERROR)));
    }

    @Override
    public void saveVenue(Venue venue) throws ControllerException {
        try {
            venueRepository.save(venue);
        } catch (Exception exception) {
            throw new ControllerException("Venue could not save: " + exception.getMessage());
        }
    }
}