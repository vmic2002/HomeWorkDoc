

public class WorkingOwner extends Owner{
	int targetTipPct;
	WorkingOwner(String name, int targetTipPct){
		super(name);
		this.targetTipPct = targetTipPct;
	}
	public int getTargetTipPct() {
		return targetTipPct;
	}
}
