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
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.service.CharityActionService;
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

    static final Path DIRECTORY = Paths.get(System.getProperty("user.dir"), "file_storage");
    static final String APPLICATION_DIRECTORY = "applications";
    static final String ACTION_DIRECTORY = "actions";
    private final DocumentService documentService;
    private final CharityActionService charityActionService;

    @Autowired
    public FileController(DocumentService documentService, CharityActionService charityActionService) {
        this.documentService = documentService;
        this.charityActionService = charityActionService;
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
                fileName = uploadSingleFile(file, APPLICATION_DIRECTORY, documentService.generatePrefixFromId(applicationId));
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
    @GetMapping("/documents/download/{directory}/{filename}")
    public ResponseEntity<Resource> addDocumentsToApplication(@PathVariable String directory, @PathVariable String filename) {
        try {
            return downloadSingleFile(filename, APPLICATION_DIRECTORY, directory);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // dodanie zdjęć do akcji
    @PostMapping("/action/add/{actionName}")
    public ResponseEntity<List<String>> addImagesToAction(@PathVariable String actionName, @RequestParam("files")List<MultipartFile> multipartFiles) {
        List<String> fileNames = new ArrayList<>();
        CharityAction action = charityActionService.getCharityActionEntityByName(actionName);

        Boolean uploaded = false;
        String fileName = null;
        for (MultipartFile file : multipartFiles) {
            try {
                uploaded = false;
                fileName = null;
                fileName = uploadSingleFile(file, ACTION_DIRECTORY, String.format("%04x", action.getActionId()));
                uploaded = true;
            } catch (IOException e) {
                uploaded = false;
            } finally {
                if (uploaded) {
                    fileNames.add(fileName);
                }
            }
        }

        action.setImages(String.join(":", fileNames));
        charityActionService.save(action);
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }


    private String uploadSingleFile(MultipartFile file, String... directories) throws IOException {
        Path directoryPath = Paths.get(DIRECTORY.toString(), directories).toAbsolutePath().normalize();

        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path fileStorage = Paths.get(directoryPath.toString(), filename).toAbsolutePath().normalize();
        Files.copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    private ResponseEntity<Resource> downloadSingleFile(String fileName, String... directories) throws IOException {
        Path directoryPath = Paths.get(DIRECTORY.toString(), directories).toAbsolutePath().normalize().resolve(fileName);

        if(!Files.exists(directoryPath)) {
            throw new FileNotFoundException(fileName + " was not found on the server");
        }
        Resource resource = new UrlResource(directoryPath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", fileName);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(directoryPath)))
                .headers(httpHeaders).body(resource);
    }


}
