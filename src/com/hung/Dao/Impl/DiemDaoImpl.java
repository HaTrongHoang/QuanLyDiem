package com.hung.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hung.Dao.DiemDao;
import com.hung.Dao.JDBCConnection;
import com.hung.model.Diem;
import com.hung.model.HocKy;
import com.hung.model.Lop;
import com.hung.model.Mon;
import com.hung.model.SinhVien;

public class DiemDaoImpl extends JDBCConnection implements DiemDao {

	@Override
	public List<Diem> getLop(int lop, int mamon, int hocky) {
		List<Diem> diemList = new ArrayList<Diem>();
		final String sql = "SELECT * FROM diem INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon INNER JOIN hocky ON hocky.id_hocky=diem.hocky WHERE lop.id_lop=? AND mon.id_mon=? AND hocky=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, lop);
			preparedStatement.setInt(2, mamon);
			preparedStatement.setInt(3, hocky);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				diemList.add(rowMapper(rs));
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
		return diemList;
	}

	private Diem rowMapper(ResultSet rs) throws SQLException {
		Diem diem = new Diem();
		diem.setId_diem(rs.getInt("id_diem"));

		SinhVien sv = new SinhVien();
		sv.setId_sinhvien(rs.getInt("id_sinhvien"));
		sv.setMsv(rs.getString("msv"));
		sv.setHoten(rs.getString("hoten"));

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

		diem.setChuyencan(rs.getString("chuyencan"));
		diem.setKiemtragk(rs.getString("kiemtragk"));
		diem.setKetthuc1(rs.getString("ketthuc1"));
		diem.setKetthuc2(rs.getString("ketthuc2"));
		diem.setTongket(rs.getString("tongket"));
		diem.setDanhgia(rs.getString("danhgia"));
		diem.setDiemchu(rs.getString("diemchu"));
		diem.setGhichu(rs.getString("ghichu"));
		diem.setStatus(rs.getInt("status"));

		HocKy hocky = new HocKy();
		hocky.setId_hocky(rs.getInt("id_hocky"));
		hocky.setTenhocky(rs.getString("tenhocky"));
		hocky.setStatus_hk(rs.getInt("status_hk"));
		diem.setHocky(hocky);
		return diem;
	}

	@Override
	public void updateDiem(Diem diem) {
		final String sql = "UPDATE diem SET chuyencan=?,kiemtragk=?,ghichu=?,status=? WHERE id_diem=?";
		Connection conn = super.getConnection();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, diem.getChuyencan());
			preparedStatement.setString(2, diem.getKiemtragk());
			preparedStatement.setString(3, diem.getGhichu());
			preparedStatement.setInt(4, diem.getStatus());
			preparedStatement.setInt(5, diem.getId_diem());
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
	public void addDiem(Diem diem) {
		final String sql = "INSERT INTO diem(sinhvien,mon,hocky,status) VALUES(?,?,?,?)";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, diem.getSinhvien().getId_sinhvien());
			preparedStatement.setInt(2, diem.getMon().getId_mon());
			preparedStatement.setInt(3, diem.getHocky().getId_hocky());
			preparedStatement.setInt(4, diem.getStatus());
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
	public Diem getSV(int sinhvien, int mon) {
		final String sql = "SELECT * FROM diem INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon INNER JOIN hocky ON hocky.id_hocky=diem.hocky WHERE diem.sinhvien=? AND diem.mon=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, sinhvien);
			preparedStatement.setInt(2, mon);
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
	public void importExcel(Diem diem) {
		final String sql = "UPDATE diem SET chuyencan=?,kiemtragk=?,ketthuc1=?,ketthuc2=?,danhgia=?,diemchu=?,tongket=?,ghichu=?"
				+ " WHERE sinhvien=? AND mon=?";
		Connection conn = super.getConnection();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, diem.getChuyencan());
			preparedStatement.setString(2, diem.getKiemtragk());
			preparedStatement.setString(3, diem.getKetthuc1());
			preparedStatement.setString(4, diem.getKetthuc2());
			preparedStatement.setString(5, diem.getDanhgia());
			preparedStatement.setString(6, diem.getDiemchu());
			preparedStatement.setString(7, diem.getTongket());
			preparedStatement.setString(8, diem.getGhichu());
			preparedStatement.setInt(9, diem.getSinhvien().getId_sinhvien());
			preparedStatement.setInt(10, diem.getMon().getId_mon());
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
	public List<Diem> getDiem(int sinhvien) {
		List<Diem> diemList = new ArrayList<Diem>();
		final String sql = "SELECT * FROM diem INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon INNER JOIN hocky ON hocky.id_hocky=diem.hocky WHERE sinhvien.id_sinhvien=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, sinhvien);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				diemList.add(rowMapper(rs));
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
		return diemList;
	}

	@Override
	public List<Diem> getThiLai(int mamon) {
		List<Diem> diemList = new ArrayList<Diem>();
		final String sql = "SELECT * FROM diem INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon INNER JOIN hocky ON hocky.id_hocky=diem.hocky WHERE mon.id_mon=? AND danhgia = 'THILAI'";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, mamon);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				diemList.add(rowMapper(rs));
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
		return diemList;
	}

	@Override
	public Diem getId(int id_diem) {
		final String sql = "SELECT * FROM diem INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon INNER JOIN hocky ON hocky.id_hocky=diem.hocky WHERE id_diem=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_diem);
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
	public void importExcelL1(Diem diem) {
		final String sql = "UPDATE diem SET ketthuc1=?,danhgia=?,diemchu=?,tongket=?" + " WHERE sinhvien=? AND mon=?";
		Connection conn = super.getConnection();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, diem.getKetthuc1());
			preparedStatement.setString(2, diem.getDanhgia());
			preparedStatement.setString(3, diem.getDiemchu());
			preparedStatement.setString(4, diem.getTongket());
			preparedStatement.setInt(5, diem.getSinhvien().getId_sinhvien());
			preparedStatement.setInt(6, diem.getMon().getId_mon());
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
	public void importExcelL2(Diem diem) {
		final String sql = "UPDATE diem SET ketthuc2=?,danhgia=?,diemchu=?,tongket=?" + " WHERE sinhvien=? AND mon=?";
		Connection conn = super.getConnection();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, diem.getKetthuc2());
			preparedStatement.setString(2, diem.getDanhgia());
			preparedStatement.setString(3, diem.getDiemchu());
			preparedStatement.setString(4, diem.getTongket());
			preparedStatement.setInt(5, diem.getSinhvien().getId_sinhvien());
			preparedStatement.setInt(6, diem.getMon().getId_mon());
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
	public List<Diem> getDiem(int sinhvien, int hocky) {
		List<Diem> diemList = new ArrayList<Diem>();
		final String sql = "SELECT * FROM diem INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon INNER JOIN hocky ON hocky.id_hocky=diem.hocky WHERE sinhvien.id_sinhvien=? AND diem.hocky=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, sinhvien);
			preparedStatement.setInt(2, hocky);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				diemList.add(rowMapper(rs));
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
		return diemList;
	}

	@Override
	public List<Diem> getThiLai(int mamon, int lop) {
		List<Diem> diemList = new ArrayList<Diem>();
		final String sql = "SELECT * FROM diem INNER JOIN sinhvien ON sinhvien.id_sinhvien=diem.sinhvien INNER JOIN lop ON lop.id_lop=sinhvien.lop INNER JOIN mon ON mon.id_mon=diem.mon INNER JOIN hocky ON hocky.id_hocky=diem.hocky WHERE mon.id_mon=? AND sinhvien.lop.id_lop=? AND danhgia = 'THILAI'";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, mamon);
			preparedStatement.setInt(1, lop);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				diemList.add(rowMapper(rs));
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
		return diemList;
	}

	@Override
	public void updateDiemAdmin(Diem diem) {
		final String sql = "UPDATE diem SET chuyencan=?,kiemtragk=?,ketthuc1=?,ketthuc2=?,danhgia=?,diemchu=?,tongket=?,ghichu=?"
				+ " WHERE id_diem=?";
		Connection conn = super.getConnection();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, diem.getChuyencan());
			preparedStatement.setString(2, diem.getKiemtragk());
			preparedStatement.setString(3, diem.getKetthuc1());
			preparedStatement.setString(4, diem.getKetthuc2());
			preparedStatement.setString(5, diem.getDanhgia());
			preparedStatement.setString(6, diem.getDiemchu());
			preparedStatement.setString(7, diem.getTongket());
			preparedStatement.setString(8, diem.getGhichu());
			preparedStatement.setInt(9, diem.getId_diem());
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
}
