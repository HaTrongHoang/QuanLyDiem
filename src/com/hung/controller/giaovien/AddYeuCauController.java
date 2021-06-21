package com.hung.controller.giaovien;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.DiemDao;
import com.hung.Dao.YeuCauDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.YeuCauDaoImpl;
import com.hung.model.Diem;
import com.hung.model.GiaoVien;
import com.hung.model.YeuCau;

@WebServlet(urlPatterns = "/giaovien/yeucauUpdate")
public class AddYeuCauController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id_lop = req.getParameter("id_lop");
		String id_mon = req.getParameter("id_mon");
		String id_hocky = req.getParameter("id_hocky");
		int id_diem = Integer.parseInt(req.getParameter("id_diemUpdate"));
		int status = Integer.parseInt(req.getParameter("statusUpdate"));
		String chuyencan = req.getParameter("chuyencanUpdate");
		String kiemtragk = req.getParameter("kiemtragkUpdate");
		String ketthuc1 = req.getParameter("ketthuc1Update");
		String ketthuc2 = req.getParameter("ketthuc2Update");
		String tongket = req.getParameter("tongketUpdate");
		String ghichu = req.getParameter("ghichuUpdate");
		HttpSession session = req.getSession();
		GiaoVien user = (GiaoVien) session.getAttribute("loginGiaoVien");
		DiemDao diemDao = new DiemDaoImpl();
		YeuCauDao yeucauDao = new YeuCauDaoImpl();
		if (status == 0) {
			YeuCau yeucau = new YeuCau();
			Diem diem = new Diem();
			diem.setId_diem(id_diem);
			diem.setChuyencan(chuyencan);
			if (diem.getChuyencan().equals("F")) {
				diem.setKiemtragk("0");
				diem.setKetthuc1("0");
				diem.setTongket("0");
				diem.setDanhgia("HOCLAI");
				diem.setDiemchu("");
				diem.setKetthuc2("");
				diem.setGhichu("Cấm thi");
				diem.setStatus(1);
			} else {
				diem.setKiemtragk(kiemtragk);
				diem.setStatus(1);
			}
			diemDao.updateDiem(diem);
		}
		if (status == 1) {
			YeuCau yeucau = new YeuCau();
			yeucau.setDiem(diemDao.getId(id_diem));
			yeucau.setChuyencan(chuyencan);
			if (yeucau.getChuyencan().equals("F")) {
				yeucau.setKiemtragk("0");
				yeucau.setKetthuc1("0");
				yeucau.setTongket("0");
				yeucau.setDanhgia("HOCLAI");
				yeucau.setDiemchu("");
				yeucau.setKetthuc2("");
				yeucau.setGhichu("Cấm thi");
				yeucau.setStatus(0);
			} else {
				yeucau.setKiemtragk(kiemtragk);
				if (ketthuc1 != "" && ketthuc2 == "") {
					float dchuyencan = Float.parseFloat(chuyencan);
					float dkiemtragk = Float.parseFloat(kiemtragk);
					float dkethuc1 = Float.parseFloat(ketthuc1);
					yeucau.setKetthuc1(ketthuc1);
					yeucau.setKetthuc2(ketthuc2);
					float dtongket = (float) (dchuyencan * 0.1 + dkiemtragk * 0.2 + dkethuc1 * 0.7);
					yeucau.setTongket(Float.toString(dtongket));
					if (yeucau.getTongket() != "") {
						try {
							if (dtongket >= 4.0) {
								yeucau.setDanhgia("DAT");
							} else if (dtongket < 4.0) {
								yeucau.setDanhgia("THILAI");
							}
							if (dtongket < 4.0) {
								yeucau.setDiemchu("F");
							} else if (dtongket >= 4.0 && dtongket <= 4.9) {
								yeucau.setDiemchu("D");
							} else if (dtongket >= 5.0 && dtongket <= 5.4) {
								yeucau.setDiemchu("D+");
							} else if (dtongket >= 5.5 && dtongket <= 6.4) {
								yeucau.setDiemchu("C");
							} else if (dtongket >= 6.5 && dtongket <= 6.9) {
								yeucau.setDiemchu("C+");
							} else if (dtongket >= 7.0 && dtongket <= 7.9) {
								yeucau.setDiemchu("B");
							} else if (dtongket >= 8.0 && dtongket <= 8.4) {
								yeucau.setDiemchu("B+");
							} else if (dtongket >= 8.5 && dtongket <= 10) {
								yeucau.setDiemchu("A");
							}
						} catch (NumberFormatException e) {
						}
					}
				}
				if (ketthuc2 != "") {
					float dchuyencan = Float.parseFloat(chuyencan);
					float dkiemtragk = Float.parseFloat(kiemtragk);
					float dkethuc2 = Float.parseFloat(ketthuc2);
					float dtongket = (float) (dchuyencan * 0.1 + dkiemtragk * 0.2 + dkethuc2 * 0.7);
					yeucau.setKetthuc1(ketthuc1);
					yeucau.setKetthuc2(ketthuc2);
					yeucau.setTongket(Float.toString(dtongket));
					if (yeucau.getTongket() != "") {
						try {
							if (dtongket >= 4.0) {
								yeucau.setDanhgia("DAT");
							} else if (dtongket < 4.0) {
								yeucau.setDanhgia("THILAI");
							}
							if (dtongket < 4.0) {
								yeucau.setDiemchu("F");
							} else if (dtongket >= 4.0 && dtongket <= 4.9) {
								yeucau.setDiemchu("D");
							} else if (dtongket >= 5.0 && dtongket <= 5.4) {
								yeucau.setDiemchu("D+");
							} else if (dtongket >= 5.5 && dtongket <= 6.4) {
								yeucau.setDiemchu("C");
							} else if (dtongket >= 6.5 && dtongket <= 6.9) {
								yeucau.setDiemchu("C+");
							} else if (dtongket >= 7.0 && dtongket <= 7.9) {
								yeucau.setDiemchu("B");
							} else if (dtongket >= 8.0 && dtongket <= 8.4) {
								yeucau.setDiemchu("B+");
							} else if (dtongket >= 8.5 && dtongket <= 10) {
								yeucau.setDiemchu("A");
							}
						} catch (NumberFormatException e) {
						}
					}
				}
				yeucau.setGhichu(ghichu);
				yeucau.setStatus(0);
				yeucau.setUser(user);
			}
			yeucauDao.addYeuCau(yeucau);
		}
		resp.sendRedirect(
				req.getContextPath() + "/giaovien/diem?lop=" + id_lop + "&mon=" + id_mon + "&hocky=" + id_hocky);
	}
}
