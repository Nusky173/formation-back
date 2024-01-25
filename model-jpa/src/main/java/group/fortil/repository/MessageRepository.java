package group.fortil.repository;

import group.fortil.model.MessageModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageRepository extends CrudRepository<MessageModel, UUID> {

}
