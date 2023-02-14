package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.DocumentReadModel;
import pl.polsl.io.charityapp.model.dto.write.DocumentWriteModel;
import pl.polsl.io.charityapp.model.entity.Document;
import pl.polsl.io.charityapp.service.ApplicationToCharityActionService;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ApplicationToCharityActionService.class})
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mapping(target = "applicationToCharityActionId", expression = "java(applicationToCharityActionService.getApplicationEntityById(applicationId))")
    Document toEntity(ApplicationToCharityActionService applicationToCharityActionService, Long applicationId, DocumentWriteModel documentWriteModel);

    DocumentReadModel toReadModel(Document document);
    DocumentReadModel toReadModelFromString(String fileName);
    DocumentWriteModel toWriteModel(String fileName);

    List<DocumentWriteModel> mapFromString(List<String> fileNames);
    List<DocumentReadModel> map(List<Document> documents);
}
