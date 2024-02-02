package group.fortil.service;

import group.fortil.business.IUserBusiness;

import java.util.List;
import java.util.Optional;

public interface IUserBusinessService<T extends IUserBusiness> extends IBusinessService<T> {

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
