package com.pakhi.agile.jparepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakhi.agile.beans.AgileUser;

public interface AgileUserRepository extends JpaRepository<AgileUser, Long> {
	Optional<AgileUser> findByEmailId(String emailId);
}
