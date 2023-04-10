package com.workvenue.backend.service.impl;

import com.workvenue.backend.data.model.AppUserRole;
import com.workvenue.backend.service.VisitorService;
import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.data.model.Visitor;
import com.workvenue.backend.data.enums.Status;
import com.workvenue.backend.data.request.visitor.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.request.visitor.UpdateVisitorControllerRequest;
import com.workvenue.backend.data.response.visitor.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.UpdateVisitorControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorManager implements VisitorService {
    private final ModelMapper modelMapper;
    private final VisitorRepository visitorRepository;

    @Override
    public GetAllVisitorControllerResponse getAllVisitors() throws Exception {
        try {
            GetAllVisitorControllerResponse getAllVisitorControllerResponse = new GetAllVisitorControllerResponse();
            Set<Visitor> allVisitors;
            try {
                allVisitors = visitorRepository.getAllVisitors();
            } catch (Exception ex) {
                throw new ControllerException("Visitor and AppUser");
            }

            if (allVisitors.isEmpty())
                throw new Exception(ErrorMessage.VisitorError.GET_USER_NULL_ERROR);

            Set<VisitorDTO> visitorDTOSet = allVisitors
                    .stream()
                    .map(visitor -> modelMapper.map(visitor, VisitorDTO.class))
                    .collect(Collectors.toSet());

            getAllVisitorControllerResponse.setGetVisitorDTOSet(visitorDTOSet);
            return getAllVisitorControllerResponse;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterVisitorControllerResponse registerVisitor(RegisterVisitorControllerRequest request) throws Exception {
        RegisterVisitorControllerResponse registerVisitorControllerResponse = new RegisterVisitorControllerResponse();
        Visitor visitor = visitorRepository.getUserByEmail(request.getVisitorDTO().getEmail());

        if (visitor == null) {
            Visitor newVisitor = new Visitor();
            newVisitor.setEmail(request.getVisitorDTO().getEmail());
            newVisitor.setPassword(request.getVisitorDTO().getPassword());
            newVisitor.setLastName(request.getVisitorDTO().getLastName());
            newVisitor.setDescription(request.getVisitorDTO().getDescription());
            newVisitor.setLink(request.getVisitorDTO().getLink());
            newVisitor.setStatus(Status.ACTIVE);
            newVisitor.setCreatedDate(OffsetDateTime.now());
            newVisitor.setRoles(Set.of(AppUserRole.ROLE_VISITOR));
            try {
                visitorRepository.save(newVisitor);
            } catch (Exception ex) {
                throw new ControllerException("Visitor");
            }
            VisitorDTO visitorDTO = modelMapper.map(newVisitor, VisitorDTO.class);
            registerVisitorControllerResponse.setVisitorDTO(visitorDTO);
            //TODO: helpera send email onayı servisi yazılcak, aktive edildikten sonra 1'e çek hesap kapatılcaksa 2'ye çek
        } else {
            throw new Exception(ErrorMessage.VisitorError.USER_ALREADY_SAVED);
        }
        return registerVisitorControllerResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UpdateVisitorControllerResponse updateVisitor(UpdateVisitorControllerRequest request) throws Exception {
        UpdateVisitorControllerResponse updateVisitorControllerResponse = new UpdateVisitorControllerResponse();
        Visitor visitor = visitorRepository.getUserByEmail(request.getVisitorDTO().getEmail());
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
            throw new ControllerException("Visitor bulunamadı.");
        }
    }
}