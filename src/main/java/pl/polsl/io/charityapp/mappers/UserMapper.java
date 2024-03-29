package pl.polsl.io.charityapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.polsl.io.charityapp.model.dto.read.UserReadModel;
import pl.polsl.io.charityapp.model.dto.write.UserWriteModel;
import pl.polsl.io.charityapp.model.entity.User;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserWriteModel userWriteModel);

    @Mapping(source = "userRole.roleName", target = "role")
    UserReadModel toReadModel(User user);

    void updateUserFromDto(UserWriteModel userWriteModel, @MappingTarget User user);

    List<UserReadModel> map(List<User> users);
}
