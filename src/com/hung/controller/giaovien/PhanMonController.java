package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.DiemDao;
import com.hung.Dao.HocKyDao;
import com.hung.Dao.LopDao;
import com.hung.Dao.MonDao;
import com.hung.Dao.PhanMonDao;
import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.HocKyDaoImpl;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.Dao.Impl.PhanMonDaoImpl;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.library.Pagination;
import com.hung.model.Diem;
import com.hung.model.GiaoVien;
import com.hung.model.HocKy;
import com.hung.model.Lop;
import com.hung.model.Mon;
import com.hung.model.PhanMon;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/giaovien/phanmon")
public class PhanMonController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LopDao lopDao = new LopDaoImpl();
		List<Lop> lop = lopDao.getAll();
		req.setAttribute("lopList", lop);
		MonDao monDao = new MonDaoImpl();
		List<Mon> mon = monDao.getAll();
		req.setAttribute("monList", mon);

		TaiKhoanGVDao gvDao = new TaiKhoanGVDaoImpl();
		List<GiaoVien> giaoVien = gvDao.getAll();
		req.setAttribute("gvList", giaoVien);

		HocKyDao hocKyDao = new HocKyDaoImpl();
		List<HocKy> hockyList = hocKyDao.getAll();
		req.setAttribute("hockyList", hockyList);

		String pageParam = req.getParameter("page");
		int page;
		int limit = 10;

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
		PhanMonDao phanMonDao = new PhanMonDaoImpl();
		int totalPhanMon = phanMonDao.totalPhanMon();
		int totalPage = pagination.totalPage(totalPhanMon, limit);
		int offset = pagination.offset(page, limit, totalPage);
		req.setAttribute("totalPage", totalPage);
		List<Integer> listPage = pagination.listPage(totalPage);
		req.setAttribute("pageList", listPage);

		List<PhanMon> phanMonList = phanMonDao.getAll(limit, offset);
		req.setAttribute("phanMonList", phanMonList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/giaovien/phanmon.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// add phân môn
		int id_lop = Integer.parseInt(req.getParameter("lop"));
		int id_mon = Integer.parseInt(req.getParameter("mon"));
		int id_giaovien = Integer.parseInt(req.getParameter("giaovien"));
		int id_hocky = Integer.parseInt(req.getParameter("hocky"));

		PhanMon phanMon = new PhanMon();

		LopDao lopDao = new LopDaoImpl();
		Lop lop = lopDao.getLopId(id_lop);
		phanMon.setLop(lop);

		MonDao monDao = new MonDaoImpl();
		Mon mon = monDao.getMonId(id_mon);
		phanMon.setMon(mon);

		TaiKhoanGVDao gvDao = new TaiKhoanGVDaoImpl();
		GiaoVien gv = gvDao.getIdGV(id_giaovien);
		phanMon.setGiaovien(gv);

		HocKyDao hockyDao = new HocKyDaoImpl();
		HocKy hocky = hockyDao.getHocKyId(id_hocky);
		phanMon.setHocky(hocky);

		PhanMonDao phanMonDao = new PhanMonDaoImpl();
		phanMonDao.addPhanMon(phanMon);
		// add điểm sinh viên lớp
		TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();
		List<SinhVien> svList = svDao.getSVLop(id_lop);
		DiemDao diemDao = new DiemDaoImpl();
		for (SinhVien sinhvien : svList) {
			if (diemDao.getSV(sinhvien.getId_sinhvien(), mon.getId_mon()) == null) {
				Diem diem = new Diem();
				diem.setSinhvien(sinhvien);
				diem.setMon(mon);
				diem.setHocky(hocky);
				diem.setStatus(0);
				diemDao.addDiem(diem);
			}
		}
		doGet(req, resp);

	}
}
