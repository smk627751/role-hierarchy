package com.smk627751.rolehierarchy.repository;

import java.sql.SQLException;
import java.util.List;

import com.smk627751.rolehierarchy.dto.Employee;

public class Repository {
	private static Repository repo;
	Database db;
	private Repository(){
		this.db = new Database();
	}
	
	public static Repository getInstance()
	{
		if(repo == null)
		{
			repo = new Repository();
		}
		
		return repo;
	}
	
	public List<Employee> getEmployee(String role) throws SQLException
	{
		return db.getEmployee(role);
	}

	public boolean addSubRole(String role, String reportingManager) throws SQLException {
		return db.insertEmployee(role,reportingManager);
	}
	public List<String> displayRole(String reportingManager) throws SQLException
	{
		return db.displayRole(reportingManager);
	}

	public boolean deleteRole(String roleToDelete, String roleToTransfer) throws SQLException {
		return db.deleteRole(roleToDelete,roleToTransfer);
	}
	public boolean addUser(String name, String role) throws SQLException
	{
		return db.addUser(name, role);
	}
}
