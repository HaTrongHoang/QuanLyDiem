package com.hung.controller.giaovien;

import java.io.IOException;
import java.net.URLEncoder;
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

@WebServlet(urlPatterns = "/giaovien/searchGV")
public class SearchGVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		req.setAttribute("key", key);
		String pageParam = req.getParameter("page");
		int page;
		int limit = 10;
		TaiKhoanGVDao gvDao = new TaiKhoanGVDaoImpl();

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

		int total = gvDao.totalSearch(key);
		int totalPage = pagination.totalPage(total, limit);
		req.setAttribute("totalPage", totalPage);
		int offset = pagination.offset(page, limit, totalPage);
		List<Integer> listPage = pagination.listPage(totalPage);
		req.setAttribute("pageList", listPage);
		List<GiaoVien> gv = gvDao.searchGV(key, limit, offset);
		req.setAttribute("gvListSearch", gv);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/searchGV.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		if (key == "") {
			resp.sendRedirect(req.getContextPath() + "/giaovien/tai-khoan-gv");
		} else {
			resp.sendRedirect(req.getContextPath() + "/giaovien/searchGV?key=" + URLEncoder.encode(key,"UTF-8"));
		}
	}
}
