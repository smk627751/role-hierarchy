package com.smk627751.rolehierarchy.repository;

public class Query {
	static final String BASE_QUERY = "select e.id, e.name, e.is_active, r.role, e.reporting_manager from employee_table as e join role_table as r on e.role = r.id";
	static final String DISPLAY_ROLES_QUERY = "select emp.id,emp.name,emp.role,r.role as reporting_manager from (select e1.id,e1.name,r.role,e2.role as reporting_manager from employee_table as e1 \r\n"
			+ "join employee_table as e2 \r\n"
			+ "on e1.reporting_manager = e2.role\r\n"
			+ "join role_table as r\r\n"
			+ "on e1.role = r.id) as emp\r\n"
			+ "join role_table as r\r\n"
			+ "on emp.reporting_manager = r.id\r\n";
	static final String UPDATE_ROLE_QUERY = "update employee_table\r\n"
			+ "set reporting_manager = (select id from role_table where role = 'rm')\r\n"
			+ "where reporting_manager = (select id from role_table where role = 'rd')";
	static final String DELETE_ROLE_QUERY = "Delete from role_table where role = '?'";
	static final String DELETE_QUERY = "update employee_table set is_active = false where role = (select id from role_table)";
}
