package com.workvenue.backend.integration.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

//@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VisitorControllerIT {

    //buna service test yazdığımız için mocklayıpta yapılabilir
    //sanki

//    @LocalServerPort
//    private int port;

    //Controller IT'leri E2E gibi rest template ile test etsin
    //    private val restTemplate = TestRestTemplate()
}
