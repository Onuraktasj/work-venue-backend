package com.workvenue.backend.data.response.login;

import com.workvenue.backend.data.response.BaseControllerResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginControllerResponse extends BaseControllerResponse {

    static final long serialVersionUID = -1646689267763405555L;

    private String accessToken;
}
