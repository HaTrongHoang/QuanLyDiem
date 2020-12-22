package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.YeuCauDao;
import com.hung.Dao.Impl.YeuCauDaoImpl;

@WebServlet(urlPatterns = "/giaovien/yeucau/delete")
public class DeleteYeuCauController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_yeucau = Integer.parseInt(req.getParameter("id_yeucau"));

		YeuCauDao yeucauDao = new YeuCauDaoImpl();
		yeucauDao.deleteYeuCau(id_yeucau);
		resp.sendRedirect(req.getContextPath() + "/giaovien/yeucau");
	}
}
