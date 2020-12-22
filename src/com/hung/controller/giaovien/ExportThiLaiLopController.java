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
import com.hung.Dao.MonDao;
import com.hung.Dao.Impl.DiemDaoImpl;
import com.hung.Dao.Impl.LopDaoImpl;
import com.hung.Dao.Impl.MonDaoImpl;
import com.hung.model.Diem;
import com.hung.model.Lop;
import com.hung.model.Mon;
@WebServlet(urlPatterns = "/giaovien/exportThiLaiLop")
public class ExportThiLaiLopController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_mon = Integer.parseInt(req.getParameter("id_mon"));
		int id_lop = Integer.parseInt(req.getParameter("id_lop"));

		DiemDao diemDao = new DiemDaoImpl();
		List<Diem> listDiem = diemDao.getThiLai(id_mon,id_lop);

		MonDao mondao = new MonDaoImpl();
		Mon mon = mondao.getMonId(id_mon);
		
		LopDao lopDao=new LopDaoImpl();
		Lop lop=lopDao.getLopId(id_lop);
		String url = "E:\\Java\\QuanLyDiem\\WebContent\\upload\\file\\";
		// ghi file
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Diem");

		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14);

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);

		int rownum = 3;
		Cell cell;
		Row row;

		row = sheet.createRow(0);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Danh sách thi lại lớp: "+lop.getTenlop());

		row = sheet.createRow(1);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Môn:" + mon.getTenmon());

		//
		row = sheet.createRow(2);

		// msc
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("MSV");
		// hoten
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Họ tên");
		// lop
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Lớp");
		// chuyencan
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Chuyên cần");
		// kta gk
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Kiểm tra GK");
		// ket thuc 1
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Kết thúc lần 1");
		// ket thuc 2
		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Kết thúc lần 2");
		// tong ket
		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("Tổng kết");
		// danh gia
		cell = row.createCell(8, CellType.STRING);
		cell.setCellValue("Đánh giá");
		// điểm chữ
		cell = row.createCell(9, CellType.STRING);
		cell.setCellValue("Điểm chữ");

		for (Diem diem : listDiem) {
			rownum++;
			row = sheet.createRow(rownum);

			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(diem.getSinhvien().getMsv());

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(diem.getSinhvien().getHoten());

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(diem.getSinhvien().getLop().getTenlop());

			cell = row.createCell(3, CellType.NUMERIC);
			cell.setCellValue(diem.getChuyencan());

			cell = row.createCell(4, CellType.NUMERIC);
			cell.setCellValue(diem.getKiemtragk());

			cell = row.createCell(5, CellType.NUMERIC);
			cell.setCellValue(diem.getKetthuc1());

			cell = row.createCell(6, CellType.NUMERIC);
			cell.setCellValue(diem.getKetthuc2());

			cell = row.createCell(7, CellType.NUMERIC);
			cell.setCellValue(diem.getTongket());

			cell = row.createCell(8, CellType.STRING);
			cell.setCellValue(diem.getDanhgia());

			cell = row.createCell(9, CellType.STRING);
			cell.setCellValue(diem.getDiemchu());

		}
		File file = new File(url);
		if (!file.exists()) {
			file.mkdir();
		}

		File f = File.createTempFile("thilai", ".xlsx", file);
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
