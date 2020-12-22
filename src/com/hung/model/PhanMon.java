package com.hung.model;

public class PhanMon {
	private int id_phanmon;
	private Lop lop;
	private GiaoVien giaovien;
	private Mon mon;
	private HocKy hocky;

	public int getId_phanmon() {
		return id_phanmon;
	}

	public void setId_phanmon(int id_phanmon) {
		this.id_phanmon = id_phanmon;
	}

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}

	public GiaoVien getGiaovien() {
		return giaovien;
	}

	public void setGiaovien(GiaoVien giaovien) {
		this.giaovien = giaovien;
	}

	public Mon getMon() {
		return mon;
	}

	public void setMon(Mon mon) {
		this.mon = mon;
	}

	public HocKy getHocky() {
		return hocky;
	}

	public void setHocky(HocKy hocky) {
		this.hocky = hocky;
	}

}
