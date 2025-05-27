package com.task.taskFiles.service;

import com.task.taskFiles.entity.FileEntity;
import com.task.taskFiles.repository.FileRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity storeFile(MultipartFile file) throws IOException
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Long userid=(Long) authentication.getPrincipal();
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!"application/pdf".equals(file.getContentType())) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }

        FileEntity fileEntity=new FileEntity();
        fileEntity.setFilename(file.getOriginalFilename());
        fileEntity.setFiletype(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setUserId(userid);

        return  fileRepository.save(fileEntity);
    }
}
