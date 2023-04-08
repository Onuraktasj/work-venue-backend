package com.workvenue.backend.core.util;

import com.workvenue.backend.core.constant.ErrorMessage;

import java.util.List;

public class ValidationUtil {

    public static void validateList(List<?> list) throws Exception {
        if (list.isEmpty())
            throw new Exception(ErrorMessage.VisitorError.GET_VENUE_NULL_ERROR);
    }
}
