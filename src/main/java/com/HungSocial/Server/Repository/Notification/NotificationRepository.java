package com.HungSocial.Server.Repository.Notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.Notification.NotificationEntity;


@Repository
public interface  NotificationRepository extends  JpaRepository<NotificationEntity, Integer>  {
    List<NotificationEntity> findByUserid(Integer userid);
}
