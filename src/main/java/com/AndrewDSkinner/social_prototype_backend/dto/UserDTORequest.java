package com.AndrewDSkinner.social_prototype_backend.dto;

import lombok.Getter;

@Getter
public class UserDTORequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public UserDTORequest() {
        this(null, null, null, null);
    }

    public UserDTORequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

}

