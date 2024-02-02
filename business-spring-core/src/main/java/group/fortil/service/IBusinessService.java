package group.fortil.service;

import java.util.List;
import java.util.Optional;

public interface IBusinessService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T create(T business);

    T update(T business);

    void delete(T business);
}
