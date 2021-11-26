import acm.graphics.*;
import java.awt.Color;
public class Letter {
	private GRectID grectID;
	private LineCluster lineCluster;
	public static GCanvas canvas;

	//maybe need private int id;
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
		line.setColor(Color.BLACK);//this is how to change color of letters (not highlighting them)
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
	
	

}
