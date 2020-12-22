package com.hung.Dao;

import java.util.List;

import com.hung.model.GiaoVien;

public interface TaiKhoanGVDao {
	List<GiaoVien> getAll(int limit, int offset);

	int totalGiaoVien();

	GiaoVien getGiaoVien(String mgv);

	GiaoVien searchGVHoten(String mgv);

	void addGiaoVien(GiaoVien giaovien);

	void updateGiaoVien(GiaoVien giaovien);

	GiaoVien getIdGV(int id_giaovien);

	void deleteGV(int id_giaovien);
	
	int totalSearch(String key);

	List<GiaoVien> searchGV(String key, int limit, int offset);
	
	List<GiaoVien> getAll();
	
	void updateMatKhauGV(int id_giaovien,String pass);

}
