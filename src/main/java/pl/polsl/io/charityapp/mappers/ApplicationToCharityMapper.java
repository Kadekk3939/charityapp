package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.ApplicationToCharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.ApplicationToCharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.ApplicationToCharityAction;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserMapper.class, CharityActionMapper.class, DocumentMapper.class})
public interface ApplicationToCharityMapper {
    ApplicationToCharityMapper INSTANCE = Mappers.getMapper(ApplicationToCharityMapper.class);

    ApplicationToCharityAction toEntity(ApplicationToCharityActionWriteModel applicationToCharityActionWriteModel);

    @Mapping(source = "benefactorId", target = "benefactor")
    @Mapping(source = "charityActionId", target = "charityAction")
    ApplicationToCharityActionReadModel toReadModel(ApplicationToCharityAction applicationToCharityAction);

    List<ApplicationToCharityActionReadModel> map(List<ApplicationToCharityAction> applicationToCharityActions);

}
