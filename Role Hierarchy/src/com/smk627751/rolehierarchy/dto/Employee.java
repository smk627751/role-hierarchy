package com.smk627751.rolehierarchy.dto;

public class Employee {
	private int id;
	private String name;
	private boolean isActive;
	private String role;
	private String reportingManager;
	public Employee(int id,String name, boolean isActive,String role, String reportingManager)
	{
		this.id = id;
		this.name = name;
		this.setActive(isActive);
		this.role =role;
		this.reportingManager = reportingManager;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getReportingManager() {
		return reportingManager;
	}
	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
	public String toString()
	{
		return this.id+" "+this.name+" "+this.isActive+" "+this.role+" "+this.reportingManager;
	}
}
