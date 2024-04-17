package com.cellpay.ticketingSystem.common.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class GenericFileUtil {

    public String saveFile(MultipartFile image) throws Exception {
        if (image.isEmpty()) {
            throw new RuntimeException("Image not found.");
        }
        String originalFileName = image.getOriginalFilename();
        String location = System.getProperty("user.dir") + File.separator + "images" + File.separator;
        File file = new File(location);
        if (!file.exists()) file.mkdir();
        String fileName = UUID.randomUUID() + originalFileName;
        image.transferTo(new File(location + fileName));
        return location + fileName;
    }

    public String updateFile(MultipartFile image, String existingImage) throws Exception {
        String originalFileName = image.getOriginalFilename();
        String location = System.getProperty("user.dir") + File.separator + "images" + File.separator;
        String bak_location = System.getProperty("user.dir") + File.separator + "images_bak" + File.separator;
        File file = new File(location);
        File file1 = new File(bak_location);
        if (!file1.exists()) file1.mkdir();
        Files.copy(Paths.get(location + originalFileName),
                Paths.get(bak_location + existingImage), StandardCopyOption.REPLACE_EXISTING);
        deleteFile(file);
        return saveFile(image);
    }

    public void deleteFile(File image) {
        image.delete();
    }

    public void reSaveFile(String existingImage) throws IOException {
        String bak_location = System.getProperty("user.dir") + File.separator + "images_bak" + File.separator;
        String location = System.getProperty("user.dir") + File.separator + "images" + File.separator;
        Files.copy(Paths.get(bak_location + existingImage),
                Paths.get(location + existingImage), StandardCopyOption.COPY_ATTRIBUTES);
    }

    public void getFileAsResource(String existingImage, HttpServletResponse httpServletResponse) throws IOException {
        File file = new File(existingImage);
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + existingImage);
        FileCopyUtils.copy(new FileInputStream(file), httpServletResponse.getOutputStream());
    }
}
