package group.fortil.mapper;

import group.fortil.business.UserBusinessImpl;
import group.fortil.dto.UserDtoImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapperDto extends IMapperDto<UserBusinessImpl, UserDtoImpl> {

    UserBusinessImpl dtoToBusiness(UserDtoImpl dto);

    UserDtoImpl businessToDto(UserBusinessImpl business);
}
