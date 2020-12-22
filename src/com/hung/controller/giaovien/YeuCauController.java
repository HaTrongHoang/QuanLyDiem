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
import com.hung.Dao.YeuCauDao;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.YeuCauDaoImpl;
import com.hung.library.Pagination;
import com.hung.model.Lop;
import com.hung.model.YeuCau;

@WebServlet(urlPatterns = "/giaovien/yeucau")
public class YeuCauController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageParam = req.getParameter("page");
		int page;
		int limit = 10;
		YeuCauDao yeucauDao = new YeuCauDaoImpl();

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

		int totalYeuCau = yeucauDao.totalYeuCau();
		int totalPage = Pagination.totalPage(totalYeuCau, limit);
		int offset = Pagination.offset(page, limit, totalPage);
		req.setAttribute("totalPage", totalPage);
		List<Integer> listPage = Pagination.listPage(totalPage);
		req.setAttribute("pageList", listPage);
		
		List<YeuCau> yeucau = yeucauDao.getYeuCau(limit, offset);
		req.setAttribute("yeucauList", yeucau);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/yeucau.jsp");
		requestDispatcher.forward(req, resp);
	}
}
