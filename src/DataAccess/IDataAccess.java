package DataAccess;

import java.util.List;

public interface IDataAccess<T> {

    List<T> getAll();

    T getById(String id);

    void saveAll(List<T> data);

    void add(T entity);

    void update(T entity);

    void delete(String id);
}
