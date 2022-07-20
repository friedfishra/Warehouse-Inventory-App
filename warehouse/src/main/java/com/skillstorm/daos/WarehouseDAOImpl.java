package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.skillstorm.conf.WarehouseDBCreds;
import com.skillstorm.models.Warehouse;

public class WarehouseDAOImpl implements WarehouseDAO{

	@Override
	public LinkedList<Warehouse> findAll() {
		String sql = "SELECT * FROM warehouse";
		
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			LinkedList<Warehouse> warehouses = new LinkedList<>();
			while(rs.next()) {
				Warehouse warehouse = mapRowToWarehouse(rs);
				warehouses.add(warehouse);
			}
			return warehouses;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	@Override
	public Warehouse findById(int id) {
		String sql = "SELECT * FROM warehouse WHERE WarehouseId =  + ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Warehouse warehouse = mapRowToWarehouse(rs);
				return warehouse;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Warehouse findByName(String name) {
		String sql = "SELECT * FROM warehouse WHERE WarehouseId =  + ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Warehouse warehouse = mapRowToWarehouse(rs);
				return warehouse;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Warehouse save(Warehouse warehouse) {
		String sql = "INSERT INTO warehouse (ItemName) VALUES (?)";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, warehouse.getWarehouseName());

			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = ps.getGeneratedKeys();
				if (keys.next()) {
					int key = keys.getInt(1);
					warehouse.setWarehouseId(key);
					conn.commit();
					return warehouse;
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
	public void update(Warehouse warehouse) {
		String sql = "UPDATE warehouse SET WarehouseId = ?, WarehouseName = ? WHERE WarehouseId = ?;";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, warehouse.getWarehouseId());
			ps.setString(2, warehouse.getWarehouseName());
			ps.setInt(3,  warehouse.getWarehouseId());
			
			
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
	public void delete(Warehouse warehouse) {
		String sql = "DELETE FROM warehouse WHERE WarehouseId = ?";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  warehouse.getWarehouseId());
			
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
		String sql = "DELETE FROM warehouse WHERE WarehouseId = ?";
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
		String sql = "DELETE FROM warehouse WHERE WarehouseId = ?";
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
	
	private static Warehouse mapRowToWarehouse(ResultSet rs) {
		Warehouse warehouse = new Warehouse();
		try {
			warehouse.setWarehouseId(rs.getInt("WarehouseId"));
			warehouse.setWarehouseName(rs.getString("WarehouseName"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return warehouse;
	}

}
