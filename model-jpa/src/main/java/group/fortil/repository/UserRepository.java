package group.fortil.repository;

import group.fortil.IRepository;
import group.fortil.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<UserModel, Long> {

    UserModel findByEmail(String email);
}
