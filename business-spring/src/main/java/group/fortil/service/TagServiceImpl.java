package group.fortil.service;

import group.fortil.business.TagBusinessImpl;
import group.fortil.dto.TagDtoImpl;
import group.fortil.exception.EntityNotFoundException;
import group.fortil.mapper.ITagMapperDto;
import group.fortil.mapper.ITagMapperModel;
import group.fortil.model.TagModel;
import group.fortil.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class TagServiceImpl<T extends TagDtoImpl> implements ITagBusinessService<TagDtoImpl> {

    @Autowired
    private ITagMapperModel mapperDao;

    @Autowired
    private ITagMapperDto mapperDto;

    @Autowired
    private TagRepository repository;

    @Override
    public List<TagDtoImpl> findAll() {
        return this.mapListBusinessToDto(mapListModelToBusiness(repository.findAll()));
    }

    @Override
    public TagDtoImpl findById(Long id) {
        return mapperDto.businessToDto(checkDaoExist(id));
    }

    @Override
    public TagDtoImpl create(TagDtoImpl tagDto) {
        return mapperDto.businessToDto(saveModelWithBusiness(mapperDto.dtoToBusiness(tagDto)));
    }

    @Override
    public TagDtoImpl update(TagDtoImpl tagDto) {
        //dBO reference to tag in base
        Optional<TagBusinessImpl> tagToUpdate = repository
            .findById(tagDto.getIndex())
            .map(model -> mapperDao
                .modelToBusiness(model));

        if (tagToUpdate.isEmpty()) {
            throw new EntityNotFoundException(String.format("No tag found for %s", tagDto.getIndex().toString()));
        } else {
            TagModel result = repository.save(mapperDao.businessToModel(mapperDto.dtoToBusiness(tagDto)));
            return mapperDto.businessToDto(mapperDao.modelToBusiness(result));
        }
    }

    @Override
    public void delete(Long tagId) {
        repository.delete(mapperDao.businessToModel(checkDaoExist(tagId)));
    }

    public List<TagDtoImpl> findAllTagsForAMessage() {
        return mapListBusinessToDto(mapListModelToBusiness(repository.findAllTagsForAMessage()));
    }

    private List<TagDtoImpl> mapListBusinessToDto(List<TagBusinessImpl> tagBusiness) {
        return tagBusiness.stream().map(e -> mapperDto.businessToDto(e)).collect(Collectors.toList());
    }

    private List<TagBusinessImpl> mapListModelToBusiness(List<TagModel> tagModels) {
        return tagModels.stream().map(e -> mapperDao.modelToBusiness(e)).collect(Collectors.toList());
    }

    private TagBusinessImpl saveModelWithBusiness(TagBusinessImpl tagBusiness) {
        return mapperDao.modelToBusiness(repository.save(mapperDao.businessToModel(tagBusiness)));
    }

    private TagBusinessImpl checkDaoExist(Long id) {
        Optional<TagModel> tagModel = repository.findById(id);

        //If tag don't exist throw exception
        if (tagModel.isEmpty()) {
            throw new EntityNotFoundException(String.format("No tag found for %s", id.toString()));
        }

        return mapperDao.modelToBusiness(tagModel.get());
    }
}
