package com.hung.Dao;

import java.util.List;

import com.hung.model.Mon;

public interface MonDao {
	List<Mon> getAll();

	Mon getMonId(int id_mon);

	Mon getMaMon(String mamon);
}
