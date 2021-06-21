package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.MonDao;
import com.hung.Dao.Impl.MonDaoImpl;

@WebServlet(urlPatterns = "/giaovien/deletemon")
public class DeleteMonController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_mon = Integer.parseInt(req.getParameter("id_mon"));
		MonDao monDao = new MonDaoImpl();
		monDao.deleteMon(id_mon);
		resp.sendRedirect(req.getContextPath() + "/giaovien/mon?mess=delete");
	}
}
