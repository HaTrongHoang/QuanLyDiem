package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
@WebServlet(urlPatterns = "/giaovien/deleteSV")
public class DeleteSVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_sinhvien = Integer.parseInt(req.getParameter("id_sinhvien"));
		TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();
		svDao.deleteSV(id_sinhvien);
		resp.sendRedirect(req.getContextPath()+"/giaovien/taikhoan-sv?mess=delete");
	}
}
