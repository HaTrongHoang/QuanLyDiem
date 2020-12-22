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

@WebServlet(urlPatterns = "/giaovien/hocky/update")
public class UpdateHocKyController extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_hocky = Integer.parseInt(req.getParameter("id_hocky"));
		HocKyDao hockyDao = new HocKyDaoImpl();
		HocKy hockyId = hockyDao.getHocKyId(id_hocky);
		req.setAttribute("hockyId", hockyId);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/updateHocKy.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tenhocky = req.getParameter("tenhocky");
		int id_hocky = Integer.parseInt(req.getParameter("id_hocky"));
		HocKyDao hockyDao = new HocKyDaoImpl();
		HocKy hockyId = hockyDao.getHocKyId(id_hocky);
//kiem tra ten da ton tai chua
		if (tenhocky.equals(hockyId.getTenhocky())) {
			HocKy hocky = new HocKy();
			hocky.setId_hocky(id_hocky);
			hocky.setTenhocky(tenhocky);
			hockyDao.updateHocKy(hocky);
			resp.sendRedirect(req.getContextPath() + "/giaovien/hocky?mess=update");
		} else {
			if (hockyDao.getTenHocKy(tenhocky) != null) {
				resp.sendRedirect(req.getContextPath() + "/giaovien/hocky/update?id_hocky=" + id_hocky + "&mess=exist");
			} else {
				HocKy hocky = new HocKy();
				hocky.setId_hocky(id_hocky);
				hocky.setTenhocky(tenhocky);
				hockyDao.updateHocKy(hocky);
				resp.sendRedirect(req.getContextPath() + "/giaovien/hocky?mess=update");
			}
		}
	}
}
