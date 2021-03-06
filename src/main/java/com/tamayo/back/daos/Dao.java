package com.tamayo.back.daos;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	Optional<T> get(Integer id);

	List<T> getAll();

	void save(T t);

	void update(T t);
	
	void deleteById(Integer t);
}
