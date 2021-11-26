import java.awt.Color;

import acm.graphics.GLine;
import acm.graphics.GRect;

public class Cursor extends GRect {
	GRect thinCursor;
	public Cursor(int arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2, arg2);
		this.setVisible(false);

		thinCursor = new GRect(arg0, arg1, 3,arg2);
	//	thinCursor.sendToFront();
		//thinCursor.setVisible(true);
		thinCursor.setColor(Color.BLACK);
		thinCursor.setFilled(true);
	}

	public void move(double x, double y) {
		super.move(x, y);
		thinCursor.move(x,y);

	}
	public void changeLocation(double x, double y) {
		super.setLocation(x, y);
		thinCursor.setLocation(x,y);

	}



}
