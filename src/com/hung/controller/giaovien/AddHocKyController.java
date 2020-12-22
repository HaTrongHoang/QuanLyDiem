package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.HocKyDao;
import com.hung.Dao.LopDao;
import com.hung.Dao.Impl.HocKyDaoImpl;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.model.HocKy;
import com.hung.model.Lop;
@WebServlet(urlPatterns = "/giaovien/addhocky")
public class AddHocKyController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/addHocKy.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tenhocky = req.getParameter("tenhocky");
		HocKy hocky =new HocKy();
		hocky.setTenhocky(tenhocky);
		HocKyDao hockyDao = new HocKyDaoImpl();
		if (hockyDao.getTenHocKy(tenhocky) != null) {
			resp.sendRedirect(req.getContextPath() + "/giaovien/addhocky?mess=exist");
		} else {
			hockyDao.addHocKy(hocky);
			resp.sendRedirect(req.getContextPath() + "/giaovien/hocky?mess=success");
		}
	}
}
