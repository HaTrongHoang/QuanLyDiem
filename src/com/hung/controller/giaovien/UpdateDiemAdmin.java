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
@WebServlet(urlPatterns = "/giaovien/lop/sinhvien/diem/update")
public class UpdateDiemAdmin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id_diem = Integer.parseInt(req.getParameter("id_diemUpdate"));
		String chuyencan = req.getParameter("chuyencanUpdate");
		String kiemtragk = req.getParameter("kiemtragkUpdate");
		String ketthuc1 = req.getParameter("ketthuc1Update");
		String ketthuc2 = req.getParameter("ketthuc2Update");
		String tongket = req.getParameter("tongketUpdate");
		String ghichu = req.getParameter("ghichuUpdate");
		
		DiemDao diemDao = new DiemDaoImpl();
		Diem getSV=diemDao.getId(id_diem);
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
				if (ketthuc1!=""&&ketthuc2 == "") {
					float dchuyencan = Float.parseFloat(chuyencan);
					float dkiemtragk = Float.parseFloat(kiemtragk);
					float dkethuc1 = Float.parseFloat(ketthuc1);
					diem.setKetthuc1(ketthuc1);
					float dtongket = (float) (dchuyencan * 0.1 + dkiemtragk * 0.2 + dkethuc1 * 0.7);
					diem.setTongket(Float.toString(dtongket));
					if (diem.getTongket() != "") {
						try {
							if (dtongket >= 4.0) {
								diem.setDanhgia("DAT");
							} else if (dtongket < 4.0) {
								diem.setDanhgia("THILAI");
							}
							if (dtongket < 4.0) {
								diem.setDiemchu("F");
							} else if (dtongket >= 4.0 && dtongket <= 4.9) {
								diem.setDiemchu("D");
							} else if (dtongket >= 5.0 && dtongket <= 5.4) {
								diem.setDiemchu("D+");
							} else if (dtongket >= 5.5 && dtongket <= 6.4) {
								diem.setDiemchu("C");
							} else if (dtongket >= 6.5 && dtongket <= 6.9) {
								diem.setDiemchu("C+");
							} else if (dtongket >= 7.0 && dtongket <= 7.9) {
								diem.setDiemchu("B");
							} else if (dtongket >= 8.0 && dtongket <= 8.4) {
								diem.setDiemchu("B+");
							} else if (dtongket >= 8.5 && dtongket <= 10) {
								diem.setDiemchu("A");
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
					diem.setKetthuc1(ketthuc1);
					diem.setTongket(Float.toString(dtongket));
					if (diem.getTongket() != "") {
						try {
							if (dtongket >= 4.0) {
								diem.setDanhgia("DAT");
							} else if (dtongket < 4.0) {
								diem.setDanhgia("THILAI");
							}
							if (dtongket < 4.0) {
								diem.setDiemchu("F");
							} else if (dtongket >= 4.0 && dtongket <= 4.9) {
								diem.setDiemchu("D");
							} else if (dtongket >= 5.0 && dtongket <= 5.4) {
								diem.setDiemchu("D+");
							} else if (dtongket >= 5.5 && dtongket <= 6.4) {
								diem.setDiemchu("C");
							} else if (dtongket >= 6.5 && dtongket <= 6.9) {
								diem.setDiemchu("C+");
							} else if (dtongket >= 7.0 && dtongket <= 7.9) {
								diem.setDiemchu("B");
							} else if (dtongket >= 8.0 && dtongket <= 8.4) {
								diem.setDiemchu("B+");
							} else if (dtongket >= 8.5 && dtongket <= 10) {
								diem.setDiemchu("A");
							}
						} catch (NumberFormatException e) {
						}
					}
				}
				diem.setGhichu(ghichu);
			}
			diemDao.updateDiemAdmin(diem);
		
			
		resp.sendRedirect(
				req.getContextPath() + "/giaovien/lop/sinhvien/diem?id_sinhvien="+getSV.getSinhvien().getId_sinhvien());
	}
}
