package com.example.UserManagement.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.UserManagement.API.Entity.User_info;

/*logging.level.org.springframework.web.servlet=DEBUG
logging.level.org.springframework = DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.web=DEBUG
*/

@Repository
public  interface UserRepository extends JpaRepository<User_info, Long> {
	 Optional<User_info> findByUsername(String username);
}
