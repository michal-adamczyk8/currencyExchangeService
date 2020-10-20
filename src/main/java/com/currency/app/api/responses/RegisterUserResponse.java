package com.currency.app.api.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUserResponse implements Response {
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;
}
