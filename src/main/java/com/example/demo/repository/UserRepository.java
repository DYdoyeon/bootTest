package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.entity.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// Select * from user where account=?
	/*
	 * Optional<User> findByAccout(String account);
	 * 
	 * Optional<User> findByEmail(String email);
	 * 
	 * Optional<User> findByAccountAndEmail(String account, String email);
	 */
	User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
