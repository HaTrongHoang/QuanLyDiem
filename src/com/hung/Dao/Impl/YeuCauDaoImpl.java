package com.hung.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hung.Dao.JDBCConnection;
import com.hung.Dao.YeuCauDao;
import com.hung.model.Diem;
import com.hung.model.GiaoVien;
import com.hung.model.Lop;
import com.hung.model.Mon;
import com.hung.model.SinhVien;
import com.hung.model.YeuCau;

public class YeuCauDaoImpl extends JDBCConnection implements YeuCauDao {

	@Override
	public List<YeuCau> getYeuCau(int limit, int offset) {
		List<YeuCau> yeucauList = new ArrayList<YeuCau>();
		final String sql = "SELECT * FROM yeucau INNER JOIN diem ON yeucau.diem=diem.id_diem INNER JOIN giaovien ON giaovien.id_giaovien=yeucau.user INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon ORDER BY id_yeucau ASC LIMIT ? OFFSET ? ";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				yeucauList.add(rowMapper(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return yeucauList;
	}

	private YeuCau rowMapper(ResultSet rs) throws SQLException {
		YeuCau yeucau = new YeuCau();
		yeucau.setId_yeucau(rs.getInt("id_yeucau"));

		Diem diem = new Diem();
		diem.setId_diem(rs.getInt("id_diem"));

		SinhVien sv = new SinhVien();
		sv.setId_sinhvien(rs.getInt("id_sinhvien"));
		sv.setMsv(rs.getString("msv"));
		sv.setHoten(rs.getString("sinhvien.hoten"));

		Lop lop = new Lop();
		lop.setId_lop(rs.getInt("id_lop"));
		lop.setTenlop(rs.getString("tenlop"));
		lop.setKhoa(rs.getString("khoa"));
		sv.setLop(lop);
		diem.setSinhvien(sv);

		Mon mon = new Mon();
		mon.setId_mon(rs.getInt("id_mon"));
		mon.setMamon(rs.getString("mamon"));
		mon.setTenmon(rs.getString("tenmon"));
		diem.setMon(mon);

		diem.setChuyencan(rs.getString("diem.chuyencan"));
		diem.setKiemtragk(rs.getString("diem.kiemtragk"));
		diem.setKetthuc1(rs.getString("diem.ketthuc1"));
		diem.setKetthuc2(rs.getString("diem.ketthuc2"));
		diem.setTongket(rs.getString("diem.tongket"));
		diem.setDanhgia(rs.getString("diem.danhgia"));
		diem.setDiemchu(rs.getString("diem.diemchu"));
		diem.setGhichu(rs.getString("diem.ghichu"));
		diem.setStatus(rs.getInt("diem.status"));
		yeucau.setDiem(diem);

		yeucau.setChuyencan(rs.getString("yeucau.chuyencan"));
		yeucau.setKiemtragk(rs.getString("yeucau.kiemtragk"));
		yeucau.setKetthuc1(rs.getString("yeucau.ketthuc1"));
		yeucau.setKetthuc2(rs.getString("yeucau.ketthuc2"));
		yeucau.setTongket(rs.getString("yeucau.tongket"));
		yeucau.setDanhgia(rs.getString("yeucau.danhgia"));
		yeucau.setDiemchu(rs.getString("yeucau.diemchu"));
		yeucau.setGhichu(rs.getString("yeucau.ghichu"));

		GiaoVien giaoVien = new GiaoVien();
		giaoVien.setId_giaovien(rs.getInt("id_giaovien"));
		giaoVien.setMgv(rs.getString("mgv"));
		giaoVien.setHoten(rs.getString("giaovien.hoten"));

		yeucau.setUser(giaoVien);
		yeucau.setStatus(rs.getInt("yeucau.status"));
		return yeucau;
	}

	@Override
	public void addYeuCau(YeuCau yeucau) {
		final String sql = "INSERT INTO yeucau(diem,chuyencan,kiemtragk,ketthuc1,ketthuc2,tongket,danhgia,diemchu,ghichu,user,status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, yeucau.getDiem().getId_diem());
			preparedStatement.setString(2, yeucau.getChuyencan());
			preparedStatement.setString(3, yeucau.getKiemtragk());
			preparedStatement.setString(4, yeucau.getKetthuc1());
			preparedStatement.setString(5, yeucau.getKetthuc2());
			preparedStatement.setString(6, yeucau.getTongket());
			preparedStatement.setString(7, yeucau.getDanhgia());
			preparedStatement.setString(8, yeucau.getDiemchu());
			preparedStatement.setString(9, yeucau.getGhichu());
			preparedStatement.setInt(10, yeucau.getUser().getId_giaovien());
			preparedStatement.setInt(11, yeucau.getStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public int totalYeuCau() {
		final String sql = "SELECT COUNT(*) AS total_yeucau FROM yeucau";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int total = rs.getInt("total_yeucau");
				return total;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public YeuCau getId(int id_yeucau) {
		final String sql = "SELECT * FROM yeucau INNER JOIN diem ON yeucau.diem=diem.id_diem INNER JOIN giaovien ON giaovien.id_giaovien=yeucau.user INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon WHERE id_yeucau=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_yeucau);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return rowMapper(rs);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public void updateYeuCau(int status, int id_yeucau) {
		final String sql = "UPDATE yeucau SET status=? WHERE id_yeucau=?";
		Connection conn = super.getConnection();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, status);
			preparedStatement.setInt(2, id_yeucau);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteYeuCau(int id_yeucau) {
		final String sql = "DELETE FROM yeucau WHERE id_yeucau=? ";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_yeucau);
			preparedStatement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
