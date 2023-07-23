package com.workvenue.backend.core.util;

import com.workvenue.backend.core.constant.ErrorMessage.GeneralError;
import com.workvenue.backend.core.util.exception.ControllerException;

import java.util.List;

public class ValidationUtil {

    public static void validateIsListEmpty(List<?> list) throws ControllerException {
        if (list.isEmpty()) {
            throw new ControllerException(GeneralError.EMPTY_LIST_ERROR);
        }
    }

    public static void isObjectNull(Object object) throws ControllerException {
        if (object == null) {
            throw new ControllerException(GeneralError.NULL_OBJECT_ERROR);
        }
    }
}