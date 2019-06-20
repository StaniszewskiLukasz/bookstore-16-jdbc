package dao;

import java.util.List;

public interface AbstractDao <T> {
    List<T> findAll();
    T findById(long id);
    int insert (T object);
    int update (T object);
    int delete (long id);
}
