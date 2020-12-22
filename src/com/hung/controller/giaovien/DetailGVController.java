package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.model.GiaoVien;
@WebServlet(urlPatterns ="/giaovien/detailGV" )
public class DetailGVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_giaovien = Integer.parseInt(req.getParameter("id_giaovien"));
		TaiKhoanGVDao giaovienDao = new TaiKhoanGVDaoImpl();
		GiaoVien gv = giaovienDao.getIdGV(id_giaovien);
		req.setAttribute("gvDetail", gv);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/detailGV.jsp");
		requestDispatcher.forward(req, resp);
	}
}
