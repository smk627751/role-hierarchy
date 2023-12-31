package com.smk627751.rolehierarchy.addsubrole.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.smk627751.rolehierarchy.addsubrole.viewmodel.ViewModel;

public class View {
	private ViewModel viewModel;
	private Scanner sc = new Scanner(System.in);
	public View()
	{
		this.viewModel = new ViewModel(this);
	}
	public void init()
	{
		do
		{
			System.out.println("Enter root role name:");
			String role = sc.nextLine();
			viewModel.getEmployees(role);
			onPrint("1.Add sub roles\n2.Display roles\n3.Delete Roles\n4.Add user\n");
			onPrint("operation to be performed: ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
				case 1 ->{
					onPrint("Enter sub role name :");
					String subRole = sc.nextLine();
					onPrint("Enter reporting to role name :");
					String reportingManager = sc.nextLine();
					viewModel.addSubRole(subRole, reportingManager);
				}
				case 2 ->{
					viewModel.displayRoles();
				}
				case 3 ->{
					onPrint("Enter the role to be deleted :");
					String roleToDelete = sc.nextLine();
					onPrint("Enter the role to be transferred :");
					String roleToTransfer = sc.nextLine();
					viewModel.deleteRole(roleToDelete,roleToTransfer);
				}
				case 4 ->{
					onPrint("Enter user name: ");
					String name = sc.nextLine();
					onPrint("Enter role: ");
					String roleToAssign = sc.nextLine();
					viewModel.addUser(name, roleToAssign);
				}
				default ->{
					
				}
			}
		}while(true);
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
