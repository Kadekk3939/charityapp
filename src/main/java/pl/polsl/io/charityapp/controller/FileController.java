package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.polsl.io.charityapp.service.DocumentService;
import org.springframework.http.MediaType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/files")
public class FileController {

    static final String DIRECTORY = System.getProperty("user.dir") + System.getProperty("file.separator") + "file_storage";
    private final DocumentService documentService;

    @Autowired
    public FileController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // dodanie dokumentów do aplikacji
    @PostMapping("/documents/add/{applicationId}")
    public ResponseEntity<List<String>> addDocumentsToApplication(@PathVariable Long applicationId, @RequestParam("files")List<MultipartFile> multipartFiles) {
        List<String> fileNames = new ArrayList<>();
        Boolean uploaded = false;
        String fileName = null;
        for (MultipartFile file : multipartFiles) {
            try {
                uploaded = false;
                fileName = null;
                fileName = uploadSingleFile("d_", file);
                uploaded = true;
            } catch (IOException e) {
                uploaded = false;
            } finally {
                if (uploaded) {
                    fileNames.add(fileName);
                }
            }
        }
        documentService.addDocuments(applicationId, fileNames);
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    // pobranie dokumentu
    @GetMapping("/documents/download/{filename}")
    public ResponseEntity<Resource> addDocumentsToApplication(@PathVariable String filename) {
        try {
            return downloadSingleFile(filename);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // dodanie zdjęć do akcji
//    public ResponseEntity<List<String>> addImagesToAction(@PathVariable Long applicationId, @RequestParam("files")List<MultipartFile> multipartFiles) {
//        List<String> fileNames = new ArrayList<>();
//
//        for (MultipartFile file : multipartFiles) {
//            try {
//                fileNames.add(uploadSingleFile("d_", file));
//            } catch (IOException e) {
//                continue;
//            }
//        }
//        return new ResponseEntity<>(fileNames, HttpStatus.OK);
//    }


    private String uploadSingleFile(String prefix, MultipartFile file) throws IOException {
        String filename = prefix + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
        Files.copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    private ResponseEntity<Resource> downloadSingleFile(String fileName) throws IOException {
        Path filePath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(fileName);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(fileName + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", fileName);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }


}
