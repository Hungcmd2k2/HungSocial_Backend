package com.HungSocial.Server.Service.File;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.Entity.File.FileEntity;
import com.HungSocial.Server.Repository.File.FileRepository;

@Service
public class FileService {
    
@Autowired
private  FileRepository  fileRepository;

public List<FileEntity> getAllFile(Integer postId){
    
    return fileRepository.findByPostId(postId);
}
}
