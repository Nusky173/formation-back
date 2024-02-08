package group.fortil.service;

import java.util.List;

public interface IBusinessService<T> {

    List<T> findAll();

    T findById(Long id);

    T create(T business);

    T update(T business);

    void delete(Long id);
}
