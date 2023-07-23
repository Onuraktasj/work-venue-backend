package com.workvenue.backend.data.request;

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
public class BaseControllerRequest implements Serializable {

    //TODO: serialversion uid
    private RestHeader header;
}
