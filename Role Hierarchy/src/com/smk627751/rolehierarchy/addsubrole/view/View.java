package com.smk627751.rolehierarchy.addsubrole.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.smk627751.rolehierarchy.addsubrole.viewmodel.ViewModel;

public class View {
	private ViewModel viewModel;
	public View()
	{
		this.viewModel = new ViewModel(this);
	}
	public void init()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter root role name:");
		String role = sc.nextLine();
		viewModel.getEmployees(role);
		onPrint("1.Add sub roles\n2.Display roles\n3.Delete Roles\n");
		switch(sc.nextInt())
		{
			case 1 ->{
				sc.nextLine();
				System.out.println("Enter sub role name :");
				String subRole = sc.nextLine();
				System.out.println("Enter reporting to role name :");
				String reportingManager = sc.nextLine();
				viewModel.addSubRole(subRole, reportingManager);
			}
			case 2 ->{
				
			}
			default ->{
				
			}
		}
	}
	public void onPrint(String s)
	{
		System.out.println(s);
	}
	public void onError(SQLException e) {
//		System.out.println(e.getMessage());
		e.printStackTrace();
	}
}
