package com.wipro.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.training.model.Admin;



public interface AdminRepository extends JpaRepository<Admin ,Long> {
	Optional<Admin> findByUsername(String username);
	public Optional<Admin> findByPassword(String password);

}
