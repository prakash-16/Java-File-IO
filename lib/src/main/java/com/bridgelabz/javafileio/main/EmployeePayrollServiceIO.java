package com.bridgelabz.javafileio.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.google.common.io.Files;

public class EmployeePayrollServiceIO {
	public static String Payroll_String = "payroll-file.txt";
	public static File Payroll_File = new File("C:\\Users\\pgoud\\payroll-file");

	public void writeData(ArrayList<EmployeePayroll> employeePayrollData) {
		StringBuffer empData = new StringBuffer();
		employeePayrollData.stream().forEach(n -> {
			String emp = n.toString().concat("\n");
			empData.append(emp);
		});
		try {
			Files.write(empData.toString().getBytes(), Payroll_File);
		} catch (IOException x) {
			x.printStackTrace();
		}

	}
	
	public void printData() {
		try {
			Files.lines((new File("payroll-file.txt").toPath()).forEach(System.out::println);
		}
		catch(IOException x) {
			x.printStackTrace();
		}
	}
	
	public int countEntry() {
		int entry;
		try {
			entry = Files.lines((new File("payroll-file.txt").toPath()).count();
		}
		catch(IOException x) {
			x.printStackTrace();
		}
		return entry;
	}
}
