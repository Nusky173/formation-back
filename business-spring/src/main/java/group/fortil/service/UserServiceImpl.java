package group.fortil.service;

import group.fortil.business.UserBusinessImpl;
import group.fortil.dto.UserDtoImpl;
import group.fortil.exception.EmailAlreadyUseException;
import group.fortil.exception.EntityNotFoundException;
import group.fortil.mapper.IUserMapperDto;
import group.fortil.mapper.IUserMapperModel;
import group.fortil.model.UserModel;
import group.fortil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class UserServiceImpl<T extends UserDtoImpl> implements IUserBusinessService<UserDtoImpl> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private IUserMapperModel mapperDao;

    @Autowired
    private IUserMapperDto mapperDto;

    @Override
    public List<UserDtoImpl> findAll() {
        return this.mapListBusinessToModel(mapListModelToBusiness(repository.findAll()));
    }

    @Override
    public UserDtoImpl findById(Long id) throws EntityNotFoundException {
        return mapperDto.businessToDto(checkDaoExist(id));
    }

    @Override
    public UserDtoImpl create(UserDtoImpl userDto) throws DataIntegrityViolationException {
        try {
            return saveModelWithBusiness(mapperDto.dtoToBusiness(userDto));
        } catch (DataIntegrityViolationException ex) {
            throw new EmailAlreadyUseException(String.format("Email %s already in use by another user", userDto.getEmailAddress()));
        }
    }

    @Override
    public UserDtoImpl update(UserDtoImpl userDto) throws EntityNotFoundException {
        //dBO reference to user in base
        Optional<UserBusinessImpl> userToUpdate = repository
            .findById(userDto.getIndex())
            .map(model -> mapperDao
                .modelToBusiness(model));

        if (userToUpdate.isEmpty()) {
            throw new EntityNotFoundException(String.format("No found for %s", userDto.getIndex().toString()));
        } else {
            UserModel result = repository.save(mapperDao.businessToModel(mapperDto.dtoToBusiness(userDto)));
            return mapperDto.businessToDto(mapperDao.modelToBusiness(result));
        }

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        repository.delete(mapperDao.businessToModel(checkDaoExist(id)));
    }

    private List<UserDtoImpl> mapListBusinessToModel(List<UserBusinessImpl> usersBusiness) {
        return usersBusiness.stream().map(e -> mapperDto.businessToDto(e)).collect(Collectors.toList());
    }

    private List<UserBusinessImpl> mapListModelToBusiness(List<UserModel> usersBusiness) {
        return usersBusiness.stream().map(e -> mapperDao.modelToBusiness(e)).collect(Collectors.toList());
    }

    private UserDtoImpl saveModelWithBusiness(UserBusinessImpl userBusiness) {
        return mapperDto.businessToDto(
            mapperDao.modelToBusiness(
                repository.save(
                    mapperDao.businessToModel(userBusiness)
                )
            )
        );
    }

    private UserBusinessImpl checkDaoExist(Long id) {
        Optional<UserModel> userModel = repository.findById(id);

        //If user don't exist throw exception
        if (userModel.isEmpty()) {
            throw new EntityNotFoundException(String.format("No user found for %s", id.toString()));
        }

        return mapperDao.modelToBusiness(userModel.get());
    }
}
