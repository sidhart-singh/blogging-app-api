package com.sidhart.singh.blog.services.impl;

import com.sidhart.singh.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
//        File Name
        String fileName = file.getOriginalFilename();

//        random id for file
        String randomId = UUID.randomUUID().toString();
        String newFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

//        Full path
        String filePath = path + File.separator + newFileName;

//        create folder if not already created
        File newFile = new File(path);
        if(!newFile.exists()){
            newFile.mkdir();
        }

//        copy file
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return newFileName;

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);

//        database logic to return inputStream

        return inputStream;
    }
}
