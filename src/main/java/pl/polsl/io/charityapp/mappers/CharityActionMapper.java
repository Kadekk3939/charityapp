package pl.polsl.io.charityapp.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.CharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.CharityAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharityActionMapper {
    CharityActionMapper INSTANCE = Mappers.getMapper(CharityActionMapper.class);

    CharityAction toEntity(CharityActionWriteModel charityActionWriteModel);

    @Named("CharityActionShortDescription")
    @Mapping(target = "shortDescription", constant = "true")
    @Mapping(target = "directory", expression = "java(String.format(\"%04x\", charityAction.getActionId()))")
    CharityActionReadModel toShortReadModel(CharityAction charityAction);

    @Named("CharityActionLongDescription")
    @Mapping(target = "shortDescription", constant = "false")
    @Mapping(target = "directory", expression = "java(String.format(\"%04x\", charityAction.getActionId()))")
    CharityActionReadModel toLongReadModel(CharityAction charityAction);

    void updateCharityActionFromDto(CharityActionWriteModel charityActionWriteModel, @MappingTarget CharityAction charityAction);

     default List<String> mapImages(String images) {
         return images == null ? new ArrayList<>() : Arrays.asList(images.split(";"));
     }

    @IterableMapping(qualifiedByName = "CharityActionShortDescription")
    List<CharityActionReadModel> map(List<CharityAction> charityActions);
}
