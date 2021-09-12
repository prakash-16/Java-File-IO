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
}
