package com.workvenue.backend.service;

import com.workvenue.backend.core.util.exception.ControllerException;

public interface CryptService {

    String encode(final String value) throws ControllerException;

    boolean isMatched(final String inputValue, final String savedAndEncodedValue) throws ControllerException;
}
