package com.hung.Dao;

import java.util.List;

import com.hung.model.PhanMon;

public interface PhanMonDao {
	void addPhanMon(PhanMon phanMon);

	List<PhanMon> getAll(int limit, int offset);

	int totalPhanMon();

	List<PhanMon> getMonGV(int giaovien, int hocky);

	List<PhanMon> getLopGV(int giaovien, int hocky);

	List<PhanMon> getLopSV(int lop, int mon,int hocky);

	void updatePhanMon(PhanMon phanMon);

	PhanMon getId(int id_phanmon);

	void deletePhanMon(int id_phanmon);

	List<PhanMon> getMon(int lop);

	List<PhanMon> getMon(int lop, int hocky);
}
