package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.DiemDao;
import com.hung.Dao.YeuCauDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.YeuCauDaoImpl;
import com.hung.model.Diem;
import com.hung.model.Mon;
import com.hung.model.SinhVien;
import com.hung.model.YeuCau;

@WebServlet(urlPatterns = "/giaovien/yeucau/update")
public class UpdateYeuCauController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_yeucau = Integer.parseInt(req.getParameter("id_yeucau"));

		YeuCauDao yeucauDao = new YeuCauDaoImpl();

		yeucauDao.updateYeuCau(1, id_yeucau);

		YeuCau yeucau = yeucauDao.getId(id_yeucau);

		Diem diem = new Diem();

		diem.setId_diem(yeucau.getDiem().getId_diem());
		diem.setChuyencan(yeucau.getChuyencan());
		diem.setKiemtragk(yeucau.getKiemtragk());
		diem.setKetthuc1(yeucau.getKetthuc1());
		diem.setKetthuc2(yeucau.getKetthuc2());
		diem.setDanhgia(yeucau.getDanhgia());
		diem.setDiemchu(yeucau.getDiemchu());
		diem.setTongket(yeucau.getTongket());
		diem.setGhichu(yeucau.getGhichu());
		diem.setStatus(1);
		SinhVien sv=yeucau.getDiem().getSinhvien();
		diem.setSinhvien(sv);
		Mon mon=yeucau.getDiem().getMon();
		diem.setMon(mon);
		DiemDao diemDao = new DiemDaoImpl();
		diemDao.importExcel(diem);
		resp.sendRedirect(req.getContextPath() + "/giaovien/yeucau");
	}
}
