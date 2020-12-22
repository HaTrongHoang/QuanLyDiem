package com.hung.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hung.Dao.JDBCConnection;
import com.hung.Dao.TaiKhoanSVDao;
import com.hung.model.GiaoVien;
import com.hung.model.Lop;
import com.hung.model.SinhVien;

public class TaiKhoanSVDaoImpl extends JDBCConnection implements TaiKhoanSVDao {

	@Override
	public List<SinhVien> getAll(int limit, int offset) {
		List<SinhVien> sinhvienList = new ArrayList<SinhVien>();
		final String sql = "SELECT * FROM sinhvien INNER JOIN lop ON sinhvien.lop=lop.id_lop LIMIT ? OFFSET ?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				sinhvienList.add(rowMapper(rs));
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

		return sinhvienList;
	}

	private SinhVien rowMapper(ResultSet rs) throws SQLException {
		SinhVien sinhvien = new SinhVien();
		sinhvien.setId_sinhvien(rs.getInt("id_sinhvien"));
		sinhvien.setMsv(rs.getString("msv"));
		sinhvien.setHoten(rs.getString("hoten"));
		sinhvien.setDiachi(rs.getString("diachi"));
		sinhvien.setNgaysinh(rs.getString("ngaysinh"));
		sinhvien.setGioitinh(rs.getString("gioitinh"));
		sinhvien.setSdt(rs.getString("sdt"));
		sinhvien.setImg(rs.getString("img"));
		sinhvien.setPassword(rs.getString("password"));
		Lop lop = new Lop();
		lop.setId_lop(rs.getInt("id_lop"));
		lop.setTenlop(rs.getString("tenlop"));
		lop.setKhoa(rs.getString("khoa"));
		sinhvien.setLop(lop);
		return sinhvien;
	}

	@Override
	public int totalSinhVien() {
		final String sql = "SELECT COUNT(*) AS total_sinhvien FROM sinhvien INNER JOIN lop ON sinhvien.lop=lop.id_lop";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int total = rs.getInt("total_sinhvien");
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
	public void addSinhVien(SinhVien sinhvien) {
		final String sql = "INSERT INTO  sinhvien(hoten,msv,password,ngaysinh,diachi,gioitinh,lop,img,sdt)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, sinhvien.getHoten());
			preparedStatement.setString(2, sinhvien.getMsv());
			preparedStatement.setString(3, sinhvien.getPassword());
			preparedStatement.setString(4, sinhvien.getNgaysinh());
			preparedStatement.setString(5, sinhvien.getDiachi());
			preparedStatement.setString(6, sinhvien.getGioitinh());
			preparedStatement.setInt(7, sinhvien.getLop().getId_lop());
			preparedStatement.setString(8, sinhvien.getImg());
			preparedStatement.setString(9, sinhvien.getSdt());
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

	@Override
	public SinhVien getSV(String msv) {
		String sql = "SELECT * FROM sinhvien INNER JOIN lop ON sinhvien.lop=lop.id_lop WHERE msv=? ";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, msv);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return rowMapper(rs);
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

		return null;
	}

	@Override
	public void updateSinhVien(SinhVien sinhvien) {
		final String sql = "UPDATE sinhvien SET hoten=?,msv=?,ngaysinh=?,diachi=?,gioitinh=?,lop=?,img=?,sdt=? WHERE id_sinhvien=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, sinhvien.getHoten());
			preparedStatement.setString(2, sinhvien.getMsv());
			preparedStatement.setString(3, sinhvien.getNgaysinh());
			preparedStatement.setString(4, sinhvien.getDiachi());
			preparedStatement.setString(5, sinhvien.getGioitinh());
			preparedStatement.setInt(6, sinhvien.getLop().getId_lop());
			preparedStatement.setString(7, sinhvien.getImg());
			preparedStatement.setString(8, sinhvien.getSdt());
			preparedStatement.setInt(9, sinhvien.getId_sinhvien());
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

	@Override
	public SinhVien getIdSV(int id_sinhvien) {
		final String sql = "SELECT * FROM sinhvien INNER JOIN lop ON sinhvien.lop=lop.id_lop WHERE id_sinhvien=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_sinhvien);
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
	public void deleteSV(int id_sinhvien) {
		final String sql = "DELETE FROM sinhvien WHERE id_sinhvien = ?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_sinhvien);
			preparedStatement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			System.out.println("loi delete" + e);
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
	public int totalSearch(String key) {
		final String sql = "SELECT COUNT(*) AS total_sinhvien FROM sinhvien INNER JOIN lop ON sinhvien.lop=lop.id_lop WHERE hoten LIKE ? OR msv LIKE ?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + key + "%");
			preparedStatement.setString(2, "%" + key + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int total = rs.getInt("total_sinhvien");
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
	public List<SinhVien> searchSV(String key, int limit, int offset) {
		List<SinhVien> listSearch = new ArrayList<SinhVien>();
		final String sql = "SELECT * FROM sinhvien INNER JOIN lop ON sinhvien.lop=lop.id_lop WHERE hoten LIKE ? OR msv LIKE ? LIMIT ? OFFSET ?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + key + "%");
			preparedStatement.setString(2, "%" + key + "%");
			preparedStatement.setInt(3, limit);
			preparedStatement.setInt(4, offset);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				listSearch.add(rowMapper(rs));
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

		return listSearch;
	}

	@Override
	public List<SinhVien> getSVLop(int lop) {
		List<SinhVien> listSVLop = new ArrayList<SinhVien>();
		final String sql = "SELECT * FROM sinhvien INNER JOIN lop ON sinhvien.lop=lop.id_lop WHERE lop.id_lop=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, lop);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				listSVLop.add(rowMapper(rs));
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

		return listSVLop;
	}

	@Override
	public void updateMatKhauSV(int id_sinhvien, String pass) {
		final String sql = "UPDATE sinhvien SET password=? WHERE id_sinhvien=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, pass);
			preparedStatement.setInt(2, id_sinhvien);
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
