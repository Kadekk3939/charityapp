package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.write.ApplicationToCharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.ApplicationToCharityAction;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationToCharityMapper {
    ApplicationToCharityMapper INSTANCE = Mappers.getMapper(ApplicationToCharityMapper.class);

    ApplicationToCharityAction toEntity(ApplicationToCharityActionWriteModel applicationToCharityActionWriteModel);




}
