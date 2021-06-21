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
@WebServlet(urlPatterns = "/giaovien/updatemon")
public class UpdateMonController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_mon = Integer.parseInt(req.getParameter("id_mon"));
		MonDao monDao = new MonDaoImpl();
		Mon monId = monDao.getMonId(id_mon);
		req.setAttribute("mon", monId);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/updateMon.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mamon = req.getParameter("mamon");
		int id_mon = Integer.parseInt(req.getParameter("id_mon"));
		String tenmon = req.getParameter("tenmon");
		MonDao monDao = new MonDaoImpl();
		Mon monId = monDao.getMonId(id_mon);
//kiem tra ma mon da ton tai chua
		if (mamon.equals(monId.getMamon())) {
			Mon mon = new Mon();
			mon.setId_mon(id_mon);
			mon.setMamon(mamon);
			mon.setTenmon(tenmon);
			monDao.updateMon(mon);
			resp.sendRedirect(req.getContextPath() + "/giaovien/mon?mess=update");
		} else {
			if (monDao.getMaMon(mamon) != null) {
				resp.sendRedirect(req.getContextPath() + "/giaovien/updatemon?id_mon=" + id_mon + "&mess=exist");
			} else {
				Mon mon = new Mon();
				mon.setId_mon(id_mon);
				mon.setMamon(mamon);
				mon.setTenmon(tenmon);
				monDao.updateMon(mon);
				resp.sendRedirect(req.getContextPath() + "/giaovien/mon?mess=update");
			}
		}
	}
}
