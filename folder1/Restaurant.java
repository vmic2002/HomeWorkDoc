

import java.util.List;
import java.util.ArrayList;
public class Restaurant extends FoodPlace{
	Staff cook;
	Server server;
	Restaurant(String name, double fixedCosts, Owner owner, Staff cook, Server server){
		super(name, fixedCosts, owner);
		this.cook = cook;
		this.server = server;
	}
	public Staff getCook() {
		return cook;
	}
	public Server getServer() {
		return server;
	}
	public String toString() {
		return this.getName() + this.getFoodPlaceID();
	}
	@Override
	public void workShift(int hours) {
		this.getOwner().salaryExpenses+=(cook.workHours(hours)+server.workHours(hours));
	}

	@Override
	public double getTipPercentage() {
		return server.getTargetTipPct();
	}
	@Override
	public List<IncomeTaxPayer> getIncomeTaxPayers() {
		List<IncomeTaxPayer> payers = new ArrayList<IncomeTaxPayer>(3);
		payers.add(cook);
		payers.add(server);
		payers.add(this.getOwner());
		return payers;
	}
	@Override
	public void distributeIncomeAndSalesTax(Check check) {
		server.income+=0.8*check.getTip();
		cook.income+=0.2*check.getTip();
		this.owner.income+=check.menuPrice;
		this.totalSalesTax += check.getSalesTax();
	}

}
