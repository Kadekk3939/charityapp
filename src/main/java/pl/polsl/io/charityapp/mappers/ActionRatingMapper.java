package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.UserReadModel;
import pl.polsl.io.charityapp.model.dto.write.ActionRatingWriteModel;
import pl.polsl.io.charityapp.model.dto.write.UserWriteModel;
import pl.polsl.io.charityapp.model.entity.ActionRating;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.service.CharityActionService;
import pl.polsl.io.charityapp.service.UserService;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserService.class, CharityActionService.class})
public interface ActionRatingMapper {
    ActionRatingMapper INSTANCE = Mappers.getMapper(ActionRatingMapper.class);

    @Mapping(target = "benefactorId", expression = "java(userService.getLoggedUserEntity())")
    @Mapping(target = "charityActionId", expression = "java(charityActionService.getCharityActionEntityByName(actionRatingWriteModel.getActionName()))")
    ActionRating toEntity(UserService userService, CharityActionService charityActionService, ActionRatingWriteModel actionRatingWriteModel);

}
