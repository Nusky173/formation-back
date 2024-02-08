package group.fortil.mapper;

import group.fortil.business.MessageBusinessImpl;
import group.fortil.business.TagBusinessImpl;
import group.fortil.business.UserBusinessImpl;
import group.fortil.model.MessageModel;
import group.fortil.model.TagModel;
import group.fortil.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMessageMapperModel extends IMapperModel<MessageBusinessImpl, MessageModel> {

    @Mapping(source = "messageIndex", target = "index")
    @Mapping(source = "content", target = "text")
    @Mapping(source = "publicationDate", target = "publishDate")
    @Mapping(source = "modificationDate", target = "changeDate")
    @Mapping(source = "user", target = "owner")
    @Mapping(source = "tags", target = "tagList")
    MessageBusinessImpl modelToBusiness(MessageModel MessageModel);


    @Mapping(source = "userIndex", target = "index")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "password", target = "loginCode")
    @Mapping(source = "email", target = "emailAddress")
    UserBusinessImpl userModelToBusiness(UserModel userModel);

    @Mapping(source = "tagIndex", target = "index")
    @Mapping(source = "name", target = "value")
    List<TagBusinessImpl> tagsModelToBusiness(List<TagModel> tagsModel);

    @Mapping(source = "index", target = "messageIndex")
    @Mapping(source = "text", target = "content")
    @Mapping(source = "publishDate", target = "publicationDate")
    @Mapping(source = "changeDate", target = "modificationDate")
    @Mapping(source = "owner", target = "user")
    @Mapping(source = "tagList", target = "tags")
    MessageModel businessToModel(MessageBusinessImpl business);

    @Mapping(source = "index", target = "userIndex")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "loginCode", target = "password")
    @Mapping(source = "emailAddress", target = "email")
    UserModel userBusinessToModel(UserBusinessImpl userBusiness);

    @Mapping(source = "tagsBusiness.index", target = "tagIndex")
    @Mapping(source = "tagsBusiness.value", target = "name")
    List<TagModel> tagsBusinessToModel(List<TagBusinessImpl> tagsBusiness);

    default TagModel map(TagBusinessImpl tag) {
        TagModel tagModel = new TagModel();
        tagModel.setName(tag.getValue());
        tagModel.setTagIndex(tag.getIndex());

        return tagModel;
    }

    default TagBusinessImpl map(TagModel tag) {
        TagBusinessImpl tagBusiness = new TagBusinessImpl();
        tagBusiness.setValue(tag.getName());
        tagBusiness.setIndex(tag.getTagIndex());

        return tagBusiness;
    }

    //void updateCarFromCarDto(CarDto carDto, @MappingTarget Car car);
}
