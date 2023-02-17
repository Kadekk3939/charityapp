package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.DonationReadModel;
import pl.polsl.io.charityapp.model.dto.write.DonationWriteModel;
import pl.polsl.io.charityapp.model.dto.write.DonorRatingWriteModel;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.model.entity.DonorRating;
import pl.polsl.io.charityapp.service.CharityActionService;
import pl.polsl.io.charityapp.service.UserService;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CharityActionService.class,
                UserService.class})
public interface DonorRatingMapper {
    DonorRatingMapper INSTANCE = Mappers.getMapper(DonorRatingMapper.class);

    @Mapping(target = "charityActionId",
            expression = "java(charityActionService.getCharityActionEntityByName(donorRatingWriteModel.getCharityActionName()))")
    @Mapping(target = "employeeId",
            expression = "java(userService.getLoggedUserEntity())")
    @Mapping(target = "donorId", expression = "java(userService.getUserEntityByLogin(donorRatingWriteModel.getDonorLogin()))")
    DonorRating toEntity(CharityActionService charityActionService, UserService userService, DonorRatingWriteModel donorRatingWriteModel);
}
