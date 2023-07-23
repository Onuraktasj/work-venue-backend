package com.workvenue.backend.data.response;

import com.workvenue.backend.core.util.RestHeader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseControllerResponse implements Serializable {

    static final long serialVersionUID = 2110624253610660320L;

    private RestHeader header;
}
