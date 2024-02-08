package group.fortil.mapper;

import group.fortil.business.UserBusinessImpl;
import group.fortil.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserMapperModel extends IMapperModel<UserBusinessImpl, UserModel> {

    @Mapping(source = "userIndex", target = "index")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "password", target = "loginCode")
    @Mapping(source = "email", target = "emailAddress")
    UserBusinessImpl modelToBusiness(UserModel model);

    @Mapping(source = "index", target = "userIndex")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "loginCode", target = "password")
    @Mapping(source = "emailAddress", target = "email")
    UserModel businessToModel(UserBusinessImpl business);
}
