package group.fortil.service;

import group.fortil.business.BusinessImpl;

import java.util.List;

public abstract class BusinessServiceImpl<T extends BusinessImpl> implements IBusinessService<T> {

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findById(Long id) {
        return null;
    }

    @Override
    public T create(T business) {
        return null;
    }

    @Override
    public T update(T business) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
