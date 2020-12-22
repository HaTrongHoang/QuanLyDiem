package com.hung.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hung.Dao.JDBCConnection;
import com.hung.Dao.TaiKhoanGVDao;
import com.hung.model.GiaoVien;

public class TaiKhoanGVDaoImpl extends JDBCConnection implements TaiKhoanGVDao {

	@Override
	public List<GiaoVien> getAll(int limit, int offset) {
		List<GiaoVien> giaovienList = new ArrayList<GiaoVien>();
		final String sql = "SELECT * FROM giaovien LIMIT ? OFFSET ?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				giaovienList.add(rowMapper(rs));
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

		return giaovienList;
	}

	private GiaoVien rowMapper(ResultSet rs) throws SQLException {
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
		return giaoVien;
	}

	@Override
	public int totalGiaoVien() {
		final String sql = "SELECT COUNT(*) AS total_giaovien FROM giaovien";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int total = rs.getInt("total_giaovien");
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
	public GiaoVien getGiaoVien(String mgv) {
		final String sql = "SELECT * FROM giaovien WHERE mgv=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, mgv);
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
	public GiaoVien searchGVHoten(String mgv) {
		String sql = "SELECT * FROM giaovien WHERE mgv=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, mgv);
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
	public void addGiaoVien(GiaoVien giaovien) {
		final String sql = "INSERT INTO  giaovien(hoten,mgv,password,ngaysinh,diachi,gioitinh,role,img,sdt)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, giaovien.getHoten());
			preparedStatement.setString(2, giaovien.getMgv());
			preparedStatement.setString(3, giaovien.getPassword());
			preparedStatement.setString(4, giaovien.getNgaysinh());
			preparedStatement.setString(5, giaovien.getDiachi());
			preparedStatement.setString(6, giaovien.getGioitinh());
			preparedStatement.setInt(7, giaovien.getRole());
			preparedStatement.setString(8, giaovien.getImg());
			preparedStatement.setString(9, giaovien.getSdt());
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
	public void updateGiaoVien(GiaoVien giaovien) {
		final String sql = "UPDATE giaovien SET hoten=?,mgv=?,ngaysinh=?,diachi=?,gioitinh=?,role=?,img=?,sdt=? WHERE id_giaovien=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, giaovien.getHoten());
			preparedStatement.setString(2, giaovien.getMgv());
			preparedStatement.setString(3, giaovien.getNgaysinh());
			preparedStatement.setString(4, giaovien.getDiachi());
			preparedStatement.setString(5, giaovien.getGioitinh());
			preparedStatement.setInt(6, giaovien.getRole());
			preparedStatement.setString(7, giaovien.getImg());
			preparedStatement.setString(8, giaovien.getSdt());
			preparedStatement.setInt(9, giaovien.getId_giaovien());
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
	public GiaoVien getIdGV(int id_giaovien) {
		final String sql = "SELECT * FROM giaovien WHERE id_giaovien=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_giaovien);
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
	public void deleteGV(int id_giaovien) {
		final String sql = "DELETE FROM giaovien WHERE id_giaovien = ?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_giaovien);
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
		final String sql = "SELECT COUNT(*) AS total_giaovien FROM giaovien WHERE hoten LIKE ? OR mgv LIKE ?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + key + "%");
			preparedStatement.setString(2, "%" + key + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int total = rs.getInt("total_giaovien");
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
	public List<GiaoVien> searchGV(String key, int limit, int offset) {
		List<GiaoVien> listSearch = new ArrayList<GiaoVien>();
		final String sql = "SELECT * FROM giaovien  WHERE hoten LIKE ? OR mgv LIKE ? LIMIT ? OFFSET ?";
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
	public List<GiaoVien> getAll() {
		List<GiaoVien> giaovienList = new ArrayList<GiaoVien>();
		final String sql = "SELECT * FROM giaovien ";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				giaovienList.add(rowMapper(rs));
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
		return giaovienList;
	}

	@Override
	public void updateMatKhauGV(int id_giaovien, String pass) {
		final String sql = "UPDATE giaovien SET password=? WHERE id_giaovien=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, pass );
			preparedStatement.setInt(2, id_giaovien);
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
