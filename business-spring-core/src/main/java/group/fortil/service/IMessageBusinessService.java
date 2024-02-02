package group.fortil.service;

import group.fortil.business.IMessageBusiness;

import java.util.List;
import java.util.Optional;

public interface IMessageBusinessService<T extends IMessageBusiness> extends IBusinessService {

    List<T> findAll();

    Optional<T> findById(Long id);

    T create(T business);

    T update(T business);

    void delete(T business);
}
