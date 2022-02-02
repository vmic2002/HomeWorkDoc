
//import java.awt.Color;

import acm.graphics.GRect;
//import acm.graphics.GCanvas;
public class GRectID extends GRect{
	/*//trying to pair each GRect with a number and a letter with corresponding number 
	 * to be able to delete a letter when cursor goes over it
	//hoping that getElementAt(cursor.getX()+cursor.getWidth()/2, cursor.getY()+cursor.getHeight()/2);
	 * will return the GRectID and that we could delete lines by keeping them in a list
	 * and adding back the correct char (if copy paste is done, not yet implemented) by using the getChar() method
	 * if copy paste is done, hopefully GRectID has the GRect field change location instead of deleting and making new GRectID 
	 * 
	 * 
	 */

	//public GRect rect;
	private int id;
	private static final long serialVersionUID = 3881223065097358339L;//don't know why this line is here but it has to for compilation to work
	//private char ch;
	public GRectID(double arg0, double arg1, double arg2, double arg3, int id) {//delete this constructor if GRectID does not extends GRect
		super(arg0, arg1, arg2, arg3);
		this.setVisible(false);//set to true to highlight letters
		//this.setFillColor(Color.BLUE);
		//this.setFilled(false);

		/*
		 * (un)comment code above for highlight
		 */

		//canvas.add(this);
		this.sendToFront();
		//	this.ch = ch;
		this.id = id;

	}
	//public char getChar() {
	//	return ch;
	//}

	public int getID() {
		return id;
	}

}