package com.workvenue.backend.integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.workvenue.backend.WorkvenueBackendApplication;
import com.workvenue.backend.controller.VisitorController;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WithMockUser(authorities = "ROLE_ADMIN", username = "testuser", password = "password")
class VisitorControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    VisitorController visitorController;

    @Test
    void testFindAll_thenReturnSuccessResponse() {
        // when
        ResponseEntity<RegisterVisitorControllerResponse> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/visitors", HttpMethod.GET, null,
                RegisterVisitorControllerResponse.class);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //        assertEquals(user, responseEntity.getBody());
    }
}
