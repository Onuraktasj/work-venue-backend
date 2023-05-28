package com.workvenue.backend.data.request.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class LoginControllerRequest implements Serializable {

    static final long serialVersionUID = 3501018710228051000L;

    private final String username;
    private final String password;
}
