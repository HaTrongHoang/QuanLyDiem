package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.HocKyDao;
import com.hung.Dao.LopDao;
import com.hung.Dao.MonDao;
import com.hung.Dao.PhanMonDao;
import com.hung.Dao.Impl.HocKyDaoImpl;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.Dao.Impl.PhanMonDaoImpl;
import com.hung.model.HocKy;
import com.hung.model.Lop;
import com.hung.model.Mon;
import com.hung.model.PhanMon;

@WebServlet(urlPatterns = "/giaovien/nhapxuat")
public class NhapXuatController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		LopDao lopDao = new LopDaoImpl();
		List<Lop> lop = lopDao.getAll();
		req.setAttribute("lopList", lop);

		HocKyDao hocKyDao = new HocKyDaoImpl();
		List<HocKy> hockyList = hocKyDao.getAll();
		req.setAttribute("hockyList", hockyList);

		MonDao monDao = new MonDaoImpl();
		List<Mon> monList = monDao.getAll();
		req.setAttribute("monList", monList);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/nhapxuat.jsp");
		requestDispatcher.forward(req, resp);
	}
}
