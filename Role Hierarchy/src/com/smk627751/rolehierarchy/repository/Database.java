package com.smk627751.rolehierarchy.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.smk627751.rolehierarchy.dto.Employee;

public class Database {
	Connection con;
	Database()
	{
		try {
			Class.forName("org.postgresql.Driver");
			String dbURL = "jdbc:postgresql://localhost:5432/role_hierarchy";
			String user = "postgres";
			String pass = "627751";
			this.con = DriverManager.getConnection(dbURL, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public List<String> displayRole(String reportingManager) throws SQLException
	{
		List<String> subRoles = new ArrayList<>();
		Statement s = con.createStatement();
		ResultSet rs = null;
		String sql = Query.DISPLAY_ROLES_QUERY;
		if(reportingManager != null)
		{
			sql += "where r.role = '"+reportingManager+"'";
		}
		rs = s.executeQuery(sql);
		while(rs.next())
		{
			subRoles.add(rs.getString("role"));
		}
		return subRoles;
	}
	public List<Employee> getEmployee(String role) throws SQLException
	{
		List<Employee> employeeList = new ArrayList<>();
		Statement s = con.createStatement();
		String sql = "select * from ("+Query.BASE_QUERY+") as emp_table where role = '"+role+"'";
		ResultSet rs = s.executeQuery(sql);
		while(rs.next())
		{
			employeeList.add(new Employee(rs.getInt("id"),rs.getString("name"),rs.getBoolean("is_active"),rs.getString("role"),rs.getString("reporting_manager")));
		}
		return employeeList;
	}
	public int getId(String role) throws SQLException
	{
		String sql = "select id from role_table where role = '"+role+"'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		int id = 0;
		while(rs.next())
		{
			id = rs.getInt("id");
		}
		return id;
	}
	public boolean insertEmployee(String role, String reportingManager) throws SQLException {
		String sql = "insert into role_table (role) values(?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, role);
		int rowsAffected = ps.executeUpdate();
		if(rowsAffected == 0)
		{
			return false;
		}
		sql = "select max(id) as id from role_table";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		int roleId = 0;
		while(rs.next())
		{
			roleId = rs.getInt("id");
		}
		int reportingManagerId = getId(reportingManager);
		sql = "insert into employee_table(role,reporting_manager) values("+roleId+",'"+reportingManagerId+"')";
		PreparedStatement ps1 = con.prepareStatement(sql);
		rowsAffected = ps1.executeUpdate();
		return rowsAffected > 0 ? true : false;
	}
	public boolean deleteRole(String roleToDelete, String roleToTransfer) throws SQLException {
		String sql = "select reporting_manager as id from employee_table where role = (select id from role_table where role ='"+roleToDelete+"')";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		int parent = 0;
		while(rs.next())
		{
			parent = rs.getInt("id");
		}
		
		sql = Query.UPDATE_ROLE_QUERY.replace("rd", roleToDelete).replace("rm", roleToTransfer);
		PreparedStatement ps = con.prepareStatement(sql);
		int rowAffected = ps.executeUpdate();
		if(rowAffected == 0)
		{
			return false;
		}
		sql = Query.DELETE_ROLE_QUERY.replace("?", roleToDelete);
		ps = con.prepareStatement(sql);
		rowAffected = ps.executeUpdate();
		if(rowAffected == 0)
		{
			return false;
		}
		sql = "update employee_table set reporting_manager = "+parent+" where role = (select id from role_table where role = '"+roleToTransfer+"')";
		ps = con.prepareStatement(sql);
		rowAffected = ps.executeUpdate();
		if(rowAffected == 0)
		{
			return false;
		}
		return true;
	}
	public boolean addUser(String name, String role) throws SQLException
	{
		String sql = "update employee_table set name = '"+name+"' where role = (select id from role_table where role = '"+role+"')";
		PreparedStatement ps = con.prepareStatement(sql);
		int rowAffected = ps.executeUpdate();
		return rowAffected > 0 ? true : false;
	}
}
