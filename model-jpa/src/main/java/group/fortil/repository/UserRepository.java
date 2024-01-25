package group.fortil.repository;

import group.fortil.model.MessageModel;
import group.fortil.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserModel, UUID> {

    List<MessageModel> findMessagesByUserId(UUID id);
}
