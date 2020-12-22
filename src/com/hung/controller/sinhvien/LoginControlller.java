package com.hung.controller.sinhvien;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.library.MD5Encoder;
import com.hung.model.GiaoVien;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/login")
public class LoginControlller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/sinhvien/login.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MD5Encoder md5 = new MD5Encoder();
		String msv = req.getParameter("username");
		String pass = req.getParameter("password");
		System.out.println(pass);
		try {
			String password = md5.md5Encoder(pass);
			System.out.println(password);
			TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();
			SinhVien sinhVien = svDao.getSV(msv);
			HttpSession session = req.getSession();
			if (sinhVien != null) {
				if (sinhVien.getMsv().equals(msv) && sinhVien.getPassword().equals(password)) {
					session.setAttribute("loginSinhVien", sinhVien);
					resp.sendRedirect(req.getContextPath() + "/sinhvien/diem");
				} else {
					resp.sendRedirect(req.getContextPath() + "/login");
				}

			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
