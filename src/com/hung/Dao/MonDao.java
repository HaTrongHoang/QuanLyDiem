package com.hung.Dao;

import java.util.List;

import com.hung.model.Lop;
import com.hung.model.Mon;

public interface MonDao {
	List<Mon> getAll();

	Mon getMonId(int id_mon);

	Mon getMaMon(String mamon);

	List<Mon> getAll(int limit, int offset);

	int totalMon();

	void addMon(Mon mon);

	void deleteMon(int id_mon);

	void updateMon(Mon mon);

	int totalSearch(String key);

	List<Mon> searchMon(String key, int limit, int offset);
}
