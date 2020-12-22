package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.model.GiaoVien;
import com.hung.model.SinhVien;
@WebServlet(urlPatterns = "/giaovien/detailSV")
public class DetailSVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_sinhvien = Integer.parseInt(req.getParameter("id_sinhvien"));
		TaiKhoanSVDao sinhvienDao = new TaiKhoanSVDaoImpl();
		SinhVien sv = sinhvienDao.getIdSV(id_sinhvien);
		req.setAttribute("svDetail", sv);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/detailSV.jsp");
		requestDispatcher.forward(req, resp);
	}
}
