package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.DocumentMapper;
import pl.polsl.io.charityapp.model.dto.read.DocumentReadModel;
import pl.polsl.io.charityapp.model.dto.write.DocumentWriteModel;
import pl.polsl.io.charityapp.model.entity.Document;
import pl.polsl.io.charityapp.repository.DocumentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    public DocumentReadModel addDocument(Long applicationId, DocumentWriteModel documentWriteModel) {
        Document doc = documentMapper.toEntity(applicationId, documentWriteModel);
        documentRepository.save(doc);
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
        return documentMapper.map(documentRepository.findAllByApplicationToCharityActionId(applicationId));
    }


}
