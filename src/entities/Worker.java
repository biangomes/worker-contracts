package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkLevel;

public class Worker {
	private String name;
	private WorkLevel level;
	private Double baseSalary;
	/*
	 * Rela��o 1:1 entre Worker e Department.
	 * UM Worker pertence a UM Department.
	 */
	private Department department; 
	
	/*
	 * Rela��o 1:N entre Worker e HourContract.
	 * Como um worker possui v�rios contratos, a representa��o da classe HourContract deve ser por meio de uma LISTA. 
	 * Esta lista precisa ser inicializada vazia, por consequ�ncia ela n�o pode estar presente no construtor.
	 */
	private List<HourContract> contracts = new ArrayList<>();
	
	public Worker() {}
	
	public Worker(String name, WorkLevel level, double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}
	
	public Worker(String name, WorkLevel level, double baseSalary) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
	}
	
	public Worker(String name, double baseSalary) {
		this.name = name;
		this.baseSalary = baseSalary;
	}
	
	
	// Getters e Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkLevel getLevel() {
		return level;
	}

	public void setLevel(WorkLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public Department getDepartment() {
		return this.department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<HourContract> getContracts() {
		return this.contracts;
	}
	
	// verificar o que acontece enquanto este m�todo existe.
	// TODO: remover ap�s ver o que acontece.
	public void setContracts(List<HourContract> contracts) {
		this.contracts = contracts;
	}
	
	
	// M�todos da regra de neg�cio
	public void addContract(HourContract contract) {
		this.contracts.add(contract);
	}
	
	public void removeContract(HourContract contract) {
		this.contracts.remove(contract);
		
	}
	
	public Double income(int year, int month) {
		double sum = baseSalary;
		Calendar cal = Calendar.getInstance();
		
		for (HourContract c : contracts) {
			cal.setTime(c.getDate());
			int c_year = cal.get(Calendar.YEAR);
			int c_month = 1 + cal.get(Calendar.MONTH);
			if (year == c_year && month == c_month) {
				sum += c.totalValue();
			}
		}
		
		return sum;
	}

	
}
