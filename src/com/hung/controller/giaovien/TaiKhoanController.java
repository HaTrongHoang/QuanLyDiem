package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.library.Pagination;
import com.hung.model.GiaoVien;

@WebServlet(urlPatterns = "/giaovien/tai-khoan-gv")
public class TaiKhoanController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageParam = req.getParameter("page");
		int page;
		int limit = 10;
		TaiKhoanGVDao giaovienDao = new TaiKhoanGVDaoImpl();

		if (pageParam != null) {
			if (pageParam == "") {
				page = 1;
			} else {
				page = Integer.parseInt(pageParam);
			}

		} else {
			page = 1;

		}
		req.setAttribute("page", page);
		Pagination pagination = new Pagination();

		int totalGiaoVien = giaovienDao.totalGiaoVien();
		int totalPage = pagination.totalPage(totalGiaoVien, limit);
		int offset = pagination.offset(page, limit, totalPage);
		req.setAttribute("totalPage", totalPage);
		List<Integer> listPage = pagination.listPage(totalPage);
		req.setAttribute("pageList", listPage);

		List<GiaoVien> giaoVien = giaovienDao.getAll(limit, offset);
		req.setAttribute("gvList", giaoVien);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/taikhoan.jsp");
		dispatcher.forward(req, resp);
	}
}
