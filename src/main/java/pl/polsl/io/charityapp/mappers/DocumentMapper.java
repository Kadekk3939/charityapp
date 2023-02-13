package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.DocumentReadModel;
import pl.polsl.io.charityapp.model.dto.write.DocumentWriteModel;
import pl.polsl.io.charityapp.model.entity.Document;
import pl.polsl.io.charityapp.service.ApplicationToCharityActionService;
import pl.polsl.io.charityapp.service.CharityActionService;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ApplicationToCharityActionService.class})
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    //@Mapping(source = "applicationId", expression = "java(charityActionService.getApplicationE applicationToCharityActionId)")
    Document toEntity(CharityActionService charityActionService, Long applicationId, DocumentWriteModel documentWriteModel);
    DocumentReadModel toReadModel(Document document);
    DocumentWriteModel toWriteModel(String fileName);

    List<DocumentWriteModel> mapFromString(List<String> fileNames);
    List<DocumentReadModel> map(List<Document> documents);
}
