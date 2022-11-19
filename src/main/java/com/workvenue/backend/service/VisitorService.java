package com.workvenue.backend.service;

import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.data.entity.User;
import com.workvenue.backend.data.entity.Visitor;
import com.workvenue.backend.data.request.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.response.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.RegisterVisitorControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.repository.UserRepository;
import com.workvenue.backend.repository.VisitorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitorService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(ModelMapper modelMapper, UserRepository userRepository, VisitorRepository visitorRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.visitorRepository = visitorRepository;
    }

    public RegisterVisitorControllerResponse registerVisitor(RegisterVisitorControllerRequest request) throws Exception {
        Optional<User> userOptional = userRepository.getUserByEmail(request.getVisitorDTO().getEmail());
        RegisterVisitorControllerResponse registerVisitorControllerResponse = new RegisterVisitorControllerResponse();

        if (!userOptional.isPresent()) {
            Visitor visitor = new Visitor();
            visitor.setEmail(request.getVisitorDTO().getEmail());
            visitor.setPassword(request.getVisitorDTO().getPassword());
            visitor.setFirstName(request.getVisitorDTO().getFirstName());
            visitor.setLastName(request.getVisitorDTO().getLastName());
            visitor.setCreatedDate();
            visitor.setDescription(request.getVisitorDTO().getDescription());
            visitor.setLink(request.getVisitorDTO().getLink());
            visitor.setActive(true);
            visitorRepository.save(visitor);

            registerVisitorControllerResponse.getVisitorDTO().setFirstName(visitor.getFirstName());
            registerVisitorControllerResponse.getVisitorDTO().setLastName(visitor.getLastName());
        } else {
            throw new Exception(ErrorMessage.USER_ALREADY_SAVED);
        }
        return registerVisitorControllerResponse;
    }

    public GetAllVisitorControllerResponse getAllVisitors() throws Exception {
        try {
            GetAllVisitorControllerResponse getAllVisitorControllerResponse = new GetAllVisitorControllerResponse();
            List<Visitor> allVisitors = visitorRepository.findAll();
            if (allVisitors.isEmpty() || allVisitors == null)
                throw new Exception(ErrorMessage.GET_USER_NULL_ERROR);

            List<VisitorDTO> visitorDTOList = allVisitors
                    .stream()
                    .map(visitor -> modelMapper.map(visitor, VisitorDTO.class))
                    .collect(Collectors.toList());

            getAllVisitorControllerResponse.setGetVisitorDTOList(visitorDTOList);
            return getAllVisitorControllerResponse;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

}