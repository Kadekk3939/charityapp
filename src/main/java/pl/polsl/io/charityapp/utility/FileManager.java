package pl.polsl.io.charityapp.utility;

import org.springframework.http.*;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class FileManager {

    public static final String DIRECTORY = System.getProperty("user.dir") + System.getProperty("file.separator") + "file_storage";


    public static List<String> uploadFiles(String prefix, List<MultipartFile> multipartFiles) {
        List<String> filenames = new ArrayList<>();
        try {
            for(MultipartFile file : multipartFiles) {
                String filename = prefix + StringUtils.cleanPath(file.getOriginalFilename());
                Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
                Files.copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
                filenames.add(filename);
            }
        } catch (IOException e) {
            return filenames;
        }
        return filenames;
    }

    //TODO: format and fix
    public static Resource downloadFile(String filename) throws IOException {
        Path filePath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
//                .headers(httpHeaders).body(resource);
        return null;
    }
}
