package com.hung.controller.giaovien;

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
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.model.Diem;
import com.hung.model.SinhVien;
@WebServlet(urlPatterns = "/giaovien/lop/sinhvien/diem")
public class DiemSinhVienController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_sinhvien = Integer.parseInt(req.getParameter("id_sinhvien"));
		DiemDao diemDao = new DiemDaoImpl();
		List<Diem> listDiem = diemDao.getDiem(id_sinhvien);
		TaiKhoanSVDao svDao=new TaiKhoanSVDaoImpl();
		SinhVien sv=svDao.getIdSV(id_sinhvien);
		req.setAttribute("sv", sv);
		req.setAttribute("diem", listDiem);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/diemSV.jsp");
		dispatcher.forward(req, resp);
	}
}
