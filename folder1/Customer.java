

public class Customer {
	String name;
	int targetTipPct;
	Customer(String name, int targetTipPct){
		this.name = name;
		this.targetTipPct = targetTipPct;
	}
	public String getName() {
		return name;
	}
	public int getTargetTipPct() {
		return targetTipPct;
	}
	//public String getDescriptiveMessage(FoodPlace foodPlace) {
		//TODO
//		return "";
	//}
	public void dineAndPayCheck(FoodPlace foodPlace, double menuPrice) {
		Check check = new Check(menuPrice);
		check.setTipByPct((targetTipPct+foodPlace.getTipPercentage())/2);
	}
}
