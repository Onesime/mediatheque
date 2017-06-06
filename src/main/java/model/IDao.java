package model;

import java.util.ArrayList;

public interface IDao<T> {

	T find(int id);
	ArrayList<T> findAll();
	void update(T t);
  void delete(T t);
  void save(T t);
}
