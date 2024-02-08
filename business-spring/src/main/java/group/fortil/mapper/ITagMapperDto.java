package group.fortil.mapper;

import group.fortil.business.TagBusinessImpl;
import group.fortil.dto.TagDtoImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITagMapperDto extends IMapperDto<TagBusinessImpl, TagDtoImpl> {

    TagBusinessImpl dtoToBusiness(TagDtoImpl dto);

    TagDtoImpl businessToDto(TagBusinessImpl business);
}
