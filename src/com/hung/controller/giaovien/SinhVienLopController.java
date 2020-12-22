package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.DiemDao;
import com.hung.Dao.LopDao;
import com.hung.Dao.PhanMonDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.PhanMonDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.model.Diem;
import com.hung.model.Lop;
import com.hung.model.PhanMon;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/giaovien/lop/sinhvien")
public class SinhVienLopController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_lop = Integer.parseInt(req.getParameter("id_lop"));
		TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();
		List<SinhVien> svList = svDao.getSVLop(id_lop);
		LopDao lopDao=new LopDaoImpl();
		Lop lop=lopDao.getLopId(id_lop);
		req.setAttribute("lop", lop);
		req.setAttribute("svList", svList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/svLop.jsp");
		dispatcher.forward(req, resp);
	}
}
