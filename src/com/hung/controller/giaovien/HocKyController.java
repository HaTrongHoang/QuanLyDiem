package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.List;

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
import com.hung.library.Pagination;
import com.hung.model.HocKy;
import com.hung.model.Lop;

@WebServlet(urlPatterns = "/giaovien/hocky")
public class HocKyController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageParam = req.getParameter("page");
		int page;
		int limit = 10;
		HocKyDao hockyDao = new HocKyDaoImpl();

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

		int totalHocKy = hockyDao.totalHocKy();
		int totalPage = pagination.totalPage(totalHocKy, limit);
		int offset = pagination.offset(page, limit, totalPage);
		req.setAttribute("totalPage", totalPage);
		List<Integer> listPage = pagination.listPage(totalPage);
		req.setAttribute("pageList", listPage);

		List<HocKy> hocky = hockyDao.getAll(limit, offset);
		req.setAttribute("hockyList", hocky);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/hocky.jsp");
		requestDispatcher.forward(req, resp);
	}
}
