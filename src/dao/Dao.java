package dao;

import java.util.List;

public interface Dao<K,E> {

    boolean delete(K id);

    E getById(K id);

    List<E> getAll();

    E create(E object);

}
