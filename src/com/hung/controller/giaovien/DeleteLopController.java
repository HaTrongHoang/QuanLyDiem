package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.LopDao;
import com.hung.Dao.Impl.LopDaoImpl;

@WebServlet(urlPatterns = "/giaovien/deletelop")
public class DeleteLopController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_lop = Integer.parseInt(req.getParameter("id_lop"));
		LopDao lopDao = new LopDaoImpl();
		lopDao.deleteLop(id_lop);
		resp.sendRedirect(req.getContextPath() + "/giaovien/lop?mess=delete");
	}
}
