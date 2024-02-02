package group.fortil.mapper;

import group.fortil.business.TagBusinessImpl;
import group.fortil.model.TagModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITagMapper extends IMapper<TagBusinessImpl, TagModel> {

    @Mapping(source = "tagIndex", target = "index")
    @Mapping(source = "name", target = "value")
    TagBusinessImpl modelToBusiness(TagModel model);

    @Mapping(source = "index", target = "tagIndex")
    @Mapping(source = "value", target = "name")
    TagModel businessToModel(TagBusinessImpl business);
}
