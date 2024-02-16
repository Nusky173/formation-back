package group.fortil.repository;

import group.fortil.IRepository;
import group.fortil.model.MessageModel;
import group.fortil.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends IRepository<MessageModel, Long> {

    List<MessageModel> findMessagesByUser(UserModel userModel);
}
