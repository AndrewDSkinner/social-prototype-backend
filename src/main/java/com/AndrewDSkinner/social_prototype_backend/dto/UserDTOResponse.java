package com.AndrewDSkinner.social_prototype_backend.dto;

public class UserDTOResponse {
    private final String token;
    private final String firstName;
    private final String lastName;
    private final String email;

    public UserDTOResponse() {
        this(null, null, null, null);
    }

    public UserDTOResponse(String token, String firstName, String lastName, String email) {
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}

