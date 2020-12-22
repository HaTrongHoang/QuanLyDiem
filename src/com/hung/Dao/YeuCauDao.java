package com.hung.Dao;

import java.util.List;

import com.hung.model.YeuCau;

public interface YeuCauDao {
	List<YeuCau> getYeuCau(int limit, int offset);

	int totalYeuCau();

	void addYeuCau(YeuCau yeucau);

	YeuCau getId(int id_yeucau);

	void updateYeuCau(int status, int id_yeucau);

	void deleteYeuCau(int id_yeucau);
}
