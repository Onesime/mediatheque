package model;


public interface IDao<T> {

    void delete(T t);

    void save(T t);
}
