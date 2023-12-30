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
		return this.db.getEmployee(role);
	}

	public boolean addSubRole(String role, String reportingManager) throws SQLException {
		return this.db.insertEmployee(role,reportingManager);
	}
	public List<String> displayRole(String reportingManager) throws SQLException
	{
		return this.db.displayRole(reportingManager);
	}
}
