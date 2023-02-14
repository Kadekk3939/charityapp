package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.DocumentReadModel;
import pl.polsl.io.charityapp.model.dto.write.DocumentWriteModel;
import pl.polsl.io.charityapp.model.entity.Document;
import pl.polsl.io.charityapp.service.ApplicationToCharityActionService;
import pl.polsl.io.charityapp.service.DocumentService;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ApplicationToCharityActionService.class})
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mapping(target = "applicationToCharityActionId", expression = "java(applicationToCharityActionService.getApplicationEntityById(applicationId))")
    Document toEntity(ApplicationToCharityActionService applicationToCharityActionService, Long applicationId, DocumentWriteModel documentWriteModel);

    @Mapping(target = "directory",
            expression = "java(String.format(\"%04x\", document.getApplicationToCharityActionId().getApplicationId()))")
    DocumentReadModel toReadModel(Document document);
    DocumentReadModel toReadModelFromString(String fileName);
    DocumentWriteModel toWriteModel(String fileName);

    List<DocumentWriteModel> mapFromString(List<String> fileNames);
}
