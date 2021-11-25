

public class Check {
	double menuPrice;
	double salesTax;
	double tip;
	Check(double menuPrice){
		this.menuPrice = menuPrice;
		salesTax = 0.15*menuPrice;
	}
	public double getMenuPrice() {
		return menuPrice;
	}
	public double getSalesTax() {
		return salesTax;
	}
	public double getTip() {
		return tip;
	}
	public void setTipByPct(double tipPct) {
		tip  = menuPrice*tipPct/100;
	}
}
