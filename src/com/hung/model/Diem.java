
package com.hung.model;

public class Diem {
	private int id_diem;
	private SinhVien sinhvien;
	private Mon mon;
	private String chuyencan;
	private String kiemtragk;
	private String ketthuc1;
	private String ketthuc2;
	private String tongket;
	private String danhgia;
	private String diemchu;
	private String ghichu;
	private int status;
	private HocKy hocky;

	public int getId_diem() {
		return id_diem;
	}

	public void setId_diem(int id_diem) {
		this.id_diem = id_diem;
	}

	public SinhVien getSinhvien() {
		return sinhvien;
	}

	public void setSinhvien(SinhVien sinhvien) {
		this.sinhvien = sinhvien;
	}

	public Mon getMon() {
		return mon;
	}

	public void setMon(Mon mon) {
		this.mon = mon;
	}

	public String getChuyencan() {
		return chuyencan;
	}

	public void setChuyencan(String chuyencan) {
		this.chuyencan = chuyencan;
	}

	public String getKiemtragk() {
		return kiemtragk;
	}

	public void setKiemtragk(String kiemtragk) {
		this.kiemtragk = kiemtragk;
	}

	public String getKetthuc1() {
		return ketthuc1;
	}

	public void setKetthuc1(String ketthuc1) {
		this.ketthuc1 = ketthuc1;
	}

	public String getKetthuc2() {
		return ketthuc2;
	}

	public void setKetthuc2(String ketthuc2) {
		this.ketthuc2 = ketthuc2;
	}

	public String getDanhgia() {
		return danhgia;
	}

	public void setDanhgia(String danhgia) {
		this.danhgia = danhgia;
	}

	public String getDiemchu() {
		return diemchu;
	}

	public void setDiemchu(String diemchu) {
		this.diemchu = diemchu;
	}

	public String getTongket() {
		return tongket;
	}

	public void setTongket(String tongket) {
		this.tongket = tongket;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public HocKy getHocky() {
		return hocky;
	}

	public void setHocky(HocKy hocky) {
		this.hocky = hocky;
	}

}
