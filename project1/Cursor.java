import java.awt.Color;

import acm.graphics.GLine;
import acm.graphics.GRect;

public class Cursor extends GRect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GRect thinCursor;
	//maybe Cursor should have cursor coordinates as field
	public Cursor(int arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2, arg2);
		//COMMENTS BELOW THIS ARE TO MAKE CURSOR LOOK LIKE THE CURSOR
		//IN THE TERMINAL
		this.setVisible(false);
		//this.setColor(Color.WHITE);
		//this.setFilled(true);
		//this.setVisible(false); UNCOMMENT FOR NORMAL CURSOR

		thinCursor = new GRect(arg0, arg1, 3,arg2);
		thinCursor.sendToFront();
		thinCursor.setVisible(true);
		thinCursor.setColor(Color.GREEN); //UNCOMMENT FOR NORMAL CURSOR
		thinCursor.setFilled(true);// UNCOMMENT FOR NORMAL CURSOR
	}

	public void move(double x, double y) {
		super.move(x, y);
		thinCursor.move(x,y);

	}
	public void changeLocation(double x, double y) {
		//does not update cursor coordinates
		super.setLocation(x, y);
		thinCursor.setLocation(x,y);

	}
	
	public void sendToFront() {
		super.sendToFront();
		thinCursor.sendToFront();
	}



}
