package com.hung.model;

public class YeuCau {
	private int id_yeucau;
	private Diem diem;
	private String chuyencan;
	private String kiemtragk;
	private String ketthuc1;
	private String ketthuc2;
	private String tongket;
	private String danhgia;
	private String diemchu;
	private String ghichu;
	private int status;
	private GiaoVien user;

	public int getId_yeucau() {
		return id_yeucau;
	}

	public void setId_yeucau(int id_yeucau) {
		this.id_yeucau = id_yeucau;
	}

	public Diem getDiem() {
		return diem;
	}

	public void setDiem(Diem diem) {
		this.diem = diem;
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

	public String getTongket() {
		return tongket;
	}

	public void setTongket(String tongket) {
		this.tongket = tongket;
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

	public GiaoVien getUser() {
		return user;
	}

	public void setUser(GiaoVien user) {
		this.user = user;
	}

}
