package com.workvenue.backend.service.impl;

import com.workvenue.backend.service.VenueService;
import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.core.util.ValidationUtil;
import com.workvenue.backend.data.dto.VenueDTO;
import com.workvenue.backend.data.model.Venue;
import com.workvenue.backend.data.enums.Status;
import com.workvenue.backend.data.request.venue.CreateVenueControllerRequest;
import com.workvenue.backend.data.request.venue.UpdateVenueControllerRequest;
import com.workvenue.backend.data.response.venue.CreateVenueControllerResponse;
import com.workvenue.backend.data.response.venue.GetAllVenueControllerResponse;
import com.workvenue.backend.data.response.venue.UpdateVenueControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.repository.VenueRepository;
import com.workvenue.backend.repository.VisitorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VenueManager implements VenueService {

    private final VenueRepository venueRepository;
    private final VisitorRepository visitorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VenueManager(VenueRepository venueRepository, VisitorRepository visitorRepository, ModelMapper modelMapper) {
        this.venueRepository = venueRepository;
        this.visitorRepository = visitorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateVenueControllerResponse createVenue(CreateVenueControllerRequest request) throws Exception {
        Optional<Venue> optionalVenue = venueRepository.getVenueByName(request.getVenueDTO().getName());
        CreateVenueControllerResponse createVenueControllerResponse = new CreateVenueControllerResponse();

        if (optionalVenue.isEmpty() && request.getVenueDTO() != null) {
            Venue venue = new Venue();
            venue.setName(request.getVenueDTO().getName());
            venue.setAddress(request.getVenueDTO().getAddress());
            venue.setCategory(request.getVenueDTO().getCategory());
            venue.setNetwork(request.getVenueDTO().getNetwork());
            venue.setClosingTime(request.getVenueDTO().getClosingTime());
            venue.setOpeningTime(request.getVenueDTO().getOpeningTime());
            venue.setStatus(Status.ACTIVE);
            try {
                //TODO: bunu servise ayırıp ona transaction vereceğiz.
                //TODO: tüm repositoryleri servis olarak ayıracağız.
                //TODO: heryerden tek exception, sabit vererek tanımlayacağız. Ve global logu elkya atacağız.
                venueRepository.save(venue);
            } catch (Exception ex) {
                throw new ControllerException("Venue");
            }

            VenueDTO venueDTO = modelMapper.map(venue, VenueDTO.class);
            createVenueControllerResponse.setVenueDTO(venueDTO);
        }
        return createVenueControllerResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UpdateVenueControllerResponse updateVenue(UpdateVenueControllerRequest request) throws Exception {

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
            try {
                venueRepository.save(venue);
            } catch (Exception ex) {
                throw new ControllerException("Venue");
            }
            VenueDTO venueDTO = modelMapper.map(venue, VenueDTO.class);
            updateVenueControllerResponse.setVenueDTO(venueDTO);
        } else {
            throw new ControllerException("Venue");
        }
        return updateVenueControllerResponse;

    }

    @Override
    public GetAllVenueControllerResponse getAllVenues() throws Exception {
        try {
            GetAllVenueControllerResponse getAllVenueControllerResponse = new GetAllVenueControllerResponse();
            List<Venue> allVenues = venueRepository.getAllVenues();
            ValidationUtil.validateList(allVenues);
            List<VenueDTO> venueDTOList = allVenues
                    .stream()
                    .map(venue -> modelMapper.map(venue, VenueDTO.class))
                    .collect(Collectors.toList());
            getAllVenueControllerResponse.setVenueDTOList(venueDTOList);
            return getAllVenueControllerResponse;
        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

    private Optional<Venue> getVenueByName(String name) throws Exception {
        return Optional.ofNullable(venueRepository.getVenueByName(name)
                .orElseThrow(() -> new Exception(ErrorMessage.VisitorError.GET_VENUE_BY_NAME_ERROR)));
    }
}
