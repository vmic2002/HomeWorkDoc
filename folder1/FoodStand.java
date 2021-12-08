

import java.util.ArrayList;
import java.util.List;
public class FoodStand extends FoodPlace {
	FoodStand(String name, double fixedCosts, WorkingOwner owner){
		super(name, fixedCosts, owner);
	}
	public String toString()
	{
		return this.getName() + this.getFoodPlaceID();
	}
	@Override
	public void workShift(int hours) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getTipPercentage() {
		return (double) (((WorkingOwner) this.getOwner()).getTargetTipPct());
	}
	@Override
	public List<IncomeTaxPayer> getIncomeTaxPayers() {
		List<IncomeTaxPayer> payers = new ArrayList<IncomeTaxPayer>(1);
		payers.add(this.getOwner());
		return payers;
	}
	@Override
	public void distributeIncomeAndSalesTax(Check check) {
		this.owner.income+=(check.menuPrice+check.getTip());
		this.totalSalesTax+=check.getSalesTax();
	}

}
