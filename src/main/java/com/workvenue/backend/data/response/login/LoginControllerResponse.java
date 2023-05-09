package com.workvenue.backend.data.response.login;

import com.workvenue.backend.data.response.BaseControllerResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginControllerResponse extends BaseControllerResponse {

    private String accessToken;
}
