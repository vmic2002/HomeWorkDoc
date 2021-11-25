

public abstract class IncomeTaxPayer {
	static int currentMaxID = 0;
	int taxID;
	String name;
	public double income;
	IncomeTaxPayer(String name){
		this.name = name;
		income = 0.0;
		taxID = currentMaxID++;
	}
	public static int getCurrentMaxTaxID() {
		return currentMaxID;
	}
	public int getTaxID() {
		return taxID;
	}
	public String getName() {
		return name;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public String toString() {
		return name + taxID;//might not be correct
	}
	public boolean equals(Object obj) {
		return obj instanceof IncomeTaxPayer && ((IncomeTaxPayer) obj).getTaxID()==this.taxID;
	}
	public abstract double calculateIncomeTax();
	
	}
	
