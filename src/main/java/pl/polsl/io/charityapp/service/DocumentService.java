package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.DocumentMapper;
import pl.polsl.io.charityapp.model.dto.read.DocumentReadModel;
import pl.polsl.io.charityapp.model.dto.write.DocumentWriteModel;
import pl.polsl.io.charityapp.model.entity.ApplicationToCharityAction;
import pl.polsl.io.charityapp.model.entity.Document;
import pl.polsl.io.charityapp.repository.DocumentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final ApplicationToCharityActionService applicationToCharityActionService;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper, ApplicationToCharityActionService applicationToCharityActionService) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.applicationToCharityActionService = applicationToCharityActionService;
    }

    public DocumentReadModel addDocument(Long applicationId, DocumentWriteModel documentWriteModel) {
        Document doc = documentMapper.toEntity(applicationToCharityActionService, applicationId, documentWriteModel);
        ApplicationToCharityAction application = applicationToCharityActionService.getApplicationEntityById(applicationId);
        application.getDocuments().add(documentRepository.save(doc));
        applicationToCharityActionService.save(application);
        return documentMapper.toReadModel(doc);

    }

    public List<DocumentReadModel> addDocuments(Long applicationId, List<String> fileNames) {
        List<DocumentWriteModel> docs = documentMapper.mapFromString(fileNames);
        List<DocumentReadModel> result = new ArrayList<>();

        for (DocumentWriteModel doc : docs) {
            result.add(addDocument(applicationId, doc));
        }
        return result;
    }

    public List<DocumentReadModel> getDocuments(Long applicationId) {
        List<Document> docs = documentRepository.findAllByApplicationToCharityActionId(applicationId);
        return docs.stream().map(documentMapper::toReadModel).collect(Collectors.toList());
    }

    public String generatePrefixFromId(Long applicationId) {
        return String.format("%04x", applicationId);
    }
}
