package com.hung.Dao;

import java.util.List;

import com.hung.model.GiaoVien;
import com.hung.model.SinhVien;

public interface TaiKhoanSVDao {
	List<SinhVien> getAll(int limit, int offset);

	int totalSinhVien();

	void addSinhVien(SinhVien sinhvien);

	SinhVien getSV(String msv);

	void updateSinhVien(SinhVien sinhvien);

	SinhVien getIdSV(int id_sinhvien);

	void deleteSV(int id_sinhvien);

	int totalSearch(String key);

	List<SinhVien> searchSV(String key, int limit, int offset);
	
	List<SinhVien> getSVLop(int lop);
	
	void updateMatKhauSV(int id_sinhvien,String pass);

}
