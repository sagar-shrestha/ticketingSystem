package com.cellpay.ticketingSystem.common.util;

import jakarta.servlet.http.HttpServletResponse;
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

    private Path foundFile;

    public String saveFile(MultipartFile image) throws Exception {
        if (image.isEmpty()) {
            throw new RuntimeException("Image not found.");
        }

        String originalFileName = image.getOriginalFilename();
        String location = System.getProperty("user.dir") + File.separator + "images" + File.separator;
        File file = new File(location);
        if (!file.exists()) {
            file.mkdir();
        }

        String fileName = UUID.randomUUID() + originalFileName;
        image.transferTo(new File(location + fileName));
        return location + fileName;
    }

    public void getFile(String existingImage, HttpServletResponse httpServletResponse) throws MalformedURLException {
        File file = new File(existingImage);
        httpServletResponse.setContentType("image/" + file.toPath());
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=" + file);
    }

    public Resource getFileAsResource(String existingImage) throws IOException {
        Path dirPath = Paths.get(existingImage);
        return new UrlResource(dirPath.toUri());
    }
}
