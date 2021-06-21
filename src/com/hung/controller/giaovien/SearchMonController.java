package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.LopDao;
import com.hung.Dao.MonDao;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.library.Pagination;
import com.hung.model.Lop;
import com.hung.model.Mon;
@WebServlet(urlPatterns = "/giaovien/searchmon" )
public class SearchMonController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		System.out.println(key);
		req.setAttribute("key", key);
		String pageParam = req.getParameter("page");
		int page;
		int limit = 10;
		MonDao monDao = new MonDaoImpl();

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

		int total = monDao.totalSearch(key);
		int totalPage = pagination.totalPage(total, limit);
		req.setAttribute("totalPage", totalPage);
		int offset = pagination.offset(page, limit, totalPage);
		List<Integer> listPage = pagination.listPage(totalPage);
		req.setAttribute("pageList", listPage);
		List<Mon> mon = monDao.searchMon(key, limit, offset);
		req.setAttribute("monListSearch", mon);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/searchMon.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String key = req.getParameter("key");
		System.out.println(key);
		if (key == "") {
			resp.sendRedirect(req.getContextPath() + "/giaovien/mon");
		} else {
			resp.sendRedirect(
					req.getContextPath() + "/giaovien/searchmon?key=" + java.net.URLEncoder.encode(key, "UTF-8"));
		}
	}
}
