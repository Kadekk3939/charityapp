package pl.polsl.io.charityapp.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.DonationReadModel;
import pl.polsl.io.charityapp.model.dto.write.DonationWriteModel;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.service.CharityActionService;
import pl.polsl.io.charityapp.service.UserService;

import java.util.List;

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

    @Mapping(target = "fullName", expression = "java(donation.getAnonymous() ? null : donation.getDonorId().getFirstName() + \" \" + donation.getDonorId().getLastName())")
    @Mapping(target = "charityActionName", expression = "java(donation.getCharityActionId().getName())")
    @Mapping(target = "donorLogin", expression = "java(donation.getDonorId().getLogin())")
    DonationReadModel toReadModel(Donation donation);

    List<DonationReadModel> map(List<Donation> donations);
}
