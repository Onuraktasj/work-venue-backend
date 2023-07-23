package com.workvenue.backend.data.request.login;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LoginControllerRequest implements Serializable {

    static final long serialVersionUID = 3501018710228051000L;

    private String username;
    private String password;
}
