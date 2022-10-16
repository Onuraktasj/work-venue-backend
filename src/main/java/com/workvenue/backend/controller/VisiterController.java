package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.RestMessage;
import com.workvenue.backend.exception.custom.ControllerException;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.data.dto.other.RestHeader;
import com.workvenue.backend.data.dto.request.RegisterVisiterControllerRequest;
import com.workvenue.backend.data.dto.response.GetAllVisiterControllerResponse;
import com.workvenue.backend.data.dto.response.RegisterVisiterControllerResponse;
import com.workvenue.backend.data.entity.Visiter;
import com.workvenue.backend.service.VisiterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/visiters")
public class VisiterController {

    private final VisiterService visiterService;

    @Autowired
    public VisiterController(VisiterService visiterService) {
        this.visiterService = visiterService;
    }

    @PostMapping()
    public ResponseEntity<RegisterVisiterControllerResponse> registerVisiter(@RequestBody RegisterVisiterControllerRequest registerVisiterControllerRequest) throws ControllerException {
        RegisterVisiterControllerResponse registerVisiterControllerResponse;
        try {
            RegisterVisiterControllerResponse response=visiterService.registerVisiter(registerVisiterControllerRequest);
            registerVisiterControllerResponse=response;
            registerVisiterControllerResponse.setHeader(new RestHeader(true, MessageUtil.getMessage("Visiter", RestMessage.CREATED), null));
        } catch (Exception ex) {
            throw new ControllerException("Hata oluştu","mert");
        }
        return new ResponseEntity(registerVisiterControllerResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<GetAllVisiterControllerResponse>> getAllVisiter() throws ControllerException {
        GetAllVisiterControllerResponse response = new GetAllVisiterControllerResponse();
        List<GetAllVisiterControllerResponse> getAllVisiterControllerResponseList = Collections.emptyList();
        try {
            //TODO Businessı servise taşı
            List<Visiter> deneme=visiterService.getAllVisiter();

            Iterator<Visiter> it = deneme.iterator();
            if (it.hasNext()){
                BeanUtils.copyProperties(it.next(),response);
                getAllVisiterControllerResponseList.add(response);
            }
        } catch (Exception ex) {
            throw new IllegalStateException();
        }
        return new ResponseEntity(getAllVisiterControllerResponseList, HttpStatus.CREATED);
    }
}
// https://stackoverflow.com/questions/51739509/spring-boot-global-controller-advice-is-not-loaded-in-spring-context