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

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharityActionMapper {
    CharityActionMapper INSTANCE = Mappers.getMapper(CharityActionMapper.class);

    CharityAction toEntity(CharityActionWriteModel charityActionWriteModel);

    @Mapping(target = "shortDescription", constant = "true")
    CharityActionReadModel toShortReadModel(CharityAction charityAction);

//    @Mapping(target = "shortDescription", constant = "false")
//    CharityActionReadModel toLongReadModel(CharityAction charityAction);

    void updateCharityActionFromDto(CharityActionWriteModel charityActionWriteModel, @MappingTarget CharityAction charityAction);

//    @Mapping(target = "charityAction", expression = "java(toShortReadModel(charityAction))")
    List<CharityActionReadModel> map(List<CharityAction> charityActions);
}
