package com.HungSocial.Server.Repository.ChatRoomRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.ChatRoomID.ChatRoomID;

@Repository
public interface  ChatRoomRepository extends JpaRepository<ChatRoomID,String> {
    
    @Query("SELECT c FROM ChatRoomID c WHERE " +
    "(c.useroneId = :useroneId AND c.usertwoId = :usertwoId) OR " +
    "(c.useroneId = :usertwoId AND c.usertwoId = :useroneId)")
Optional<ChatRoomID> findChatRoomByUsers(@Param("useroneId") Integer useroneId, @Param("usertwoId") Integer usertwoId);
}
