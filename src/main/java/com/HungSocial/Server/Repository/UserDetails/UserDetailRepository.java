package com.HungSocial.Server.Repository.UserDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HungSocial.Server.Entity.UserDetails.UserDetails;

public interface UserDetailRepository extends JpaRepository<UserDetails, Integer> {
    Optional<UserDetails> findByUserid(Integer userid);
}
