
import java.util.List;
import java.util.ArrayList;
public class FastFood extends FoodPlace {
	List<Staff> staff;
	FastFood(String name, double fixedCosts, Owner owner, List<Staff> staff){
		super(name, fixedCosts, owner);
		
	this.staff = new ArrayList<Staff>();
	for(Staff s:staff)
		this.staff.add(s);
	//	for (Staff s:staff) {
			//Staff x = new Staff(s.getName(), s.getSalaryPerHour()==20);
		//	this.staff.add(x);
	//	}
	}

	public List<Staff> getStaff(){
		return staff;
	}
	public String toString() {
		return this.name + this.foodPlaceID;
	}

	@Override
	public void workShift(int hours) {
		double x = 0;
		for(Staff s:staff)
			x+=s.workHours(hours);
		this.owner.salaryExpenses+=x;
	}

	@Override
	public double getTipPercentage() {
		return 0.0;
	}

	@Override
	public List<IncomeTaxPayer> getIncomeTaxPayers() {
		ArrayList<IncomeTaxPayer> payers = new ArrayList<IncomeTaxPayer>(staff.size()+1);
		for (Staff s:staff)
			payers.add(s);
		payers.add(this.getOwner());
		ArrayList<IncomeTaxPayer> copy = new ArrayList<IncomeTaxPayer>(payers.size());
		for (IncomeTaxPayer p:payers)
			copy.add(p);
		return copy;
	}
	
	
	
	
	
	

	@Override
	public void distributeIncomeAndSalesTax(Check check) {
		this.getOwner().income+=check.getMenuPrice();
		double splitTip  = check.getTip()/staff.size();
		for(Staff s:staff)
			s.income+=splitTip;
		this.totalSalesTax+=check.getSalesTax();
	}

}
