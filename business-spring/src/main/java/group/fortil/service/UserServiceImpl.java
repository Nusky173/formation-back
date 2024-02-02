package group.fortil.service;

import group.fortil.business.UserBusinessImpl;
import group.fortil.mapper.IUserMapper;
import group.fortil.model.UserModel;
import group.fortil.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class UserServiceImpl<T extends UserBusinessImpl> implements IUserBusinessService<UserBusinessImpl> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private IUserMapper mapper;

    @Override
    public List<UserBusinessImpl> findAll() {
        return this.mapListModelToBusiness(repository.findAll());
    }

    @Override
    public Optional<UserBusinessImpl> findById(Long id) {
        Optional<UserModel> userModel = repository.findById(id);
        return userModel.map(model -> mapper.modelToBusiness(model));
    }

    @Override
    public UserBusinessImpl create(@Valid UserBusinessImpl userBusiness) {
        return this.saveModelWithBusiness(userBusiness);
    }

    @Override
    public UserBusinessImpl update(@Valid UserBusinessImpl userBusiness) {
        return this.saveModelWithBusiness(userBusiness);
    }

    @Override
    public void delete(UserBusinessImpl userBusiness) {
        repository.delete(mapper.businessToModel(userBusiness));
    }

    public UserBusinessImpl findByEmail(String userEmail) {
        return mapper.modelToBusiness(repository.findByEmail(userEmail));
    }

    private List<UserModel> mapListBusinessToModel(List<UserBusinessImpl> usersBusiness) {
        return usersBusiness.stream().map(e -> mapper.businessToModel(e)).collect(Collectors.toList());
    }

    private List<UserBusinessImpl> mapListModelToBusiness(List<UserModel> usersBusiness) {
        return usersBusiness.stream().map(e -> mapper.modelToBusiness(e)).collect(Collectors.toList());
    }

    private UserBusinessImpl saveModelWithBusiness(UserBusinessImpl userBusiness) {
        return mapper.modelToBusiness(repository.save(mapper.businessToModel(userBusiness)));
    }
}
