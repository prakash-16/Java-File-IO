package com.bridgelabz.javafileio.test;

import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.javafileio.main.EmployeePayroll;
import com.bridgelabz.javafileio.main.EmployeePayrollService;
import com.bridgelabz.javafileio.main.IOService;

public class EmployeePayrollTest {

	@Test
	public void givenEmployeeDetailswhenWrittenShouldReturnTrue() {
		EmployeePayroll[] arrOfEmp = { new EmployeePayroll(1, "Elon Musk", 500),
				new EmployeePayroll(2, "Tim Cook", 485), new EmployeePayroll(3, "Richard Brandson", 600) };
		EmployeePayrollService empService = new EmployeePayrollService(
				(ArrayList<EmployeePayroll>) Arrays.asList(arrOfEmp));
		empService.writeData(IOService.FILE_IO);

	}

}
