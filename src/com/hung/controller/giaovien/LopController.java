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
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.library.Pagination;
import com.hung.model.Lop;
@WebServlet(urlPatterns = "/giaovien/lop")
public class LopController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pageParam = req.getParameter("page");
		int page;
		int limit = 10;
		LopDao lopDao = new LopDaoImpl();

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

		int totalLop = lopDao.totalLop();
		int totalPage = pagination.totalPage(totalLop, limit);
		int offset = pagination.offset(page, limit, totalPage);
		req.setAttribute("totalPage", totalPage);
		List<Integer> listPage = pagination.listPage(totalPage);
		req.setAttribute("pageList", listPage);

		List<Lop> lop = lopDao.getAll(limit, offset);
		req.setAttribute("lopList", lop);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/lop.jsp");
		requestDispatcher.forward(req, resp);
	}
}
