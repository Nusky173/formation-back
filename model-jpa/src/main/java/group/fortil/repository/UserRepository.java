package group.fortil.repository;

import group.fortil.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserModel, UUID> {

    UserModel findByEmail(String email);
}
