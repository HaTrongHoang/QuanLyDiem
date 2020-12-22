package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;

@WebServlet(urlPatterns = "/giaovien/delete")
public class DeleteGVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_giaovien = Integer.parseInt(req.getParameter("id_giaovien"));
		TaiKhoanGVDao gvDao = new TaiKhoanGVDaoImpl();
		gvDao.deleteGV(id_giaovien);
		System.out.println("xoa user id:"+ id_giaovien);
		resp.sendRedirect(req.getContextPath() + "/giaovien/tai-khoan-gv?mess=delete");
	}
}
