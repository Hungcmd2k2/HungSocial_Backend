package com.HungSocial.Server.Repository.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HungSocial.Server.DTO.User.SearchUserDTO;
import com.HungSocial.Server.Entity.User.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    @Query("SELECT new com.HungSocial.Server.DTO.User.SearchUserDTO(u.id,u.username, ud.fullname, ud.avatar) " +
       "FROM User u JOIN UserDetails ud ON u.id = ud.userid " +
       "WHERE u.username LIKE CONCAT('%', :keyword, '%')")
List<SearchUserDTO> findByUsernameContaining(@Param("keyword") String keyword);
}
