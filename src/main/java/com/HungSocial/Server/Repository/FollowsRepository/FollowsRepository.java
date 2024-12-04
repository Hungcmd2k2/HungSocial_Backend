package com.HungSocial.Server.Repository.FollowsRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.Follows.FollowPK;
import com.HungSocial.Server.Entity.Follows.Follows;
@Repository
public interface  FollowsRepository extends JpaRepository<Follows,FollowPK> {
    Follows findByFollowerIdAndFollowingId(Integer followerId, Integer followingId);

    @Query(value = "SELECT 1 FROM follows WHERE follower_id = :followerId AND following_id = :followingId", nativeQuery = true)
    Optional<Integer> existsFollow(@Param("followerId") Integer followerId, @Param("followingId") Integer followingId);
    
    // @Query("SELECT u FROM User u WHERE u.id IN (SELECT f.followingId FROM Follows f WHERE f.followerId = :followerId)")
    // List<User> findFollowingByFollowerId(@Param("followerId") Integer followerId);

    @Query("SELECT f FROM Follows f WHERE f.followerId = :followerId")
    List<Follows> findFollowingByFollowerId(@Param("followerId") Integer followerId);
    
}
