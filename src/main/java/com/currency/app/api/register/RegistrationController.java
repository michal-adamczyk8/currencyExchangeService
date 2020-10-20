package com.currency.app.api.register;

import com.currency.app.api.responses.RegisterUserResponse;
import com.currency.app.domain.user.model.User;
import com.currency.app.domain.user.service.UserService;
import com.currency.app.domain.user.service.exception.InvalidPeselException;
import com.currency.app.domain.user.service.exception.UserAlreadyExistsException;
import com.currency.app.domain.user.service.exception.UserUnder18YearsOldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user/registration")
public class RegistrationController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> processRegistration(@RequestBody RegistrationForm form)
            throws UserUnder18YearsOldException, UserAlreadyExistsException, InvalidPeselException {
        User newUser = form.toUser(passwordEncoder);
        newUser = userService.registerNewUser(newUser);
        RegisterUserResponse response = RegisterUserResponse.builder()
                                                            .userId(newUser.getUserId())
                                                            .firstName(newUser.getFirstName())
                                                            .lastName(newUser.getLastName())
                                                            .pesel(newUser.getPesel())
                                                            .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
