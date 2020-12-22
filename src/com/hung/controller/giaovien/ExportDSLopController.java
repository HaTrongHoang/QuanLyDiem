package com.hung.controller.giaovien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hung.Dao.DiemDao;
import com.hung.Dao.LopDao;
import com.hung.Dao.PhanMonDao;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.PhanMonDaoImpl;
import com.hung.Dao.Impl.TaiKhoanSVDaoImpl;
import com.hung.model.Diem;
import com.hung.model.Lop;
import com.hung.model.PhanMon;
import com.hung.model.SinhVien;

@WebServlet(urlPatterns = "/giaovien/dslop")
public class ExportDSLopController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_lop = Integer.parseInt(req.getParameter("id_lop"));
		int id_hocky = Integer.parseInt(req.getParameter("id_hocky"));

		DiemDao diemDao = new DiemDaoImpl();

		LopDao lopDao = new LopDaoImpl();
		Lop lop = lopDao.getLopId(id_lop);

		String url = "E:\\Java\\QuanLyDiem\\WebContent\\upload\\file\\";

		// ghi file
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(lop.getTenlop());

		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14);
		
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		
		int rownum = 2;
		Cell cell;
		Row row;
		//
		
		row = sheet.createRow(0);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Danh sách lớp "+ lop.getTenlop());
		
		
		row = sheet.createRow(rownum);
		PhanMonDao phanmonDao = new PhanMonDaoImpl();
		List<PhanMon> monList = phanmonDao.getMon(id_lop, id_hocky);
		// msc
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("MSV");
		// hoten
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Họ tên");
		// chuyencan
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Lớp");

		TaiKhoanSVDao svDao = new TaiKhoanSVDaoImpl();
		List<SinhVien> svList = svDao.getSVLop(id_lop);
		int cellnum = 3;
		for (int i = 0; i < monList.size(); i++) {
			PhanMon mon = monList.get(i);
			cell = row.createCell(cellnum + i, CellType.STRING);
			cell.setCellValue(mon.getMon().getTenmon());
		}
		for (SinhVien sinhVien : svList) {
			rownum++;
			row = sheet.createRow(rownum);

			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(sinhVien.getMsv());

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(sinhVien.getHoten());

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(sinhVien.getLop().getTenlop());

			for (int i = 0; i < monList.size(); i++) {
				PhanMon mon = monList.get(i);
				Diem diemSV = diemDao.getSV(sinhVien.getId_sinhvien(), mon.getMon().getId_mon());
				cell = row.createCell(cellnum + i, CellType.STRING);
				if (diemSV.getTongket() == null) {
					cell.setCellValue("0");
				} else {
					cell.setCellValue(diemSV.getTongket());
				}
			}
		}

		File file = new File(url);
		if (!file.exists()) {
			file.mkdir();
		}

		File f = File.createTempFile("diem", ".xlsx", file);
		FileOutputStream outFile = new FileOutputStream(f);
		workbook.write(outFile);
		File input = f.getAbsoluteFile();
		FileInputStream is = new FileInputStream(input);
		resp.setContentType("application/vnd.ms-excel");

		byte[] bytes = new byte[1024];
		int bytesRead;
		while ((bytesRead = is.read(bytes)) != -1) {
			resp.getOutputStream().write(bytes, 0, bytesRead);
		}
		is.close();

		File[] fileList = file.listFiles();
		for (File fileD : fileList) {
			fileD.delete();
			System.out.println(fileD.getName());
		}
	}
}
