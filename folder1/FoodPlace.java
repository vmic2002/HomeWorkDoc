
import java.util.List;
public abstract class FoodPlace {
		static int currentMaxFoodPlaceID = 0;
		int foodPlaceID;
		String name;
		double fixedCosts;
		public double totalSalesTax;
		Owner owner;
		
		FoodPlace(String name ,double fixedCosts, Owner owner){
			this.name = name;
			this.fixedCosts = fixedCosts;
			this.owner = owner;
			foodPlaceID = currentMaxFoodPlaceID++;
			totalSalesTax = 0;
		}
		
		static int getCurrentMaxFoodPlaceID() {
			return currentMaxFoodPlaceID;
		}
		
		public int getFoodPlaceID() {
			return foodPlaceID;
		}
		
		public String getName() {
			return name;
		}
		public double getFixedCosts() {
			return fixedCosts;
		}
		public double getTotalSalesTax() {
			return totalSalesTax;
		}
		public void setTotalSalesTax(double totalSalesTax) {
			this.totalSalesTax = totalSalesTax;
		}
		
		public Owner getOwner() {
			return owner;
		}
		public boolean equals(Object obj) {
				return obj instanceof FoodPlace && ((FoodPlace)obj).getFoodPlaceID() == this.foodPlaceID;
		}
		public abstract void workShift(int hours);
		public abstract List<IncomeTaxPayer> getIncomeTaxPayers();
		public abstract void distributeIncomeAndSalesTax(Check check);
		public abstract double getTipPercentage();
		
		
}
