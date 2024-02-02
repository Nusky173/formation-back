package group.fortil.service;

import group.fortil.business.ITagBusiness;

import java.util.List;
import java.util.Optional;

public interface ITagBusinessService<T extends ITagBusiness> extends IBusinessService<T> {

    @Override
    List<T> findAll();

    @Override
    Optional<T> findById(Long id);

    @Override
    T create(T business);

    @Override
    T update(T business);

    @Override
    void delete(T business);
}
