package com.hung.Dao;

import java.util.List;

import com.hung.model.HocKy;

public interface HocKyDao {
	List<HocKy> getAll(int limit, int offset);

	int totalHocKy();

	void addHocKy(HocKy hocky);

	HocKy getTenHocKy(String tenhocky);

	void deleteHocKy(int id_hocky);

	HocKy getHocKyId(int id_hocky);

	void updateHocKy(HocKy hocky);

	int totalSearch(String key);

	List<HocKy> searchHocKy(String key, int limit, int offset);

	List<HocKy> getAll();
	
	void updateStatus(int hocky,int status_hk);
}
