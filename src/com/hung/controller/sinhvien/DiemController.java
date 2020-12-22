package com.hung.controller.sinhvien;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.DiemDao;
import com.hung.Dao.HocKyDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.HocKyDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.model.Diem;
import com.hung.model.HocKy;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/sinhvien/diem")
public class DiemController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SinhVien sinhvien = (SinhVien) session.getAttribute("loginSinhVien");

		HocKyDao hocKyDao = new HocKyDaoImpl();
		List<HocKy> hockyList = hocKyDao.getAll();
		req.setAttribute("hockyList", hockyList);

		DiemDao diemDao = new DiemDaoImpl();
		List<Diem> listDiem = diemDao.getDiem(sinhvien.getId_sinhvien());
		req.setAttribute("diem", listDiem);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/sinhvien/diem.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int hocky=Integer.parseInt(req.getParameter("hocky"));
		
		HttpSession session = req.getSession();
		SinhVien sinhvien = (SinhVien) session.getAttribute("loginSinhVien");
		
		DiemDao diemDao = new DiemDaoImpl();
		List<Diem> listDiem = diemDao.getDiem(sinhvien.getId_sinhvien(),hocky);
		req.setAttribute("diem", listDiem);
		
		HocKyDao hocKyDao = new HocKyDaoImpl();
		List<HocKy> hockyList = hocKyDao.getAll();
		req.setAttribute("hockyList", hockyList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/sinhvien/diem.jsp");
		dispatcher.forward(req, resp);
	}
}
