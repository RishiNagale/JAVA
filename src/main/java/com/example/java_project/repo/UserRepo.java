package com.example.java_project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_project.entity.User;
import java.util.List;



@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	public Optional<User> findByName(String name);
	public List<User> findByEmail(String email);
	
 
}
