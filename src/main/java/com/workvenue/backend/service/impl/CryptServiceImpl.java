package com.workvenue.backend.service.impl;

import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.service.CryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptServiceImpl implements CryptService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encode(final String value) throws ControllerException {
        String encodedValue;
        try {
            encodedValue = bCryptPasswordEncoder.encode(value);
        } catch (Exception exception) {
            //TODO: systemexception
            throw new ControllerException("encode has error: " + exception);
        }
        return encodedValue;
    }

    @Override
    public boolean isMatched(final String inputValue, final String savedAndEncodedValue) throws ControllerException {
        boolean result = false;
        try {
            if (inputValue != null && savedAndEncodedValue != null) {
                result = bCryptPasswordEncoder.matches(inputValue, savedAndEncodedValue);
            }
        } catch (Exception exception) {
            //TODO: systemexception
            throw new ControllerException("isMatched has error: " + exception);
        }
        return result;
    }
}
