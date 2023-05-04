package com.workvenue.backend.integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.workvenue.backend.controller.VisitorController;
import com.workvenue.backend.data.model.Visitor;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

//@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VisitorControllerIT {

    //Controller IT'leri E2E gibi rest template ile test etsin

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    VisitorController visitorController;

    @LocalServerPort
    private int port;

    @Test
    public void testfindAll_thenReturnSuccessResponse() {
        // when
        ResponseEntity<RegisterVisitorControllerResponse> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/visitors",
                RegisterVisitorControllerResponse.class);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(user, responseEntity.getBody());
    }
}
