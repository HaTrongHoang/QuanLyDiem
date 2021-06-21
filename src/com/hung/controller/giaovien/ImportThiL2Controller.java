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
import com.hung.Dao.MonDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.model.Diem;
import com.hung.model.Mon;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/giaovien/importl2")
public class ImportThiL2Controller extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		final int sinhvien = 0;
		final int hocphan = 2;
		final int ketthuc2 = 3;
		DiemDao diemDao = new DiemDaoImpl();
		try {
			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("file")) {
					if (item.getSize() > 0) {
						final String UPLOAD = "C:\\Users\\Windows10\\Desktop\\DoAn\\QuanLyDiem\\WebContent\\upload\\file";
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
										svNull.setMsv("");
										diem.setSinhvien(svNull);
									}
									break;
								case hocphan:
									MonDao monDao = new MonDaoImpl();

									System.out.println("id_mon" + cell.getStringCellValue().toString());
									if (monDao.getMaMon(cell.getStringCellValue().toString().trim()) != null) {
										Mon mon = monDao.getMaMon(cell.getStringCellValue().toString().trim());
										diem.setMon(mon);

									} else {
										Mon monU = new Mon();
										monU.setMamon("");
										diem.setMon(monU);
									}

									break;
								case ketthuc2:
									try {
										diem.setKetthuc2(cell.getStringCellValue().toString());
									} catch (NumberFormatException e) {
										// TODO: handle exception
									}
									break;
								}
							}

							if (!diem.getSinhvien().getMsv().equals("")) {
								if (!diem.getMon().getMamon().equals("")) {
									if (diemDao.getSV(diem.getSinhvien().getId_sinhvien(),
											diem.getMon().getId_mon()) != null) {

										Diem diemSV = diemDao.getSV(diem.getSinhvien().getId_sinhvien(),
												diem.getMon().getId_mon());
										if (diemSV.getChuyencan().equals("F")) {
											diem.setDiemchu("F");
											diem.setKetthuc1("0");
											diem.setDanhgia("HOCLAI");
											diem.setTongket("0");
										} else {
											float chuyencan = Float.parseFloat(diemSV.getChuyencan());
											float kiemtragk = Float.parseFloat(diemSV.getKiemtragk());
											float ketthuc = Float.parseFloat(diem.getKetthuc2());
											float tongket = (float) (chuyencan * 0.1 + kiemtragk * 0.2 + ketthuc * 0.7);
											diem.setTongket(Float.toString(tongket));
											if (diem.getTongket() != "") {
												try {
													if (tongket >= 4.0) {
														diem.setDanhgia("DAT");
													} else if (tongket < 4.0) {
														diem.setDanhgia("THILAI");
													}
													if (tongket < 4.0) {
														diem.setDiemchu("F");
													} else if (tongket >= 4.0 && tongket <= 4.9) {
														diem.setDiemchu("D");
													} else if (tongket >= 5.0 && tongket <= 5.4) {
														diem.setDiemchu("D+");
													} else if (tongket >= 5.5 && tongket <= 6.4) {
														diem.setDiemchu("C");
													} else if (tongket >= 6.5 && tongket <= 6.9) {
														diem.setDiemchu("C+");
													} else if (tongket >= 7.0 && tongket <= 7.9) {
														diem.setDiemchu("B");
													} else if (tongket >= 8.0 && tongket <= 8.4) {
														diem.setDiemchu("B+");
													} else if (tongket >= 8.5 && tongket <= 10) {
														diem.setDiemchu("A");
													}
												} catch (NumberFormatException e) {
												}
											}

										}
										diemDao.importExcelL2(diem);
									}

								}
							}
							wb.close();
							fileInputStream.close();
							UPLOAD_EXCEL.delete();

						}
					}

				}

			}
			resp.sendRedirect(req.getContextPath() + "/giaovien/nhapxuat?mess=success");

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
