package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.LopDao;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.model.Lop;

@WebServlet(urlPatterns = "/giaovien/updatelop")
public class UpdateLopController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_lop = Integer.parseInt(req.getParameter("id_lop"));
		LopDao lopDao = new LopDaoImpl();
		Lop lopId = lopDao.getLopId(id_lop);
		req.setAttribute("lop", lopId);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/updateLop.jsp");
		requestDispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tenlop = req.getParameter("tenlop");
		int id_lop = Integer.parseInt(req.getParameter("id_lop"));
		String khoa = req.getParameter("khoa");
		LopDao lopDao = new LopDaoImpl();
		Lop lopId = lopDao.getLopId(id_lop);
//kiem tra ten da ton tai chua
		if (tenlop.equals(lopId.getTenlop())) {
			Lop lop = new Lop();
			lop.setId_lop(id_lop);
			lop.setTenlop(tenlop);
			lop.setKhoa(khoa);
			lopDao.updateLop(lop);
			resp.sendRedirect(req.getContextPath() + "/giaovien/lop?mess=update");
		} else {
			if (lopDao.getTenLop(tenlop) != null) {
				resp.sendRedirect(req.getContextPath() + "/giaovien/updatelop?id_lop="+id_lop+"&mess=exist");
			} else {
				Lop lop = new Lop();
				lop.setId_lop(id_lop);
				lop.setTenlop(tenlop);
				lop.setKhoa(khoa);
				lopDao.updateLop(lop);
				resp.sendRedirect(req.getContextPath() + "/giaovien/lop?mess=update");
			}
		}
	}
}
