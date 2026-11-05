package pe.edu.utp.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    void create(T entity);
    T update(T entity);
    void delete(T entity);
    void deleteById(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
}