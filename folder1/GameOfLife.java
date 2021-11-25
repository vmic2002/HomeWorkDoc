import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameOfLife  extends GraphicsProgram{
	/**
	 * 
	 */
	List<GRect> rectangles;
	private static final long serialVersionUID = 1L;
	private static final int x = 40;
	public void run() {
		rectangles  = new ArrayList<GRect>();
		setSize(x*25, x*25);
		System.out.println(getWidth()+"<<>>"+ getHeight()+"<<HERE");
		GRect rect1 = new GRect(x*12, x*12, x, x);
		rect1.setFilled(true);
		rect1.setVisible(true);

		GRect rect2 = new GRect(x*13, x*11, x, x);
		rect2.setFilled(true);
		rect2.setVisible(true);
		GRect rect5= new GRect(x*13, x*12, x, x);
		rect5.setFilled(true);
		rect5.setVisible(true);
		GRect rect3 = new GRect(x*14, x*12, x, x);
		rect3.setFilled(true);
		rect3.setVisible(true);
		GRect rect4 = new GRect(x*15, x*12, x, x);
		rect4.setFilled(true);
		rect4.setVisible(true);
		rectangles.add(rect1);
		rectangles.add(rect2);
		rectangles.add(rect3);
		rectangles.add(rect4);
		rectangles.add(rect5);
		for (int i=0; i<rectangles.size(); i++) {
			System.out.println("HI");
			
			rectangles.get(i).setColor(Color.BLUE);
			rectangles.get(i).sendToFront();
			add(rectangles.get(i));

		}
		pause(250);
		for (int i=1; i<25; i++) {
			add(new GLine(x*i, 0, x*i, getHeight()));
		}
		for (int i=1; i<25; i++) {
			add(new GLine(0, x*i, getWidth(), x*i));
		}

		System.out.println("SIZE>>"+ rectangles.size());
		//for (int i=0; i<300; i++) {
			pause(100);

			//System.out.println("HEY>>"+i);
			for (int k = 1; k<24; k++) {
				for (int l = 1; l<24; l++) {
					if (getElementAt(x*k+x/2,x*l+x/2)!=null){
						System.out.println("COOOOL");
					}
					if (getElementAt(x*k+x/2,x*l+x/2)==null&&update(x*k,x*l)==3) {
						GRect rect = new GRect(x*k, x*l, x, x);
						rect.setFilled(true);
						rect.setVisible(true);
						rect.setColor(Color.BLUE);
						rect.sendToFront();
						rectangles.add(rect);
						add(rect);
						check(rect);
						pause(200);
					}
				}
			}

		//	System.out.println("IN LOOOP size is >>"+ rectangles.size());

	
			for (int j=0; j<rectangles.size(); j++) {
				int temp1  = update(rectangles.get(j).getX(), rectangles.get(j).getY());
				System.out.println(">>"+temp1);
				if (temp1>=4|| temp1<2) {
					remove(rectangles.get(j));
					rectangles.remove(j);
					j--;
					pause(200);
				}

			}

		//}
		System.out.println("DONE");

	
	}
	
	public void check(GRect grect) { 
		int temp = update(grect.getX(), grect.getY());
		if (temp>=4|| temp<2) {
			remove(grect);
			rectangles.remove(grect);
			pause(200);
		}
	}
	
	public int update(double xNum, double yNum) {
		int numNeighbors = 0;

		for (int i=0; i<3; i++){
			if (getElementAt(xNum-x/2, yNum+i*x+x/2)!=null &&getElementAt(xNum-x/2, yNum+i*x+x/2) instanceof GRect) {
				numNeighbors++;
				System.out.println("AAAAA");
			}
		}
		for (int i=0; i<3; i++){
			if (getElementAt(xNum+3*x/2, yNum+i*x+x/2)!=null &&getElementAt(xNum+3*x/2, yNum+i*x+x/2) instanceof GRect) {
				numNeighbors++;
				System.out.println("BBBBB");
			}
		}
		if (getElementAt(xNum+x/2, yNum-x/2)!=null && getElementAt(xNum+x/2, yNum-x/2) instanceof GRect) {
			numNeighbors++;
			System.out.println("CCCCC");
		}
		if (getElementAt(xNum+x/2, yNum+3*x/2)!=null && getElementAt(xNum+x/2, yNum+3*x/2) instanceof GRect) {
			numNeighbors++;
			System.out.println("DDDDD");
		}
		System.out.println("x:"+xNum+" y:"+yNum+" numNeighbors:"+numNeighbors);
		return numNeighbors;



	}


}
