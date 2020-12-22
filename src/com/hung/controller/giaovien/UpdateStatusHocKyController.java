package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.HocKyDao;
import com.hung.Dao.Impl.HocKyDaoImpl;

@WebServlet(urlPatterns = "/giaovien/hocky/updateStatus")
public class UpdateStatusHocKyController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int status_hk = Integer.parseInt(req.getParameter("status"));
		int id_hocky = Integer.parseInt(req.getParameter("id_hocky"));

		if (status_hk == 1) {
			HocKyDao hockyDao = new HocKyDaoImpl();
			hockyDao.updateStatus(id_hocky, 0);

		}
		if (status_hk == 0) {
			HocKyDao hockyDao = new HocKyDaoImpl();
			hockyDao.updateStatus(id_hocky, 1);
		}
		resp.sendRedirect(req.getContextPath() + "/giaovien/hocky");
	}
}
