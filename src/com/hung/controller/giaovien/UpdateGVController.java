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

import com.hung.Dao.TaiKhoanGVDao;
import com.hung.Dao.Impl.TaiKhoanGVDaoImpl;
import com.hung.library.MD5Encoder;
import com.hung.model.GiaoVien;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/giaovien/updateGV")
public class UpdateGVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_giaovien = Integer.parseInt(req.getParameter("id_giaovien"));
		TaiKhoanGVDao giaovienDao = new TaiKhoanGVDaoImpl();
		GiaoVien gv = giaovienDao.getIdGV(id_giaovien);
		req.setAttribute("gvUpdate", gv);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/giaovien/updateGV.jsp");
		requestDispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GiaoVien gv = new GiaoVien();
		TaiKhoanGVDao gvDao = new TaiKhoanGVDaoImpl();
		HttpSession sesson = req.getSession();
		int id_giaovien = Integer.parseInt(req.getParameter("id_giaovien"));
		gv.setId_giaovien(id_giaovien);
		// tao doi tuong luu file
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//// set dia chi luu file
		factory.setRepository(new File("E:\\Java\\QuanLyDiem\\WebContent\\upload\\giaovien"));

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
					gv.setHoten(hoten);
				}
				if (item.getFieldName().equals("role")) {
					int role = Integer.parseInt(item.getString("UTF-8"));
					gv.setRole(role);
				}
				if (item.getFieldName().equals("gioitinh")) {
					String gioitinh = item.getString("UTF-8");
					gv.setGioitinh(gioitinh);
				}
				if (item.getFieldName().equals("diachi")) {
					String diachi = item.getString("UTF-8");
					gv.setDiachi(diachi);
				}
				if (item.getFieldName().equals("mgv")) {
					String mgv = item.getString("UTF-8");
					gv.setMgv(mgv);
				}
				if (item.getFieldName().equals("sdt")) {
					String sdt = item.getString();
					gv.setSdt(sdt);
				}
				if (item.getFieldName().equals("ngaysinh")) {
					String ngaysinh = item.getString();
					gv.setNgaysinh(ngaysinh);
				}
				if (item.getFieldName().equals("img")) {
					if (item.getSize() > 0) {
						final String UPLOAD = "E:\\Java\\QuanLyDiem\\WebContent\\upload\\giaovien";
						File UPLOAD_FOLDER = new File(UPLOAD);
						if (!UPLOAD_FOLDER.exists()) {
							UPLOAD_FOLDER.mkdir();
						}
						String name = item.getName();
						File UPLOAD_IMG = new File(UPLOAD + File.separator + name);
						if (!UPLOAD_IMG.exists()) {
							item.write(UPLOAD_IMG);
							gv.setImg(name);
						} else {
							gv.setImg(name);
						}
					}
					else {
						GiaoVien img = gvDao.getIdGV(id_giaovien);
						gv.setImg(img.getImg());
					}
				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gvDao.updateGiaoVien(gv);
		resp.sendRedirect(req.getContextPath() + "/giaovien/tai-khoan-gv?mess=update");

	}
}
