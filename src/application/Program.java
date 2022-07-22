package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkLevel;

public class Program {
	public static void main(String[] args) throws ParseException{
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.println("Level: ");
		String level = sc.nextLine();
		System.out.print("Base salary: ");
		Double baseSalary = sc.nextDouble();
		System.out.print("How many contracts to this worker? ");
		Integer numberOfContracts = sc.nextInt();
		
		Worker w1 = new Worker(name, WorkLevel.valueOf(level), baseSalary, new Department(departmentName));
		
		
		for (int i=0; i < numberOfContracts; i++) {
			System.out.println("Enter contract #" + i + " data:");
			System.out.print("Date (DD/MM/YYYY): ");
			Date contractDate = sdf.parse(sc.next());
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			w1.addContract(contract);
		}
		
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String incomeDate = sc.next();
		int incomeMonth = Integer.parseInt(incomeDate.substring(0, 2)); 
		int incomeYear = Integer.parseInt(incomeDate.substring(3));
		System.out.println("Name: " + w1.getName());
		System.out.println("Department: " + w1.getDepartment());
		System.out.println("Income for " + incomeDate + ":" + String.format("%.2f", w1.income(incomeYear, incomeMonth)));
		
		sc.close();
	}
}
