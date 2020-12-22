package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.model.GiaoVien;

@WebServlet(urlPatterns = "/giaovien/taikhoan")
public class DetailTaiKhoanController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// lấy thông tin đăng nhập
		HttpSession session = req.getSession();
		GiaoVien gv = (GiaoVien) session.getAttribute("loginGiaoVien");
		req.setAttribute("taikhoan", gv);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/detailTaiKhoan.jsp");
		requestDispatcher.forward(req, resp);
	}
}
