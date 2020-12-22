package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.HocKyDao;
import com.hung.Dao.LopDao;
import com.hung.Dao.Impl.HocKyDaoImpl;
import com.hung.Dao.Impl.LopDaoImpl;

@WebServlet(urlPatterns = "/giaovien/hocky/delete")
public class DeleteHocKyController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_hocky = Integer.parseInt(req.getParameter("id_hocky"));
		HocKyDao hockyDao = new HocKyDaoImpl();
		hockyDao.deleteHocKy(id_hocky);
		resp.sendRedirect(req.getContextPath() + "/giaovien/hocky?mess=delete");
	}
}
