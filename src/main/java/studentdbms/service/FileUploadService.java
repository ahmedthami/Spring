package studentdbms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    /**
     * Saves the uploaded file to the specified upload directory and returns the URL of the saved file.
     *
     * @param file The file to be uploaded.
     * @return The URL of the saved file.
     * @throws IOException If there is an error during file saving.
     */
    public String saveFile(MultipartFile file) throws IOException {
        // Generate a unique filename based on the current timestamp and the original filename
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDirectory, filename);

        // Save the file to the upload directory
        Files.copy(file.getInputStream(), filePath);

        // Return the URL of the saved file (e.g., "/uploads/filename")
        return "/uploads/" + filename;
    }
}
