package com.AndrewDSkinner.social_prototype_backend.dto;

import lombok.Getter;

@Getter
public class UserDTOResponse {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;

    public UserDTOResponse() {
        this(null, null, null, null);
    }

    public UserDTOResponse(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}

