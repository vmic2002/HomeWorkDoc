

public class Staff extends IncomeTaxPayer {
	int salaryPerHour;
	int incomeTaxPercentage;
	Staff(String name, boolean isCook){
		super(name);
		if(isCook)
			salaryPerHour = 20;
		else
			salaryPerHour = 10;
		incomeTaxPercentage = 25;
	}
	public int getSalaryPerHour() {
		return salaryPerHour;
	}
	public int getIncomeTaxPercentage() {
		return incomeTaxPercentage;
	}
	public double workHours(int numHours) {
		double profit =  numHours*salaryPerHour;
		this.income += profit;
		return profit;
	}
	public double calculateIncomeTax() {
		return this.income*incomeTaxPercentage;
	}

}
