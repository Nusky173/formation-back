package group.fortil.mapper;

import group.fortil.business.MessageBusinessImpl;
import group.fortil.business.TagBusinessImpl;
import group.fortil.business.UserBusinessImpl;
import group.fortil.dto.MessageDtoImpl;
import group.fortil.dto.TagDtoImpl;
import group.fortil.dto.UserDtoImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMessageMapperDto extends IMapperDto<MessageBusinessImpl, MessageDtoImpl> {

    MessageBusinessImpl dtoToBusiness(MessageDtoImpl dto);

    MessageDtoImpl businessToDto(MessageBusinessImpl business);

    UserBusinessImpl userDtoToBusiness(UserDtoImpl userDto);

    UserDtoImpl userBusinessToDto(UserBusinessImpl userBusiness);

    TagDtoImpl tagBusinessToDto(TagBusinessImpl tagBusiness);

    TagBusinessImpl tagDtoToBusiness(TagDtoImpl tagDto);
}
