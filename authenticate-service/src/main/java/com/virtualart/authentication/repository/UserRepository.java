package com.virtualart.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualart.authentication.entity.SignUpModel;
@Repository
public interface UserRepository extends JpaRepository<SignUpModel, Integer> {
	Optional<SignUpModel> findByEmail(String email);

}
