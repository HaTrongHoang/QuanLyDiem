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
import com.hung.Dao.MonDao;
import com.hung.Dao.PhanMonDao;
import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.Dao.Impl.PhanMonDaoImpl;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.model.GiaoVien;
import com.hung.model.Lop;
import com.hung.model.Mon;
import com.hung.model.PhanMon;

@WebServlet(urlPatterns = "/giaovien/updatePM")
public class UpdatePhanMonController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_phanmon = Integer.parseInt(req.getParameter("id_phanmon"));
		PhanMonDao phanMonDao = new PhanMonDaoImpl();
		PhanMon phanMon = phanMonDao.getId(id_phanmon);
		req.setAttribute("phanMon", phanMon); 
		
		LopDao lopDao = new LopDaoImpl();
		List<Lop> lop = lopDao.getAll();
		req.setAttribute("lopList", lop);
		
		MonDao monDao = new MonDaoImpl();
		List<Mon> mon = monDao.getAll();
		req.setAttribute("monList", mon);
		
		TaiKhoanGVDao gvDao = new TaiKhoanGVDaoImpl();
		List<GiaoVien> giaoVien = gvDao.getAll();
		req.setAttribute("gvList", giaoVien);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/updatePhanMon.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_phanmon=Integer.parseInt(req.getParameter("id_phanmon"));
		int id_lop = Integer.parseInt(req.getParameter("lop"));
		int id_mon = Integer.parseInt(req.getParameter("mon"));
		int id_giaovien = Integer.parseInt(req.getParameter("giaovien"));

		PhanMon phanMon = new PhanMon();
		phanMon.setId_phanmon(id_phanmon);
		
		LopDao lopDao = new LopDaoImpl();
		Lop lop = lopDao.getLopId(id_lop);
		phanMon.setLop(lop);

		MonDao monDao = new MonDaoImpl();
		Mon mon = monDao.getMonId(id_mon);
		phanMon.setMon(mon);

		TaiKhoanGVDao gvDao = new TaiKhoanGVDaoImpl();
		GiaoVien gv = gvDao.getIdGV(id_giaovien);
		phanMon.setGiaovien(gv);

		PhanMonDao phanMonDao = new PhanMonDaoImpl();
		phanMonDao.updatePhanMon(phanMon);
		resp.sendRedirect(req.getContextPath()+"/giaovien/phanmon?mess=update");
	}
}
