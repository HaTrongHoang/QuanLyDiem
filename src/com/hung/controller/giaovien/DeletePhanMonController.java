package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.PhanMonDao;
import com.hung.Dao.Impl.PhanMonDaoImpl;

@WebServlet(urlPatterns = "/giaovien/deletePM")
public class DeletePhanMonController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_phanmon = Integer.parseInt(req.getParameter("id_phanmon"));
		PhanMonDao phanMonDao = new PhanMonDaoImpl();
		phanMonDao.deletePhanMon(id_phanmon);
		resp.sendRedirect(req.getContextPath() + "/giaovien/phanmon?mess=delete");
	}
}
