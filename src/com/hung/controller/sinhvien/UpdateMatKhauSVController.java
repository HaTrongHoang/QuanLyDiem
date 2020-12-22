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
@WebServlet(urlPatterns = "/sinhvien/taikhoan/doipass")
public class UpdateMatKhauSVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/sinhvien/updateMatKhau.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String passwordCu = req.getParameter("passwordCu");
		String passwordMoi = req.getParameter("passwordMoi");
		String passwordMoiNhapLai = req.getParameter("passwordMoiNhapLai");

		HttpSession session = req.getSession();
		SinhVien sv = (SinhVien) session.getAttribute("loginSinhVien");

		try {
			if (MD5Encoder.md5Encoder(passwordCu).equals(sv.getPassword())) {
				if (passwordMoi.equals(passwordMoiNhapLai)) {
					TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();
					svDao.updateMatKhauSV(sv.getId_sinhvien(), MD5Encoder.md5Encoder(passwordMoiNhapLai));
					resp.sendRedirect(req.getContextPath() + "/sinhvien/taikhoan/doipass?mess=success");
				} else {
					resp.sendRedirect(req.getContextPath() + "/sinhvien/taikhoan/doipass?mess=passM");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/sinhvien/taikhoan/doipass?mess=passC");
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
