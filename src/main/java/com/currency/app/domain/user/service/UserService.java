package com.currency.app.domain.user.service;

import com.currency.app.domain.user.model.User;
import com.currency.app.domain.user.repository.UserRepository;
import com.currency.app.domain.user.service.exception.InvalidPeselException;
import com.currency.app.domain.user.service.exception.UserAlreadyExistsException;
import com.currency.app.domain.user.service.exception.UserUnder18YearsOldException;
import com.currency.app.domain.user.service.validation.PeselValidation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PeselValidation peselValidation;

    /**
     * Method responsible for checking if there is already another user in the database with the same PESEL
     * @param pesel
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String pesel) throws UsernameNotFoundException {
        User user = userRepository.findByPesel(pesel);
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException("The user with pesel: " + pesel + " doesn't exist. Please register your account.");
    }


    /**
     * Method responsible for saving user to database if it passes validation
     * @param user
     * @return
     * @throws UserAlreadyExistsException
     * @throws UserUnder18YearsOldException
     * @throws InvalidPeselException
     */
    public User registerNewUser(User user) throws UserAlreadyExistsException, UserUnder18YearsOldException, InvalidPeselException {
        validatePesel(user);
        return userRepository.save(user);
    }

    /**
     * Method resposible for validating pesel of the user
     * @param user
     * @throws InvalidPeselException
     * @throws UserAlreadyExistsException
     * @throws UserUnder18YearsOldException
     */
    private void validatePesel(User user) throws InvalidPeselException, UserAlreadyExistsException, UserUnder18YearsOldException {
        String pesel = user.getPesel();
        if (!peselValidation.isPeselLengthCorrect(pesel) || !peselValidation.isChecksumValid(pesel) ) {
            throw new InvalidPeselException();
        }
        if (userRepository.existsByPesel(pesel)) {
            throw new UserAlreadyExistsException();
        }
        if (!peselValidation.isUserOver18YearsOld(pesel)) {
            throw new UserUnder18YearsOldException();
        }
    }
}
