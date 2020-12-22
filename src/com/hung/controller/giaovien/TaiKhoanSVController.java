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
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.library.Pagination;
import com.hung.model.GiaoVien;
import com.hung.model.SinhVien;
@WebServlet(urlPatterns = "/giaovien/taikhoan-sv")
public class TaiKhoanSVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageParam = req.getParameter("page");
		int page;
		int limit = 10;
		TaiKhoanSVDao sinhvienDao = new TaiKhoanSVDaoImpl();

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

		int totalSinhVien = sinhvienDao.totalSinhVien();
		int totalPage = pagination.totalPage(totalSinhVien, limit);
		int offset = pagination.offset(page, limit, totalPage);
		req.setAttribute("totalPage", totalPage);
		List<Integer> listPage = pagination.listPage(totalPage);
		req.setAttribute("pageList", listPage);

		List<SinhVien> sinhvien = sinhvienDao.getAll(limit, offset);
		req.setAttribute("svList", sinhvien);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/taikhoanSV.jsp");
		dispatcher.forward(req, resp);
	}
}
