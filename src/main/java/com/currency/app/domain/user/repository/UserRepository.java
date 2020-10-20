package com.currency.app.domain.user.repository;

import com.currency.app.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByPesel(String pesel);

    Boolean existsByPesel(String pesel);
}
