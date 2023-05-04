package com.workvenue.backend.core.util;

import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.dto.VisitorDTO;

public class ModelGenerator {

    private ModelGenerator() throws ControllerException {
        throw new ControllerException("Utility class");
    }

    public static VisitorDTO getVisitorDTO() {
        return new VisitorDTO()
                .setEmail("mdogan@gmail.com")
                .setPassword("pass")
                .setFirstName("mert")
                .setLastName("bah")
                .setDescription("desc")
                .setLink("link");
    }
}
