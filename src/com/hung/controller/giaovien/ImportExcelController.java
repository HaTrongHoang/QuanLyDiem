package com.hung.controller.giaovien;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

import com.hung.Dao.DiemDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.model.Diem;
import com.hung.model.Mon;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/giaovien/import")
public class ImportExcelController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		final int sinhvien = 0;
		final int chuyencan = 2;
		final int kiemtragk = 3;

		String id_lop = req.getParameter("id_lop");
		String id_monURL = req.getParameter("id_mon");
		String id_hocky = req.getParameter("id_hocky");
//		final int ketthuc1 = 4;
//		final int ketthuc2 = 5;
//		final int tongket = 6;
		int id_mon = Integer.parseInt(req.getParameter("id_mon"));
		Mon mon = new Mon();
		mon.setId_mon(id_mon);
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
							Diem diem = new Diem();
							while (cellIterator.hasNext()) {

								Cell cell = cellIterator.next();
								cell.setCellType(CellType.STRING);
								int columnIndex = cell.getColumnIndex();
								switch (columnIndex) {
								case sinhvien:
									System.out.println(cell.getStringCellValue());
									String msv = cell.getStringCellValue().toString();
									TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();

									if (svDao.getSV(msv) != null) {
										SinhVien sv = svDao.getSV(msv);
										diem.setSinhvien(sv);

									} else {
										SinhVien svNull = new SinhVien();
										diem.setSinhvien(svNull);
									}
									break;
								case chuyencan:
									try {
										System.out.println(cell.getStringCellValue());
										diem.setChuyencan(cell.getStringCellValue().toString());

									} catch (NumberFormatException e) {
										// TODO: handle exception
									}
									break;
								case kiemtragk:
									try {
										diem.setKiemtragk(cell.getStringCellValue().toString());

									} catch (NumberFormatException e) {
										// TODO: handle exception
									}
									break;
//								case ketthuc1:
//									try {
//										diem.setKetthuc1(cell.getStringCellValue().toString());
//									} catch (NumberFormatException e) {
//										// TODO: handle exception
//									}
//
//									break;
//								case ketthuc2:
//									try {
//										float diemketthuc1 = Float.parseFloat(diem.getKetthuc1());
//										if (diemketthuc1 < 4.0) {
//											diem.setKetthuc2(cell.getStringCellValue().toString());
//										}
//									} catch (NumberFormatException e) {
//										// TODO: handle exception
//									}
//
//									break;
//								case tongket:
//									diem.setTongket(cell.getStringCellValue().toString());
//									if (diem.getTongket() != "") {
//										try {
//											float tkhp = Float.parseFloat(diem.getTongket());
//											if (tkhp >= 4.0) {
//												diem.setDanhgia("DAT");
//											} else if (tkhp < 4.0) {
//												diem.setDanhgia("THILAI");
//											}
//											if (tkhp < 4.0) {
//												diem.setDiemchu("F");
//											} else if (tkhp >= 4.0 && tkhp <= 4.9) {
//												diem.setDiemchu("D");
//											} else if (tkhp >= 5.0 && tkhp <= 5.4) {
//												diem.setDiemchu("D+");
//											} else if (tkhp >= 5.5 && tkhp <= 6.4) {
//												diem.setDiemchu("C");
//											} else if (tkhp >= 6.5 && tkhp <= 6.9) {
//												diem.setDiemchu("C+");
//											} else if (tkhp >= 7.0 && tkhp <= 7.9) {
//												diem.setDiemchu("B");
//											} else if (tkhp >= 8.0 && tkhp <= 8.4) {
//												diem.setDiemchu("B+");
//											} else if (tkhp >= 8.5 && tkhp <= 10) {
//												diem.setDiemchu("A");
//											}
//										} catch (NumberFormatException e) {
//										}
//									}
//									break;
								}

							}
							if (diem.getChuyencan().equals("F")) {
								diem.setKiemtragk("0");
								diem.setKetthuc1("0");
								diem.setTongket("0");
								diem.setDanhgia("HOCLAI");
								diem.setDiemchu("");
								diem.setKetthuc2("");
								diem.setGhichu("Cấm thi");
							}
							diem.setStatus(1);
							diem.setMon(mon);
							if (diem.getSinhvien().getMsv() != null) {
								DiemDao diemDao = new DiemDaoImpl();
								diemDao.importExcel(diem);
							}

						}
						wb.close();
						fileInputStream.close();
						UPLOAD_EXCEL.delete();
						resp.sendRedirect(req.getContextPath() + "/giaovien/diem?lop=" + id_lop + "&mon=" + id_monURL +"&hocky="+ id_hocky);

					}
				}

			}
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
