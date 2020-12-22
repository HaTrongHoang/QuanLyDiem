package com.hung.controller.sinhvien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.model.GiaoVien;
import com.hung.model.SinhVien;
@WebServlet(urlPatterns = "/sinhvien/taikhoan")
public class DetailTaiKhoanController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SinhVien sv = (SinhVien) session.getAttribute("loginSinhVien");
		req.setAttribute("taikhoan", sv);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/sinhvien/detailTaiKhoan.jsp");
		requestDispatcher.forward(req, resp);
	}
}
