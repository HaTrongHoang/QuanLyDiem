package com.hung.Dao;

import java.util.List;

import com.hung.model.Lop;

public interface LopDao {
	List<Lop> getAll(int limit, int offset);

	int totalLop();

	void addLop(Lop lop);

	Lop getTenLop(String tenlop);

	void deleteLop(int id_lop);

	Lop getLopId(int id_lop);

	void updateLop(Lop lop);

	int totalSearch(String key);

	List<Lop> searchLop(String key, int limit, int offset);

	List<Lop> getAll();

}
