package group.fortil;

import org.springframework.data.repository.ListCrudRepository;

public interface IRepository<T, U> extends ListCrudRepository<T, U> {
}
