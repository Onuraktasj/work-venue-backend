package com.workvenue.backend.data.request.login;

import com.workvenue.backend.data.request.BaseControllerRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class LoginControllerRequest extends BaseControllerRequest {

    private final String username;
    private final String password;
}
