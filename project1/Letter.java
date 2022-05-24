import java.awt.Color;

import acm.graphics.GCanvas;
import acm.graphics.GLine;
public class Letter {
	private GRectID grectID;
	private LineCluster lineCluster;
	public static GCanvas canvas;//COULD HAVE LIST OF CANVASES IF WE WANT TO IMPLEMENT MUTLIPLE DOCS AT ONCE
	//could have button "crazy mode" where each letter has a random color
	//or each line has a random color
	//random color shouldnt be the same as the background color or else line or letter will disappear
	Letter(LineCluster lineCluster, GRectID grectID){
		this.lineCluster = lineCluster;
		this.grectID = grectID;
		canvas.add(this.grectID);
	}

	static void setCanvas(GCanvas canvas1) {
		canvas = canvas1;
	}

	public GRectID getGRectID() {
		return grectID;
	}
	public LineCluster getLineCluster() {
		return lineCluster;
	}
	public void addLine(GLine line) {
		line.setColor(Color.WHITE);//this is how to change color of letters (not highlighting them)
		lineCluster.addLine(line);
		canvas.add(line);
	}
	public void move(double dx, double dy) {
		grectID.move(dx, dy);
		if (lineCluster.getLines().size()!=0) {
			for (GLine l : lineCluster.getLines())
				l.move(dx,  dy);
		}
	}

	public void setLocation(double dx, double dy){
		move(dx-grectID.getX(),dy-grectID.getY());
	}
}
