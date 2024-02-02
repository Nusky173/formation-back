package group.fortil.service;

import group.fortil.business.TagBusinessImpl;
import group.fortil.mapper.ITagMapper;
import group.fortil.model.TagModel;
import group.fortil.repository.TagRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class TagServiceImpl<T extends TagBusinessImpl> implements ITagBusinessService<TagBusinessImpl> {

    @Autowired
    private ITagMapper mapper;

    @Autowired
    private TagRepository repository;

    @Override
    public List<TagBusinessImpl> findAll() {
        return this.mapListModelToBusiness(repository.findAll());
    }

    @Override
    public Optional<TagBusinessImpl> findById(Long id) {
        Optional<TagModel> tagModel = repository.findById(id);
        return tagModel.map(model -> mapper.modelToBusiness(model));
    }

    @Override
    public TagBusinessImpl create(@Valid TagBusinessImpl tagBusiness) {
        return saveModelWithBusiness(tagBusiness);
    }

    @Override
    public TagBusinessImpl update(@Valid TagBusinessImpl tagBusiness) {
        return saveModelWithBusiness(tagBusiness);
    }

    @Override
    public void delete(TagBusinessImpl tagBusiness) {
        repository.delete(mapper.businessToModel(tagBusiness));
    }

    public List<TagBusinessImpl> findAllTagsForAMessage() {
        return this.mapListModelToBusiness(repository.findAllTagsForAMessage());
    }

    private List<TagModel> mapListBusinessToModel(List<TagBusinessImpl> tagBusiness) {
        return tagBusiness.stream().map(e -> mapper.businessToModel(e)).collect(Collectors.toList());
    }

    private List<TagBusinessImpl> mapListModelToBusiness(List<TagModel> tagModels) {
        return tagModels.stream().map(e -> mapper.modelToBusiness(e)).collect(Collectors.toList());
    }

    private TagBusinessImpl saveModelWithBusiness(TagBusinessImpl tagBusiness) {
        return mapper.modelToBusiness(repository.save(mapper.businessToModel(tagBusiness)));
    }
}
