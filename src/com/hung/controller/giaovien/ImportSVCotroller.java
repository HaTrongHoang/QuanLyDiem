package com.hung.controller.giaovien;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

@WebServlet(urlPatterns = "/giaovien/importSV")
public class ImportSVCotroller extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		final int hoten = 0;
		final int msv = 1;
		final int lop = 2;
		final int diachi = 3;
		final int ngaysinh = 4;
		final int sdt = 5;
		TaiKhoanSVDao tkDao = new TaiKhoanSVDaoImpl();
		String errTK = "";
		try {
			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("file")) {
					if (item.getSize() > 0) {
						final String UPLOAD = "E:\\Java\\QuanLyDiem\\WebContent\\upload\\file";
						File UPLOAD_FILE = new File(UPLOAD);
						if (!UPLOAD_FILE.exists()) {
							UPLOAD_FILE.mkdir();
						}
						String name = item.getName();
						File UPLOAD_EXCEL = new File(UPLOAD + File.separator + name);
						if (!UPLOAD_EXCEL.exists()) {
							item.write(UPLOAD_EXCEL);

						}

						// get file
						FileInputStream fileInputStream = new FileInputStream(UPLOAD_EXCEL);

						// get workbook từ excel 2007 trở lên
						Workbook wb = new XSSFWorkbook(UPLOAD_EXCEL);

						// get sheet
						Sheet sheet = wb.getSheetAt(0);

						// get all row
						Iterator<Row> iterator = sheet.iterator();
						while (iterator.hasNext()) {
							Row nextRow = iterator.next();
							// get all cell
							Iterator<Cell> cellIterator = nextRow.cellIterator();
							// doc cell
							SinhVien sv = new SinhVien();
							while (cellIterator.hasNext()) {

								Cell cell = cellIterator.next();
								cell.setCellType(CellType.STRING);
								int columnIndex = cell.getColumnIndex();
								switch (columnIndex) {
								case hoten:
									System.out.println(cell.getStringCellValue());
									sv.setHoten(cell.getStringCellValue().toString());
									break;
								case msv:
									String ma = cell.getStringCellValue().toString();
									sv.setMsv(ma);

									break;
								case diachi:
									sv.setDiachi(cell.getStringCellValue().toString());
									break;
								case ngaysinh:
									sv.setNgaysinh(cell.getStringCellValue().toString());
									break;

								case sdt:

									sv.setSdt(cell.getStringCellValue().toString());

									break;
								case lop:

									LopDao lopDao = new LopDaoImpl();
									String lopStr = cell.getStringCellValue().toString();
									if (lopDao.getTenLop(lopStr.trim()) != null) {
										Lop lopSV = lopDao.getTenLop(lopStr);
										sv.setLop(lopSV);
										System.out.println("lop:" + sv.getLop().getId_lop());
									} else {
										Lop lopNull = new Lop();
										lopNull.setTenlop(null);
										sv.setLop(lopNull);
									}
									break;
								}
							}
							sv.setPassword(MD5Encoder.md5Encoder("12345678"));
							if (!sv.getMsv().equals("MSV") && sv.getLop().getTenlop() != null) {
								SinhVien svList = tkDao.getSV(sv.getMsv());
								if (svList == null) {
									tkDao.addSinhVien(sv);

								} else {
									HttpSession session = req.getSession();
									errTK = errTK + sv.getMsv().toString() + " , ";
									session.setAttribute("errTK", errTK);
								}
							} else {
								HttpSession session = req.getSession();
								errTK = errTK + sv.getMsv() + " , ";
								session.setAttribute("errTK", errTK);
							}

						}

						wb.close();
						fileInputStream.close();
						UPLOAD_EXCEL.delete();
					}

				}

			}
			resp.sendRedirect(req.getContextPath() + "/giaovien/taikhoan-sv?mess=success");

		} catch (

		FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
