package com.luv2code.springboot.cruddemo.security;

public enum Luv2codeRole {
    EMPLOYEE("employee"),
    MANAGER("manager"),
    ADMIN("admin");

    private final String value;

    Luv2codeRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
