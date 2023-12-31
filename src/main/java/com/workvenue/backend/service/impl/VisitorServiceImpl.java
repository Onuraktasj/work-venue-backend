package com.workvenue.backend.service.impl;

import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.core.constant.ErrorMessage.VisitorError;
import com.workvenue.backend.core.enums.Status;
import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.data.model.UserRole;
import com.workvenue.backend.data.model.Visitor;
import com.workvenue.backend.data.request.visitor.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.request.visitor.UpdateVisitorControllerRequest;
import com.workvenue.backend.data.response.visitor.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.UpdateVisitorControllerResponse;
import com.workvenue.backend.repository.VisitorRepository;
import com.workvenue.backend.service.VisitorService;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final ModelMapper modelMapper;
    private final VisitorRepository visitorRepository;
    private final CryptServiceImpl cryptService;

    @Override
    public RegisterVisitorControllerResponse register(RegisterVisitorControllerRequest request) throws
            ControllerException {
        RegisterVisitorControllerResponse registerVisitorControllerResponse = new RegisterVisitorControllerResponse();
        try {
            Visitor visitor = visitorRepository.findByEmail(request.getVisitorDTO().getEmail());
            if (Objects.nonNull(visitor)) {
                throw new ControllerException(VisitorError.USER_ALREADY_SAVED);
            }
            Visitor newVisitor = new Visitor();
            newVisitor.setEmail(request.getVisitorDTO().getEmail());
            newVisitor.setUsername(request.getVisitorDTO().getUsername());
            newVisitor.setPassword(cryptService.encode(request.getVisitorDTO().getPassword()));
            newVisitor.setFirstName(request.getVisitorDTO().getFirstName());
            newVisitor.setLastName(request.getVisitorDTO().getLastName());
            newVisitor.setDescription(request.getVisitorDTO().getDescription());
            newVisitor.setLink(request.getVisitorDTO().getLink());
            newVisitor.setStatus(Status.ACTIVE);
            newVisitor.setCreatedDate(OffsetDateTime.now());
            newVisitor.setRoles(Set.of(UserRole.ROLE_VISITOR));
            visitorRepository.save(newVisitor);
        } catch (Exception ex) {
            throw new ControllerException(VisitorError.SAVE_USER_ERROR);
        }
        return registerVisitorControllerResponse;
    }

    @Override
    public UpdateVisitorControllerResponse update(UpdateVisitorControllerRequest request) throws ControllerException {
        UpdateVisitorControllerResponse updateVisitorControllerResponse = new UpdateVisitorControllerResponse();
        Visitor visitor = visitorRepository.findByEmail(request.getVisitorDTO().getEmail());
        if (visitor != null && visitor.getStatus() == Status.ACTIVE) {
            visitor.setFirstName(request.getVisitorDTO().getFirstName());
            visitor.setLastName(request.getVisitorDTO().getLastName());
            visitor.setDescription(request.getVisitorDTO().getDescription());
            visitor.setLink(request.getVisitorDTO().getLink());
            visitor.setUpdatedDate(OffsetDateTime.now());
            try {
                visitorRepository.save(visitor);
            } catch (Exception ex) {
                throw new ControllerException(ErrorMessage.VisitorError.EMAIL_NOT_FOUND);
            }

            VisitorDTO visitorDTO = modelMapper.map(visitor, VisitorDTO.class);
            updateVisitorControllerResponse.setVisitorDTO(visitorDTO);
            return updateVisitorControllerResponse;
        } else {
            throw new ControllerException(VisitorError.GET_USER_NULL_ERROR);
        }
    }

    @Override
    public GetAllVisitorControllerResponse findAll() throws ControllerException {
        GetAllVisitorControllerResponse getAllVisitorControllerResponse = new GetAllVisitorControllerResponse();
        List<Visitor> allVisitors = visitorRepository.findAll();
        if (allVisitors.isEmpty()) {
            throw new ControllerException(VisitorError.GET_ALL_USER_NULL_ERROR);
        }

        Set<VisitorDTO> visitorDTOSet = allVisitors.stream().map(visitor -> modelMapper.map(visitor, VisitorDTO.class))
                .collect(Collectors.toSet());
        getAllVisitorControllerResponse.setGetVisitorDTOSet(visitorDTOSet);
        return getAllVisitorControllerResponse;
    }
}