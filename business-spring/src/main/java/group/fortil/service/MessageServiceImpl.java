package group.fortil.service;

import group.fortil.business.MessageBusinessImpl;
import group.fortil.dto.MessageDtoImpl;
import group.fortil.dto.UserDtoImpl;
import group.fortil.exception.EntityNotFoundException;
import group.fortil.mapper.IMessageMapperDto;
import group.fortil.mapper.IMessageMapperModel;
import group.fortil.mapper.IUserMapperDto;
import group.fortil.model.MessageModel;
import group.fortil.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class MessageServiceImpl<T extends MessageDtoImpl> implements IMessageBusinessService<MessageDtoImpl> {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private IMessageMapperModel mapperDao;

    @Autowired
    private IMessageMapperDto mapperDto;

    @Autowired
    private IUserMapperDto userMapperDto;

    @Override
    public List<MessageDtoImpl> findAll() {
        return mapListBusinessToDto(mapListModelToBusiness(repository.findAll()));
    }

    @Override
    public MessageDtoImpl findById(Long id) {
        return mapperDto.businessToDto(checkDaoExist(id));
    }

    @Override
    public MessageDtoImpl create(MessageDtoImpl dto) {
        return saveModelWithBusiness(mapperDto.dtoToBusiness(dto));
    }

    @Override
    public MessageDtoImpl update(MessageDtoImpl dto) {
        //dBO reference to tag in base
        Optional<MessageBusinessImpl> messageToUpdate = repository
            .findById(dto.getIndex())
            .map(model -> mapperDao
                .modelToBusiness(model));

        if (messageToUpdate.isEmpty()) {
            throw new EntityNotFoundException(String.format("No found for %s", dto.getIndex().toString()));
        } else {
            MessageBusinessImpl business = mapperDto.dtoToBusiness(dto);
            business.setChangeDate(new Date());
            MessageModel result = repository.save(mapperDao.businessToModel(business));

            return mapperDto.businessToDto(mapperDao.modelToBusiness(result));
        }
    }

    @Override
    public void delete(Long id) throws RuntimeException {
        repository.delete(mapperDao.businessToModel(checkDaoExist(id)));
    }

    public List<MessageDtoImpl> findMessagesByUserIndex(UserDtoImpl dto) {
        return mapListBusinessToDto(mapListModelToBusiness(
            repository.findMessagesByUserIndex(mapperDao.userBusinessToModel(userMapperDto.dtoToBusiness(dto)))
        ));
    }

    private List<MessageBusinessImpl> mapListModelToBusiness(List<MessageModel> messageModels) {
        return messageModels.stream().map(e -> mapperDao.modelToBusiness(e)).collect(Collectors.toList());
    }

    private List<MessageDtoImpl> mapListBusinessToDto(List<MessageBusinessImpl> business) {
        return business.stream().map(e -> mapperDto.businessToDto(e)).collect(Collectors.toList());
    }

    private MessageDtoImpl saveModelWithBusiness(MessageBusinessImpl messageBusiness) {
        return mapperDto.businessToDto(mapperDao.modelToBusiness(repository.save(mapperDao.businessToModel(messageBusiness))));
    }

    private MessageBusinessImpl checkDaoExist(Long id) {
        Optional<MessageModel> messageModel = repository.findById(id);

        //If message don't exist throw exception
        if (messageModel.isEmpty()) {
            throw new EntityNotFoundException(String.format("No message found for %s", id.toString()));
        }

        return mapperDao.modelToBusiness(messageModel.get());
    }
}
