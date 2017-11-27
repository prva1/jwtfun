package com.jwtfun.interfaces.modelhb;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtfun.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String emailAddress);
}
