package com.workvenue.backend.integration.service;

import com.workvenue.backend.service.impl.CryptServiceImpl;
import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.data.model.Visitor;
import com.workvenue.backend.data.request.visitor.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import com.workvenue.backend.repository.VisitorRepository;
import com.workvenue.backend.service.impl.VisitorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VisitorServiceIT {

    //Burası servisten repoya ordan dbye gitsin ve geri gelsin
    //diğerinde request vericez

    @Autowired
    private VisitorServiceImpl visitorServiceImpl; //TODO: Service-Impl isimlendirmesine geçelim.

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired //gerekli mi
    private CryptServiceImpl cryptService;

    @Test
    void testRegister() throws ControllerException {
        // given
        //TODO: req util
        RegisterVisitorControllerRequest request = new RegisterVisitorControllerRequest(); //utile taşı
        VisitorDTO visitorDTO = new VisitorDTO("mbahardogan@g.com", "password", "mert", "bahardoğan", "dec", "link");
        request.setVisitorDTO(visitorDTO);
        // when
        RegisterVisitorControllerResponse registerVisitorControllerResponse = visitorServiceImpl.register(request);
        Visitor foundedVisitor = visitorRepository.findByEmail(visitorDTO.getEmail());
        // then
        assertNotNull(registerVisitorControllerResponse);
        assertEquals(visitorDTO.getFirstName(), foundedVisitor.getFirstName());
        assertTrue(cryptService.isMatched(visitorDTO.getPassword(), foundedVisitor.getPassword()));
    }
}
