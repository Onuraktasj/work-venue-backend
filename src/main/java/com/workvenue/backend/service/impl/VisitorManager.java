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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorManager implements VisitorService, UserDetailsService {
    private final ModelMapper modelMapper;
    private final VisitorRepository visitorRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public GetAllVisitorControllerResponse findAll() throws ControllerException {
        GetAllVisitorControllerResponse getAllVisitorControllerResponse = new GetAllVisitorControllerResponse();
        List<Visitor> allVisitors = visitorRepository.findAll();
        if (allVisitors.isEmpty())
            throw new ControllerException(VisitorError.GET_ALL_USER_NULL_ERROR);

        Set<VisitorDTO> visitorDTOSet = allVisitors.stream().map(visitor -> modelMapper.map(visitor, VisitorDTO.class))
                                                   .collect(Collectors.toSet());
        getAllVisitorControllerResponse.setGetVisitorDTOSet(visitorDTOSet);
        return getAllVisitorControllerResponse;
    }

    @Override
    @Transactional(rollbackFor = ControllerException.class)
    public RegisterVisitorControllerResponse register(RegisterVisitorControllerRequest request) throws
                                                                                                       ControllerException {
        RegisterVisitorControllerResponse registerVisitorControllerResponse = new RegisterVisitorControllerResponse();
        Visitor visitor = visitorRepository.findByEmail(request.getVisitorDTO().getEmail());
        if (visitor == null) {
            Visitor newVisitor = new Visitor();
            newVisitor.setEmail(request.getVisitorDTO().getEmail());
            newVisitor.setPassword(bCryptPasswordEncoder.encode(request.getVisitorDTO().getPassword()));
            newVisitor.setLastName(request.getVisitorDTO().getLastName());
            newVisitor.setDescription(request.getVisitorDTO().getDescription());
            newVisitor.setLink(request.getVisitorDTO().getLink());
            newVisitor.setStatus(Status.ACTIVE);
            newVisitor.setCreatedDate(OffsetDateTime.now());
            newVisitor.setRoles(Set.of(UserRole.ROLE_VISITOR));
            try {
                visitorRepository.save(newVisitor);
            } catch (Exception ex) {
                throw new ControllerException(VisitorError.SAVE_USER_ERROR);
            }
            VisitorDTO visitorDTO = modelMapper.map(newVisitor, VisitorDTO.class);
            registerVisitorControllerResponse.setVisitorDTO(visitorDTO);
            //TODO: helpera send email onayı
            // servisi
            // yazılcak, aktive edildikten
            // sonra 1'e çek
            // hesap kapatılcaksa 2'ye çek
        } else {
            throw new ControllerException(VisitorError.USER_ALREADY_SAVED);
        }
        return registerVisitorControllerResponse;
    }

    @Override
    @Transactional(rollbackFor = ControllerException.class)
    public UpdateVisitorControllerResponse update(UpdateVisitorControllerRequest request) throws
                                                                                                 ControllerException {
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
    public UserDetails loadUserByUsername(String username) {
        Optional<Visitor> visitor2 = visitorRepository.findByUsername(username);
        Visitor visitor=visitor2.get();
        if (visitor == null) {
            System.out.println(VisitorError.SAVE_USER_ERROR);
        }
        List<SimpleGrantedAuthority> authorities = visitor.getRoles().stream()
                                                          .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                                                          .collect(Collectors.toList());
        return new User(visitor.getUsername(), visitor.getPassword(), authorities);
    }
}