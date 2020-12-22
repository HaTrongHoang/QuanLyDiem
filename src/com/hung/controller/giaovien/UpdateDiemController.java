package com.hung.controller.giaovien;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.DiemDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.model.Diem;
import com.hung.model.YeuCau;

@WebServlet(urlPatterns = "/giaovien/updatediem")
public class UpdateDiemController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] id_diem = req.getParameterValues("id_diem");
		String[] camthi = req.getParameterValues("camthi");
		String[] chuyencan = req.getParameterValues("chuyencan");
		String[] kiemtragk = req.getParameterValues("kiemtragk");
		String[] ketthuc1 = req.getParameterValues("ketthuc1");
		String[] ketthuc2 = req.getParameterValues("ketthuc2");
		String[] ghichu = req.getParameterValues("ghichu");
		String[] tongket = req.getParameterValues("tongket");
		String[] status = req.getParameterValues("status");
		String id_lop = req.getParameter("id_lop");
		String id_mon = req.getParameter("id_mon");
		String id_hocky = req.getParameter("id_hocky");
		HttpSession session = req.getSession();
		List<Diem> diemSession = (List<Diem>) session.getAttribute("diemSession");
		DiemDao diemDao = new DiemDaoImpl();

		for (int i = 0; i < id_diem.length; i++) {
			Diem diem = new Diem();
			YeuCau yeucau = new YeuCau();
			int iddiem = Integer.parseInt(id_diem[i]);
			Diem diemCu = diemDao.getId(iddiem);
			int statusDiem = Integer.parseInt(status[i]);
			if (statusDiem == 0) {
				diem.setId_diem(iddiem);
				diem.setChuyencan(chuyencan[i]);
				if (diem.getChuyencan().equals("F")) {
					diem.setKiemtragk("0");
					diem.setGhichu("Cấm thi");
					diem.setStatus(1);
				} else {
					diem.setKiemtragk(kiemtragk[i]);
					diem.setGhichu(ghichu[i]);
					diem.setStatus(1);
				}

				diemDao.updateDiem(diem);
			}

		}
		if (camthi != null) {
			for (int j = 0; j < camthi.length; j++) {
				Diem diemCamThi = new Diem();
				int checkCamThi = Integer.parseInt(camthi[j]);
				diemCamThi.setChuyencan("F");
				diemCamThi.setId_diem(checkCamThi);
				diemCamThi.setKiemtragk("0");
				diemCamThi.setGhichu("Cấm thi");
				diemDao.updateDiem(diemCamThi);

			}
		}
		resp.sendRedirect(req.getContextPath() + "/giaovien/diem?lop=" + id_lop + "&mon=" + id_mon + "&hocky="
				+ id_hocky);
	}
}
