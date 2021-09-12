package com.bridgelabz.javafileio.main;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeePayrollService {

	public EmployeePayrollService() {

	}

	public EmployeePayrollService(ArrayList<EmployeePayroll> employeePayrollData) {
		this.employeePayrollData = employeePayrollData;
	}

	public static ArrayList<EmployeePayroll> employeePayrollData;

	public void readData(Scanner consoleInputReader) {
		System.out.println("Enter employee Id :- ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter name :- ");
		String name = consoleInputReader.next();
		System.out.println("Enter salary :- ");
		double salary = consoleInputReader.nextDouble();
		employeePayrollData.add(new EmployeePayroll(id, name, salary));
	}

	public void writeData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO)) {
			System.out.println("List Of Employee Payroll Data\n");
			System.out.println(employeePayrollData);
		} else if (ioService.equals(IOService.FILE_IO)) {
			new EmployeePayrollServiceIO().writeData(employeePayrollData);
		}
	}

	public static void main(String[] args) {
		employeePayrollData = new ArrayList<>();
		EmployeePayrollService employee = new EmployeePayrollService();
		Scanner consoleInputReader = new Scanner(System.in);
		System.out.println(System.getProperty("user.home"));
		employee.readData(consoleInputReader);
		employee.writeData(IOService.FILE_IO);
	}
	
	public void printData(IOService ioService) {
		if(ioService.equals(IOService.FILE_IO)) {
			new EmployeePayrollServiceIO().printData();
		}
	}

}
