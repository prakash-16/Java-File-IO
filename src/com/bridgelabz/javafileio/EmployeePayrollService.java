package com.bridgelabz.javafileio;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeePayrollService {

	public static ArrayList<EmployeePayroll> employeePayrollData;

	public void readData(Scanner sc) {
		System.out.println("Enter employee Id :- ");
		int id = sc.nextInt();
		System.out.println("Enter name :- ");
		String name = sc.next();
		System.out.println("Enter salary :- ");
		double salary = sc.nextDouble();
		employeePayrollData.add(new EmployeePayroll(id, name, salary));
	}

	public void writeData() {
		System.out.println("List Of Employee Payroll Data\n");
		System.out.println(employeePayrollData);
	}

	public static void main(String[] args) {
		employeePayrollData = new ArrayList<>();
		EmployeePayrollService employee = new EmployeePayrollService();
		Scanner sc = new Scanner(System.in);
		employee.readData(sc);
		employee.writeData();
	}

}
