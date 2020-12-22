package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.LopDao;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.model.Lop;


@WebServlet(urlPatterns = "/giaovien/addlop")
public class AddLopController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/addLop.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tenlop = req.getParameter("tenlop");
		String khoa = req.getParameter("khoa");
		Lop lop = new Lop();
		lop.setTenlop(tenlop);
		lop.setKhoa(khoa);

	
		LopDao lopDao = new LopDaoImpl();
		if (lopDao.getTenLop(tenlop) != null) {
			resp.sendRedirect(req.getContextPath() + "/giaovien/addlop?mess=exist");
		} else {
			lopDao.addLop(lop);
			resp.sendRedirect(req.getContextPath() + "/giaovien/lop?mess=success");
		}

	}
}
