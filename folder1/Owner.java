
public class Owner extends IncomeTaxPayer {
	int incomeTaxPct;
	double salaryExpenses;
	public FoodPlace foodPlace;
	Owner(String name){
		super(name);
	}
	public int getIncomeTaxPct() {
		return incomeTaxPct;
	}
	public double getSalaryExpenses() {
		return salaryExpenses;
	}
	public void setSalaryExpenses(double salaryExpenses) {
		this.salaryExpenses = salaryExpenses;
	}
	public void setFoodPlace(FoodPlace foodPlace) {
		this.foodPlace = foodPlace;
	}
	public FoodPlace getFoodPlace() {
		return foodPlace;
	}

	@Override
	public double calculateIncomeTax() {
		if (foodPlace==null)
			return 0.0;
		double profit = this.getIncome()-(this.salaryExpenses+foodPlace.getFixedCosts());
		if(profit>0)
			return 0.1*profit;
		return 0;
	}

}
