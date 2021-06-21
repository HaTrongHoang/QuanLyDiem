package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.MonDao;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.model.Mon;

@WebServlet(urlPatterns = "/giaovien/addmon")
public class AddMonController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/addMon.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mamon = req.getParameter("mamon");
		String tenmon = req.getParameter("tenmon");
		Mon mon = new Mon();
		mon.setMamon(mamon);
		mon.setTenmon(tenmon);

		MonDao monDao = new MonDaoImpl();
		if (monDao.getMaMon(mamon) != null) {
			resp.sendRedirect(req.getContextPath() + "/giaovien/addmon?mess=exist");
		} else {
			monDao.addMon(mon);;
			resp.sendRedirect(req.getContextPath() + "/giaovien/mon?mess=success");
		}

	}
}
