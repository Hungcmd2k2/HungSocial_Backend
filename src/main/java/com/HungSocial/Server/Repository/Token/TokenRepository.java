package com.HungSocial.Server.Repository.Token;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.Token.TokenEntity;


@Repository
public interface  TokenRepository extends JpaRepository<TokenEntity, Integer> {
   
}

