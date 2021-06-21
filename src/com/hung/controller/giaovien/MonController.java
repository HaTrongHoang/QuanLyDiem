package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.MonDao;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.library.Pagination;
import com.hung.model.Mon;

@WebServlet(urlPatterns = "/giaovien/mon")

public class MonController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

	int totalMon = monDao.totalMon();
	int totalPage = pagination.totalPage(totalMon, limit);
	int offset = pagination.offset(page, limit, totalPage);
	req.setAttribute("totalPage", totalPage);
	List<Integer> listPage = pagination.listPage(totalPage);
	req.setAttribute("pageList", listPage);

	List<Mon> mon = monDao.getAll(limit, offset);
	req.setAttribute("monList", mon);
	RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/mon.jsp");
	requestDispatcher.forward(req, resp);
}
}
