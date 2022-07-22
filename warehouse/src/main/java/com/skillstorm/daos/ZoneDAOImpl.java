package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.skillstorm.conf.WarehouseDBCreds;
import com.skillstorm.models.Zone;

public class ZoneDAOImpl implements ZoneDAO{

	@Override
	public LinkedList<Zone> findAll() {
String sql = "SELECT * FROM zone";
		
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			LinkedList<Zone> zones = new LinkedList<>();
			while(rs.next()) {
				Zone zone = mapRowToZone(rs);
				zones.add(zone);
			}
			return zones;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	@Override
	public Zone findById(int id) {
		
		String sql = "SELECT * FROM zone WHERE ZoneId =  + ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Zone zone = mapRowToZone(rs);
				return zone;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Zone findByName(String name) {
		String sql = "SELECT * FROM zone WHERE ZoneName = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Zone zone = mapRowToZone(rs);
				return zone;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Zone save(Zone zone) {
		String sql = "INSERT INTO zone (ZoneName, WarehouseId) VALUES (?, ?)";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, zone.getZoneName());
			ps.setInt(2,  zone.getWarehouseId());
			
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = ps.getGeneratedKeys();
				if (keys.next()) {
					int key = keys.getInt(1);
					zone.setZoneId(key);
					conn.commit();
					return zone;
				} else {
					conn.rollback();
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Zone zone) {
		String sql = "UPDATE zone SET ZoneId = ?, ZoneName = ?, WarehouseId= ? WHERE ItemId = ?;";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, zone.getZoneId());
			ps.setString(2, zone.getZoneName());
			ps.setInt(3,  zone.getWarehouseId());
			
			
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				conn.commit();
				
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Zone zone) {
		String sql = "DELETE FROM zone WHERE ZoneId = ?";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  zone.getZoneId());
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 0) {
				conn.commit();
				
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM zone WHERE ZoneId = ?";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 0) {
				conn.commit();
				
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMany(int[] ids) {
		String sql = "DELETE FROM zone WHERE ZoneId = ?";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			for(int id : ids) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1,  id);
				
				int rowsAffected = ps.executeUpdate();
				if(rowsAffected != 0) {
					conn.commit();
					
				} else {
					conn.rollback();
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int getItemCountInZone(int zoneId) {
		String sql = "SELECT COUNT(ItemId) FROM item WHERE ZoneId = ?";
		
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  zoneId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(ItemId)");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -1;
		
		
	}	
	
	
	private static Zone mapRowToZone (ResultSet rs) {
		Zone zone = new Zone();
		try {
			zone.setZoneId(rs.getInt("ZoneId"));
			zone.setZoneName(rs.getString("ZoneName"));
			zone.setWarehouseId(rs.getInt("WarehouseId"));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return zone;
	}


	

}
