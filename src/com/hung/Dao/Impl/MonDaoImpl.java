package com.hung.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hung.Dao.JDBCConnection;
import com.hung.Dao.MonDao;
import com.hung.model.Lop;
import com.hung.model.Mon;

public class MonDaoImpl extends JDBCConnection implements MonDao {

	@Override
	public List<Mon> getAll() {
		List<Mon> monList = new ArrayList<Mon>();
		final String sql = "SELECT * FROM mon ORDER BY tenmon DESC";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				monList.add(rowMapper(rs));
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
		return monList;
	}

	private Mon rowMapper(ResultSet rs) throws SQLException {
		Mon mon = new Mon();
		mon.setId_mon(rs.getInt("id_mon"));
		mon.setTenmon(rs.getString("tenmon"));
		mon.setMamon(rs.getString("mamon"));
		return mon;
	}

	@Override
	public Mon getMonId(int id_mon) {
		final String sql = "SELECT * FROM mon WHERE id_mon=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id_mon);
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
	public Mon getMaMon(String mamon) {
		final String sql = "SELECT * FROM mon WHERE mamon=?";
		Connection conn = super.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, mamon);
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

}
