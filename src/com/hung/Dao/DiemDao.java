package com.hung.Dao;

import java.util.List;

import com.hung.model.Diem;

public interface DiemDao {
	List<Diem> getLop(int lop, int mamon, int hocky);

	void updateDiem(Diem diem);

	void addDiem(Diem diem);

	Diem getSV(int sinhvien, int mon);

	void importExcel(Diem diem);

	List<Diem> getDiem(int sinhvien);

	List<Diem> getThiLai(int mamon);

	Diem getId(int id_diem);

	void importExcelL1(Diem diem);
	
	void importExcelL2(Diem diem);
	
	List<Diem> getDiem(int sinhvien , int hocky);
	
	List<Diem> getThiLai(int mamon,int lop);
	
	void updateDiemAdmin(Diem diem);
}
