package com.hung.controller.giaovien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet(urlPatterns = "/giaovien/exportSV")
public class FileMauSVController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "E:\\Java\\QuanLyDiem\\WebContent\\upload\\file\\";
		// ghi file
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Diem");

		int rownum = 0;
		Cell cell;
		Row row;
		//
		row = sheet.createRow(rownum);

		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Họ tên");

		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("MSV");

		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Lớp");

		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Địa chỉ");

		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Ngày sinh");

		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("SDT");
		File file = new File(url);
		if (!file.exists()) {
			file.mkdir();
		}

		File f = File.createTempFile("taikhoan", ".xlsx", file);
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
