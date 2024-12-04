package com.HungSocial.Server.Repository.File;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.File.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Integer> {
    List<FileEntity> findByPostId(Integer postId);
}
