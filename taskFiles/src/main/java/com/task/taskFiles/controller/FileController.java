package com.task.taskFiles.controller;

import com.task.taskFiles.entity.FileEntity;
import com.task.taskFiles.security.UserPrincipal;
import com.task.taskFiles.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileController {
    private  final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file")MultipartFile file
          //  @AuthenticationPrincipal UserPrincipal userDetails
    ){
        try {
            FileEntity fileEntity = fileService.storeFile(file);
            return ResponseEntity.ok().body(
                    Map.of(
                            "id", fileEntity.getId(),
                            "fileName", fileEntity.getFilename(),
                            "uploadedBy", fileEntity.getUserId(),
                            "createdAt", fileEntity.getCreatedAt()
                    )
            );
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
