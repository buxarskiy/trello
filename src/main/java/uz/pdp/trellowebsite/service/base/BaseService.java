package uz.pdp.trellowebsite.service.base;

import java.util.List;

public interface BaseService<T, U> {
    void save(T t);

    void delete(U u);

    void update(T t, U u);

    List<T> findAll();
}
