package com.hung.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hung.Dao.JDBCConnection;
import com.hung.Dao.PhanMonDao;
import com.hung.model.GiaoVien;
import com.hung.model.HocKy;
import com.hung.model.Lop;
import com.hung.model.Mon;
import com.hung.model.PhanMon;

public class PhanMonDaoImpl extends JDBCConnection implements PhanMonDao {

	@Override
	public void addPhanMon(PhanMon phanMon) {
		final String sql = "INSERT INTO phanmon(lop,giaovien,mon,hocky) VALUES(?,?,?,?)";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, phanMon.getLop().getId_lop());
			preparedStatement.setInt(2, phanMon.getGiaovien().getId_giaovien());
			preparedStatement.setInt(3, phanMon.getMon().getId_mon());
			preparedStatement.setInt(4, phanMon.getHocky().getId_hocky());
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
	public List<PhanMon> getAll(int limit, int offset) {
		List<PhanMon> phanMonList = new ArrayList<PhanMon>();
		final String sql = "SELECT * FROM phanmon INNER JOIN lop ON lop.id_lop=phanmon.lop INNER JOIN mon ON mon.id_mon=phanmon.mon INNER JOIN giaovien ON giaovien.id_giaovien=phanmon.giaovien INNER JOIN hocky ON hocky.id_hocky=phanmon.hocky LIMIT ? OFFSET ?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				phanMonList.add(rowMapper(rs));
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

		return phanMonList;
	}

	private PhanMon rowMapper(ResultSet rs) throws SQLException {
		PhanMon phanMon = new PhanMon();
		phanMon.setId_phanmon(rs.getInt("id_phanmon"));

		Mon mon = new Mon();
		mon.setId_mon(rs.getInt("id_mon"));
		mon.setTenmon(rs.getString("tenmon"));
		mon.setMamon(rs.getString("mamon"));
		phanMon.setMon(mon);

		Lop lop = new Lop();
		lop.setId_lop(rs.getInt("id_lop"));
		lop.setTenlop(rs.getString("tenlop"));
		lop.setKhoa(rs.getString("khoa"));
		phanMon.setLop(lop);

		GiaoVien giaoVien = new GiaoVien();
		giaoVien.setId_giaovien(rs.getInt("id_giaovien"));
		giaoVien.setMgv(rs.getString("mgv"));
		giaoVien.setHoten(rs.getString("hoten"));
		giaoVien.setDiachi(rs.getString("diachi"));
		giaoVien.setNgaysinh(rs.getString("ngaysinh"));
		giaoVien.setGioitinh(rs.getString("gioitinh"));
		giaoVien.setSdt(rs.getString("sdt"));
		giaoVien.setImg(rs.getString("img"));
		giaoVien.setRole(rs.getInt("role"));
		giaoVien.setPassword(rs.getString("password"));
		phanMon.setGiaovien(giaoVien);
		
		HocKy hocky = new HocKy();
		hocky.setId_hocky(rs.getInt("id_hocky"));
		hocky.setTenhocky(rs.getString("tenhocky"));
		hocky.setStatus_hk(rs.getInt("status_hk"));
		phanMon.setHocky(hocky);

		return phanMon;
	}

	@Override
	public int totalPhanMon() {
		final String sql = "SELECT COUNT(*) AS total_phanmon FROM phanmon INNER JOIN lop ON lop.id_lop=phanmon.lop INNER JOIN mon ON mon.id_mon=phanmon.mon INNER JOIN giaovien ON giaovien.id_giaovien=phanmon.giaovien INNER JOIN hocky ON hocky.id_hocky=phanmon.hocky ";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int total = rs.getInt("total_phanmon");
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
	public List<PhanMon> getMonGV(int giaovien,int hocky) {
		List<PhanMon> phanMonList = new ArrayList<PhanMon>();
		final String sql = "SELECT DISTINCT mon.id_mon,mon.tenmon,mon.mamon FROM phanmon INNER JOIN lop ON lop.id_lop=phanmon.lop INNER JOIN mon ON mon.id_mon=phanmon.mon INNER JOIN giaovien ON giaovien.id_giaovien=phanmon.giaovien INNER JOIN hocky ON hocky.id_hocky=phanmon.hocky WHERE phanmon.giaovien=? AND hocky.status_hk=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, giaovien);
			preparedStatement.setInt(2, hocky);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PhanMon phanMon = new PhanMon();
				Mon mon = new Mon();
				mon.setId_mon(rs.getInt("id_mon"));
				mon.setTenmon(rs.getString("tenmon"));
				mon.setMamon(rs.getString("mamon"));
				phanMon.setMon(mon);
				phanMonList.add(phanMon);
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

		return phanMonList;
	}

	@Override
	public List<PhanMon> getLopGV(int giaovien,int hocky) {
		List<PhanMon> phanMonList = new ArrayList<PhanMon>();
		final String sql = "SELECT DISTINCT lop.id_lop,lop.tenlop FROM phanmon INNER JOIN lop ON lop.id_lop=phanmon.lop INNER JOIN mon ON mon.id_mon=phanmon.mon INNER JOIN giaovien ON giaovien.id_giaovien=phanmon.giaovien INNER JOIN hocky ON hocky.id_hocky=phanmon.hocky WHERE phanmon.giaovien=? AND hocky.status_hk=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, giaovien);
			preparedStatement.setInt(2, hocky);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PhanMon phanMon = new PhanMon();
				Lop lop = new Lop();
				lop.setId_lop(rs.getInt("id_lop"));
				lop.setTenlop(rs.getString("tenlop"));
				phanMon.setLop(lop);
				phanMonList.add(phanMon);
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

		return phanMonList;
	}

	@Override
	public void updatePhanMon(PhanMon phanMon) {
		final String sql = "UPDATE phanmon SET lop=?, giaovien=?,mon=? WHERE id_phanmon=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, phanMon.getLop().getId_lop());
			preparedStatement.setInt(2, phanMon.getGiaovien().getId_giaovien());
			preparedStatement.setInt(3, phanMon.getMon().getId_mon());
			preparedStatement.setInt(4, phanMon.getId_phanmon());
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
	public PhanMon getId(int id_phanmon) {
		final String sql = "SELECT * FROM phanmon INNER JOIN lop ON lop.id_lop=phanmon.lop INNER JOIN mon ON mon.id_mon=phanmon.mon INNER JOIN giaovien ON giaovien.id_giaovien=phanmon.giaovien INNER JOIN hocky ON hocky.id_hocky=phanmon.hocky WHERE id_phanmon=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_phanmon);
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
	public void deletePhanMon(int id_phanmon) {
		final String sql = "DELETE FROM phanmon WHERE id_phanmon=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_phanmon);
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
	public List<PhanMon> getLopSV(int lop, int mon,int hocky) {
		List<PhanMon> phanMonList = new ArrayList<PhanMon>();
		final String sql = "SELECT * FROM phanmon INNER JOIN lop ON lop.id_lop=phanmon.lop INNER JOIN mon ON mon.id_mon=phanmon.mon INNER JOIN giaovien ON giaovien.id_giaovien=phanmon.giaovien INNER JOIN hocky ON hocky.id_hocky=phanmon.hocky WHERE lop.id_lop=? AND mon.id_mon=? AND hocky=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, lop);
			preparedStatement.setInt(2, mon);
			preparedStatement.setInt(3, hocky);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				phanMonList.add(rowMapper(rs));
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

		return phanMonList;
	}

	@Override
	public List<PhanMon> getMon(int lop) {
		List<PhanMon> phanMonList = new ArrayList<PhanMon>();
		final String sql = "SELECT  * FROM phanmon INNER JOIN lop ON lop.id_lop=phanmon.lop INNER JOIN mon ON mon.id_mon=phanmon.mon INNER JOIN giaovien ON giaovien.id_giaovien=phanmon.giaovien INNER JOIN hocky ON hocky.id_hocky=phanmon.hocky WHERE phanmon.lop=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, lop);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PhanMon phanMon = new PhanMon();
				Mon mon = new Mon();
				mon.setId_mon(rs.getInt("id_mon"));
				mon.setTenmon(rs.getString("tenmon"));
				mon.setMamon(rs.getString("mamon"));
				phanMon.setMon(mon);
				phanMonList.add(phanMon);
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

		return phanMonList;
	}

	@Override
	public List<PhanMon> getMon(int lop, int hocky) {
		List<PhanMon> phanMonList = new ArrayList<PhanMon>();
		final String sql = "SELECT  * FROM phanmon INNER JOIN lop ON lop.id_lop=phanmon.lop INNER JOIN mon ON mon.id_mon=phanmon.mon INNER JOIN giaovien ON giaovien.id_giaovien=phanmon.giaovien INNER JOIN hocky ON hocky.id_hocky=phanmon.hocky WHERE phanmon.lop=? AND phanmon.hocky=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, lop);
			preparedStatement.setInt(2, hocky);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PhanMon phanMon = new PhanMon();
				Mon mon = new Mon();
				mon.setId_mon(rs.getInt("id_mon"));
				mon.setTenmon(rs.getString("tenmon"));
				mon.setMamon(rs.getString("mamon"));
				phanMon.setMon(mon);
				phanMonList.add(phanMon);
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

		return phanMonList;
	}

}
