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
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import static com.workvenue.backend.core.util.ModelGenerator.getVisitorDTO;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Service IT Classes start testing the service layer and continue up to the temp database.
 */

@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VisitorServiceIT {

    @Autowired
    private VisitorServiceImpl visitorServiceImpl;

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private CryptServiceImpl cryptService;

    @Test
    void testRegister_givenCorrectRequest_thenSaveVisitor() throws ControllerException {
        // given
        RegisterVisitorControllerRequest request = new RegisterVisitorControllerRequest();
        VisitorDTO visitorDTO = getVisitorDTO();
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
// TODO: transaction test, exception tests.