package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.DiemDao;
import com.hung.Dao.HocKyDao;
import com.hung.Dao.LopDao;
import com.hung.Dao.MonDao;
import com.hung.Dao.PhanMonDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.HocKyDaoImpl;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.Dao.Impl.PhanMonDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.model.Diem;
import com.hung.model.GiaoVien;
import com.hung.model.HocKy;
import com.hung.model.Lop;
import com.hung.model.Mon;
import com.hung.model.PhanMon;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/giaovien/diem")
public class DiemController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// kiem tra la quan tri k
		// quan tri thi hiện tất cả
		HttpSession session = req.getSession();
		GiaoVien gv = (GiaoVien) session.getAttribute("loginGiaoVien");
//		if (gv.getRole() == 0) {
//			LopDao lopDao = new LopDaoImpl();
//			List<Lop> lop = lopDao.getAll();
//			req.setAttribute("lopList", lop);
//			MonDao monDao = new MonDaoImpl();
//			List<Mon> mon = monDao.getAll();
//			req.setAttribute("monList", mon);
//
//		} // giáo vien chí hiển môn và lớp đc phân
//		if (gv.getRole() == 1) {
		PhanMonDao phanMonDao = new PhanMonDaoImpl();
		List<PhanMon> monList = phanMonDao.getMonGV(gv.getId_giaovien(), 1);
		req.setAttribute("monList", monList);
		List<PhanMon> lopList = phanMonDao.getLopGV(gv.getId_giaovien(), 1);
		req.setAttribute("lopList", lopList);
//		}
		HocKyDao hocKyDao = new HocKyDaoImpl();
		List<HocKy> hockyList = hocKyDao.getAll();
		req.setAttribute("hockyList", hockyList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/diem.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_lop = Integer.parseInt(req.getParameter("lop"));
		req.setAttribute("id_lop", id_lop);
		// lấy ten lop
		LopDao lopDao = new LopDaoImpl();
		Lop lop = lopDao.getLopId(id_lop);
		req.setAttribute("tenlop", lop);
		// lay ten mon
		int id_mon = Integer.parseInt(req.getParameter("mon"));

		MonDao monDao = new MonDaoImpl();
		Mon mon = monDao.getMonId(id_mon);
		req.setAttribute("tenmon", mon);
		req.setAttribute("id_mon", id_mon);

		int id_hocky = Integer.parseInt(req.getParameter("hocky"));
		req.setAttribute("id_hocky", id_hocky);
		// lấy danh sách sinh vien
		DiemDao diemDao = new DiemDaoImpl();

		// kiem tra sinh vien
		TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();
		List<SinhVien> svList = svDao.getSVLop(id_lop);
		PhanMonDao phanMon = new PhanMonDaoImpl();
		if (phanMon.getLopSV(id_lop, id_mon, id_hocky) != null) {
			for (SinhVien sinhvien : svList) {
				if (diemDao.getSV(sinhvien.getId_sinhvien(), mon.getId_mon()) == null) {
					Diem diem = new Diem();
					diem.setSinhvien(sinhvien);
					diem.setMon(mon);
					diem.setStatus(0);
					HocKyDao hockyDao = new HocKyDaoImpl();
					HocKy hocky = hockyDao.getHocKyId(id_hocky);
					diem.setHocky(hocky);
					diemDao.addDiem(diem);
				}
			}

		}

		HttpSession session = req.getSession();
		GiaoVien gv = (GiaoVien) session.getAttribute("loginGiaoVien");
		PhanMonDao phanMonDao = new PhanMonDaoImpl();
		List<PhanMon> monList = phanMonDao.getMonGV(gv.getId_giaovien(), 1);
		req.setAttribute("monList", monList);
		List<PhanMon> lopList = phanMonDao.getLopGV(gv.getId_giaovien(), 1);
		req.setAttribute("lopList", lopList);
		HocKyDao hocKyDao = new HocKyDaoImpl();
		List<HocKy> hockyList = hocKyDao.getAll();
		req.setAttribute("hockyList", hockyList);
		List<Diem> diemList = diemDao.getLop(id_lop, id_mon, id_hocky);
		System.out.println(id_lop + "" + id_mon);
		req.setAttribute("diemList", diemList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/diem.jsp");
		dispatcher.forward(req, resp);
	}
}
