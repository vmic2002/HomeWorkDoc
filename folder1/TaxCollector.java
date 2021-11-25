
import java.util.List;
public class TaxCollector {
	List<FoodPlace> foodPlaces;
	double incomeTaxCollected;
	double salesTaxCollected;
	TaxCollector(List<FoodPlace> foodPlaces){
		this.foodPlaces = foodPlaces;
		incomeTaxCollected = 0;
		salesTaxCollected = 0;
	}
	public List<FoodPlace> getFoodPlaces(){
		return foodPlaces;
	}
	public double getIncomeTaxCollected() {
		return incomeTaxCollected;
	}
	public double getSalesTaxCollected() {
		return salesTaxCollected;
	}
	public void collectTax() {
		List<IncomeTaxPayer> incomeTaxPayers;
		for (FoodPlace place:foodPlaces) {
			salesTaxCollected += place.getTotalSalesTax();
			incomeTaxPayers = place.getIncomeTaxPayers();
			for (IncomeTaxPayer payer:incomeTaxPayers) {
				incomeTaxCollected += payer.calculateIncomeTax();
			} 
		}
	}
	public String toString() {
		//TODO
		return "";
	}
}
