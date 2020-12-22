package com.hung.controller.giaovien;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hung.Dao.LopDao;
import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.library.MD5Encoder;
import com.hung.model.GiaoVien;
import com.hung.model.Lop;
import com.hung.model.SinhVien;
@WebServlet(urlPatterns = "/giaovien/addSV")
public class AddTaiKhoanSVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LopDao lopDao =new LopDaoImpl();
		List<Lop> lop=lopDao.getAll();
		req.setAttribute("lopList", lop);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/addSV.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SinhVien sv = new SinhVien();
		TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();
		HttpSession sesson = req.getSession();

		// tao doi tuong luu file
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//// set dia chi luu file
		factory.setRepository(new File("E:\\Java\\QuanLyDiem\\WebContent\\upload\\sinhvien"));

		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		// doc request tu client gui len trong form upload
		try {
			List<FileItem> itemList = servletFileUpload.parseRequest(req);
			Iterator<FileItem> iteml = itemList.iterator();

			while (iteml.hasNext()) {
				FileItem item = iteml.next();
				if (item.getFieldName().equals("hoten")) {
					String hoten = item.getString("UTF-8");
					System.out.println(hoten);
					sv.setHoten(hoten);
				}
				if (item.getFieldName().equals("msv")) {
					String msv = item.getString();
					SinhVien svList = svDao.getSV(msv);
					if (svList == null) {
						sv.setMsv(msv);
					} else {

					}

				}
				if (item.getFieldName().equals("lop")) {
					int id_lop = Integer.parseInt(item.getString("UTF-8"));
					LopDao lopDao=new LopDaoImpl();
					Lop lop=lopDao.getLopId(id_lop);
					sv.setLop(lop);
				}
				if (item.getFieldName().equals("gioitinh")) {
					String gioitinh = item.getString("UTF-8");
					sv.setGioitinh(gioitinh);
				}
				if (item.getFieldName().equals("diachi")) {
					String diachi = item.getString("UTF-8");
					sv.setDiachi(diachi);
				}
				if (item.getFieldName().equals("sdt")) {
					String sdt = item.getString();
					sv.setSdt(sdt);
				}
				if (item.getFieldName().equals("ngaysinh")) {
					String ngaysinh = item.getString();
					sv.setNgaysinh(ngaysinh);
				}
				if (item.getFieldName().equals("img")) {
					if (item.getSize() > 0) {
						final String UPLOAD = "E:\\Java\\QuanLyDiem\\WebContent\\upload\\sinhvien";
						File UPLOAD_FOLDER = new File(UPLOAD);
						if (!UPLOAD_FOLDER.exists()) {
							UPLOAD_FOLDER.mkdir();
						}
						String name = item.getName();
						File UPLOAD_IMG = new File(UPLOAD + File.separator + name);
						if (!UPLOAD_IMG.exists()) {
							item.write(UPLOAD_IMG);
							sv.setImg(name);
						} else {
							sv.setImg(name);
						}
					}
				}
			}
			sv.setPassword(MD5Encoder.md5Encoder("12345678"));

			if (sv.getMsv() == null) {
				resp.sendRedirect(req.getContextPath() + "/giaovien/addSV?mess=username");
				System.out.println("username ton tai");
			} else {
				svDao.addSinhVien(sv);
				resp.sendRedirect(req.getContextPath() + "/giaovien/taikhoan-sv?mess=success");
				System.out.println("them thanh cong" + sv.getMsv());
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
