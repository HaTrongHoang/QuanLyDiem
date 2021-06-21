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
import javax.servlet.http.HttpSession;
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
	protected boolean match(String str) {

		try {
			float str1 = Float.parseFloat(str);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		final int sinhvien = 0;
		final int chuyencan = 2;
		final int kiemtragk = 3;
		final int ketthuc1 = 4;
		final int ketthuc2 = 5;
		String id_lop = req.getParameter("id_lop");
		String id_monURL = req.getParameter("id_mon");
		String id_hocky = req.getParameter("id_hocky");
//		final int ketthuc1 = 4;
//		final int ketthuc2 = 5;
//		final int tongket = 6;
		int id_mon = Integer.parseInt(req.getParameter("id_mon"));
		Mon mon = new Mon();
		mon.setId_mon(id_mon);
		String methodDiem = null;
		String err = "MSV";
		try {
			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("diem")) {
					methodDiem = item.getString("UTF-8");
				}
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
										svNull.setMsv("");
										diem.setSinhvien(svNull);
									}
									break;
								case chuyencan:
									try {
										System.out.println(cell.getStringCellValue());
										diem.setChuyencan(cell.getStringCellValue().toString().toUpperCase());

									} catch (NumberFormatException e) {
										diem.setChuyencan(cell.getStringCellValue().toString());
									}
									break;
								case kiemtragk:
									try {
										diem.setKiemtragk(cell.getStringCellValue().toString());

									} catch (NumberFormatException e) {
										// TODO: handle exception
									}
									break;
								case ketthuc1:
									try {
										System.out.println(cell.getStringCellValue());
										diem.setKetthuc1(cell.getStringCellValue().toString());

									} catch (NumberFormatException e) {

									}
									break;
								case ketthuc2:
									try {
										System.out.println(cell.getStringCellValue());
										diem.setKetthuc2(cell.getStringCellValue().toString());

									} catch (NumberFormatException e) {

									}
									break;
								}

							}

							diem.setStatus(1);
							diem.setMon(mon);

							if (methodDiem.equals("1")) {
								if (diem.getChuyencan() != "F") {
									if (match(diem.getChuyencan()) == true) {
										float ck = Float.parseFloat(diem.getChuyencan());
										if (ck >= 0 && ck <= 10) {
											if (match(diem.getKiemtragk()) == true) {
												float gk = Float.parseFloat(diem.getKiemtragk());
												if (gk >= 0 && gk <= 10) {
													if (!diem.getSinhvien().getMsv().equals("")) {
														DiemDao diemDao = new DiemDaoImpl();
														diemDao.importExcel(diem);
													} else {
														HttpSession session = req.getSession();
														err = err + diem.getSinhvien().getMsv() + " , ";
														session.setAttribute("err", err);
														diem.setChuyencan(null);
													}
												} else {
													HttpSession session = req.getSession();
													err = err + diem.getSinhvien().getMsv() + " , ";
													session.setAttribute("err", err);
													diem.setChuyencan(null);
												}
											} else {
												HttpSession session = req.getSession();

												err = err + diem.getSinhvien().getMsv() + " , ";
												session.setAttribute("err", err);
												diem.setChuyencan(null);
											}
										} else {
											HttpSession session = req.getSession();

											err = err + diem.getSinhvien().getMsv() + " , ";
											session.setAttribute("err", err);
											diem.setChuyencan(null);
										}
									} else {
										HttpSession session = req.getSession();

										err = err + diem.getSinhvien().getMsv() + " , ";
										session.setAttribute("err", err);
										diem.setChuyencan(null);
									}

								}
							}
							if (methodDiem.equals("2")) {
								if (match(diem.getKetthuc1()) == true) {
									float kt1 = Float.parseFloat(diem.getKetthuc1());
									if (kt1 >= 0 && kt1 <= 10) {
										System.out.println("method:" + methodDiem);
										if (!diem.getSinhvien().getMsv().equals("")) {
											DiemDao diemDao = new DiemDaoImpl();
											Diem diemSV = diemDao.getSV(diem.getSinhvien().getId_sinhvien(),
													diem.getMon().getId_mon());
											if (diemSV.getChuyencan().equals("F")) {
												diem.setDiemchu("F");
												diem.setKetthuc1("0");
												diem.setDanhgia("HOCLAI");
												diem.setTongket("0");
											} else {
												float chuyencanD = Float.parseFloat(diemSV.getChuyencan());
												float kiemtragkD = Float.parseFloat(diemSV.getKiemtragk());
												float ketthucD = Float.parseFloat(diem.getKetthuc1());
												float tongketD = (float) (chuyencanD * 0.1 + kiemtragkD * 0.2
														+ ketthucD * 0.7);
												diem.setTongket(Float.toString(tongketD));
												System.out.println("tk:" + diem.getTongket());
												System.out.println("kt1" + diem.getKetthuc1());
												if (diem.getTongket() != "") {
													try {
														if (tongketD >= 4.0) {
															diem.setDanhgia("DAT");
														} else if (tongketD < 4.0) {
															diem.setDanhgia("THILAI");
														}
														if (tongketD < 4.0) {
															diem.setDiemchu("F");
														} else if (tongketD >= 4.0 && tongketD <= 4.9) {
															diem.setDiemchu("D");
														} else if (tongketD >= 5.0 && tongketD <= 5.4) {
															diem.setDiemchu("D+");
														} else if (tongketD >= 5.5 && tongketD <= 6.4) {
															diem.setDiemchu("C");
														} else if (tongketD >= 6.5 && tongketD <= 6.9) {
															diem.setDiemchu("C+");
														} else if (tongketD >= 7.0 && tongketD <= 7.9) {
															diem.setDiemchu("B");
														} else if (tongketD >= 8.0 && tongketD <= 8.4) {
															diem.setDiemchu("B+");
														} else if (tongketD >= 8.5 && tongketD <= 10) {
															diem.setDiemchu("A");
														}
													} catch (NumberFormatException e) {
													}
												}

											}

											diemDao.importExcelL1(diem);
										}
									} else {
										HttpSession session = req.getSession();

										err = err + diem.getSinhvien().getMsv() + " , ";
										session.setAttribute("err", err);
									}
								} else {
									HttpSession session = req.getSession();

									err = err + diem.getSinhvien().getMsv() + " , ";
									session.setAttribute("err", err);
								}

							}
							if (methodDiem.equals("3")) {
								if (match(diem.getKetthuc2()) == true) {
									float kt2 = Float.parseFloat(diem.getKetthuc2());
									if (kt2 >= 0 && kt2 <= 10) {
										System.out.println("method:" + methodDiem);
										if (!diem.getSinhvien().getMsv().equals("")) {
											DiemDao diemDao = new DiemDaoImpl();
											Diem diemSV = diemDao.getSV(diem.getSinhvien().getId_sinhvien(),
													diem.getMon().getId_mon());
											if (diemSV.getChuyencan().equals("F")) {
												diem.setDiemchu("F");
												diem.setKetthuc1("0");
												diem.setDanhgia("HOCLAI");
												diem.setTongket("0");
											} else {
												float chuyencanD = Float.parseFloat(diemSV.getChuyencan());
												float kiemtragkD = Float.parseFloat(diemSV.getKiemtragk());
												float ketthucD = Float.parseFloat(diem.getKetthuc2());
												float tongketD = (float) (chuyencanD * 0.1 + kiemtragkD * 0.2
														+ ketthucD * 0.7);
												diem.setTongket(Float.toString(tongketD));
												System.out.println("tk:" + diem.getTongket());
												System.out.println("kt1" + diem.getKetthuc1());
												if (diem.getTongket() != "") {
													try {
														if (tongketD >= 4.0) {
															diem.setDanhgia("DAT");
														} else if (tongketD < 4.0) {
															diem.setDanhgia("THILAI");
														}
														if (tongketD < 4.0) {
															diem.setDiemchu("F");
														} else if (tongketD >= 4.0 && tongketD < 4.5) {
															diem.setDiemchu("D");
														} else if (tongketD > 4.4 && tongketD < 5.5) {
															diem.setDiemchu("D+");
														} else if (tongketD > 5.4 && tongketD < 6.5) {
															diem.setDiemchu("C");
														} else if (tongketD > 6.4 && tongketD < 7.0) {
															diem.setDiemchu("C+");
														} else if (tongketD > 6.9 && tongketD < 8.0) {
															diem.setDiemchu("B");
														} else if (tongketD > 7.9 && tongketD < 8.5) {
															diem.setDiemchu("B+");
														} else if (tongketD > 8.4 && tongketD <= 10) {
															diem.setDiemchu("A");
														}
													} catch (NumberFormatException e) {
													}
												}

											}
											diemDao.importExcelL2(diem);
										}
									} else {
										HttpSession session = req.getSession();

										err = err + diem.getSinhvien().getMsv() + " , ";
										session.setAttribute("errTK", err);
									}
								} else {
									HttpSession session = req.getSession();

									err = err + diem.getSinhvien().getMsv() + " , ";
									session.setAttribute("err", err);
								}

							}
							if (diem.getChuyencan() != null) {
								if (diem.getChuyencan().equals("F")) {
									DiemDao diemDao = new DiemDaoImpl();
									diem.setKiemtragk("0");
									diem.setKetthuc1("0");
									diem.setTongket("0");
									diem.setDanhgia("HOCLAI");
									diem.setDiemchu("");
									diem.setKetthuc2("");
									diem.setGhichu("Cấm thi");
									diemDao.importExcelL1(diem);
								}
							}

						}

						wb.close();
						fileInputStream.close();
						UPLOAD_EXCEL.delete();
						resp.sendRedirect(req.getContextPath() + "/giaovien/diem?lop=" + id_lop + "&mon=" + id_monURL
								+ "&hocky=" + id_hocky + "&mess=success");

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
