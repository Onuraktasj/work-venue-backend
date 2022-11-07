package com.workvenue.backend.core.constant;

public enum ControllerName {
    REGISTER_VISITOR(1, "Register Visitor"),
    GET_ALL_VISITOR(2, "Get All Visitor");

    private final int value;
    private final String name;

    ControllerName(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
