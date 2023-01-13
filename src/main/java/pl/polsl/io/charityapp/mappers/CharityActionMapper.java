package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.CharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.CharityAction;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharityActionMapper {
    CharityActionMapper INSTANCE = Mappers.getMapper(CharityActionMapper.class);

    @Mapping(target = "shortDescription", constant = "true")
    CharityActionReadModel toReadModel(CharityAction charityAction);

    void updateCharityActionFromDto(CharityActionWriteModel charityActionWriteModel, @MappingTarget CharityAction charityAction);


    List<CharityActionReadModel> map(List<CharityAction> charityActions);
}
