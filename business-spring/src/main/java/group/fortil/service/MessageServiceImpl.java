package group.fortil.service;

import group.fortil.business.MessageBusinessImpl;
import group.fortil.business.UserBusinessImpl;
import group.fortil.mapper.IMessageMapper;
import group.fortil.model.MessageModel;
import group.fortil.repository.MessageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class MessageServiceImpl<T extends MessageBusinessImpl> extends BusinessServiceImpl implements IMessageBusinessService<MessageBusinessImpl> {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private IMessageMapper mapper;

    @Override
    public List<MessageBusinessImpl> findAll() {
        return this.mapListModelToBusiness(repository.findAll());
    }

    @Override
    public Optional<MessageBusinessImpl> findById(Long id) {
        Optional<MessageModel> messageModel = repository.findById(id);
        return messageModel.map(model -> mapper.modelToBusiness(model));
    }

    @Override
    public MessageBusinessImpl create(@Valid MessageBusinessImpl messageBusiness) {
        return this.saveModelWithBusiness(messageBusiness);
    }

    @Override
    public MessageBusinessImpl update(@Valid MessageBusinessImpl messageBusiness) {
        return this.saveModelWithBusiness(messageBusiness);
    }

    @Override
    public void delete(MessageBusinessImpl messageBusiness) {
        repository.delete(mapper.businessToModel(messageBusiness));
    }

    public List<MessageBusinessImpl> findMessagesByUserIndex(UserBusinessImpl userBusiness) {
        return this.mapListModelToBusiness(
            repository.findMessagesByUserIndex(mapper.userBusinessToModel(userBusiness))
        );
    }

    private List<MessageModel> mapListBusinessToModel(List<MessageBusinessImpl> messageBusiness) {
        return messageBusiness.stream().map(e -> mapper.businessToModel(e)).collect(Collectors.toList());
    }

    private List<MessageBusinessImpl> mapListModelToBusiness(List<MessageModel> messageModels) {
        return messageModels.stream().map(e -> mapper.modelToBusiness(e)).collect(Collectors.toList());
    }

    private MessageBusinessImpl saveModelWithBusiness(MessageBusinessImpl messageBusiness) {
        return mapper.modelToBusiness(repository.save(mapper.businessToModel(messageBusiness)));
    }
}
