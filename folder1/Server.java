

public class Server extends Staff {
	int targetTipPct;
	Server(String name, int targetTipPct){
		super(name, false);
		this.targetTipPct = targetTipPct;
	}
	public int getTargetTipPct() {
		return targetTipPct;
	}
}
