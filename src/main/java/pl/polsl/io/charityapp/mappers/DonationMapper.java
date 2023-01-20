package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.write.DonationWriteModel;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.repository.CharityActionRepository;
import pl.polsl.io.charityapp.service.CharityActionService;
import pl.polsl.io.charityapp.service.UserService;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CharityActionService.class,
                UserService.class})
public interface DonationMapper {
    DonationMapper INSTANCE = Mappers.getMapper(DonationMapper.class);

    @Mapping(target = "paymentConfirmed", constant = "false")
    @Mapping(target = "charityActionId",
            expression = "java(charityActionService.getCharityActionEntityByName(donationWriteModel.getCharityActionName()))")
    @Mapping(target = "donorId",
            expression = "java(userService.getLoggedUserEntity())")
    Donation toEntity(DonationWriteModel donationWriteModel, CharityActionService charityActionService, UserService userService);

    //TODO:convert to readModel depending on anonymous value

}
