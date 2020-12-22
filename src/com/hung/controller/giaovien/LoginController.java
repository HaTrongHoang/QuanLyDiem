package com.hung.controller.giaovien;

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
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.library.MD5Encoder;
import com.hung.model.GiaoVien;

@WebServlet(urlPatterns = "/login-gv")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/login.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MD5Encoder md5 = new MD5Encoder();
		String mgv = req.getParameter("username");
		String pass = req.getParameter("password");
		System.out.println(pass);
		try {
			String password = md5.md5Encoder(pass);
			System.out.println(password);
			TaiKhoanGVDao gvDao = new TaiKhoanGVDaoImpl();
			GiaoVien giaoVien = gvDao.getGiaoVien(mgv);

			HttpSession session = req.getSession();
			if (giaoVien != null) {
				if (giaoVien.getMgv().equals(mgv) && giaoVien.getPassword().equals(password)) {
					session.setAttribute("loginGiaoVien", giaoVien);
					resp.sendRedirect(req.getContextPath() + "/giaovien/tai-khoan-gv");
				} else {
					resp.sendRedirect(req.getContextPath() + "/login-gv");
				}

			} else {
				resp.sendRedirect(req.getContextPath() + "/login-gv");
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
