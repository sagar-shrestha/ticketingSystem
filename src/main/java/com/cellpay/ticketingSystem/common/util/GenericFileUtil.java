package com.cellpay.ticketingSystem.common.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class GenericFileUtil {

    public String saveFile(MultipartFile photo) throws Exception {
        if (photo.isEmpty()) {
            throw new RuntimeException("Photo not found.");
        }

        String originalFileName = photo.getOriginalFilename();
        String location = System.getProperty("user.dir") + File.separator + "images" + File.separator;
        File file = new File(location);
        if (!file.exists()) {
            file.mkdir();
        }

        String fileName = UUID.randomUUID() + originalFileName;
        photo.transferTo(new File(location + fileName));
        return location + fileName;
    }

    public Resource getFile(String existingPhoto) throws MalformedURLException {
        String location = System.getProperty("user.dir") + File.separator + "image" + File.separator;
        Path filePath = Paths.get(location).resolve(existingPhoto).normalize();
        return new UrlResource(filePath.toUri());
    }
}
