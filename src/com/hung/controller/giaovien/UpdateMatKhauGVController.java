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
@WebServlet(urlPatterns = "/giaovien/taikhoan/doipass")
public class UpdateMatKhauGVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/updateMatKhau.jsp");
		requestDispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String passwordCu=req.getParameter("passwordCu");
		String passwordMoi=req.getParameter("passwordMoi");
		String passwordMoiNhapLai=req.getParameter("passwordMoiNhapLai");
		
		HttpSession session=req.getSession();
		GiaoVien gv=(GiaoVien) session.getAttribute("loginGiaoVien");
		
		try {
			if(MD5Encoder.md5Encoder(passwordCu).equals(gv.getPassword())){
				if(passwordMoi.equals(passwordMoiNhapLai)) {
					TaiKhoanGVDao gvDao=new TaiKhoanGVDaoImpl();
					gvDao.updateMatKhauGV(gv.getId_giaovien(), MD5Encoder.md5Encoder(passwordMoiNhapLai));
					resp.sendRedirect(req.getContextPath()+"/giaovien/taikhoan/doipass?mess=success");
				}
				else {
					resp.sendRedirect(req.getContextPath()+"/giaovien/taikhoan/doipass?mess=passM");
				}
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/giaovien/taikhoan/doipass?mess=passC");
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
