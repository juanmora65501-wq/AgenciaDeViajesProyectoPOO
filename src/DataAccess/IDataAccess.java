package DataAccess;

import java.util.List;

public interface IDataAccess<T> {
    T findById(String id);
    List<T> findAll();
    void save(T item);
    void delete(String id);
}