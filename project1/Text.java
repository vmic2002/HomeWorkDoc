
//import java.awt.Color;
//import java.awt.List;

//import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import acm.graphics.GCanvas;
import acm.graphics.GLine;
public  class Text {
	GCanvas canvas;

	Text(GCanvas canvas){
		this.canvas = canvas;
		Letter.setCanvas(this.canvas);
	}
	private static int counter = 0;
	public Map<Integer, ArrayList<Letter>> textList = new HashMap<Integer, ArrayList<Letter>>();
	public Map<Integer, ArrayList<Letter>> getTextList(){
		return textList;
	}
	public int delete(int id, Integer row) {
		ArrayList<Letter> l = textList.get(row);
		if (l==null) {
			return 0;
		}
		int i=0;
		for (Letter letter :l) {
			GRectID grectID = letter.getGRectID();

			if (grectID.getID()==id) {
				LineCluster cluster = letter.getLineCluster();
				for (GLine line:cluster.getLines())
					canvas.remove(line);
				canvas.remove(grectID);
				l.remove(letter);//is this really removing cluster from letterTable?
				textList.put(row, l);
				return i;
			}
			i++;
		}
		System.out.println("BIG MISTAKE");
		return 0;

	}

	public void addSpace(double startX, double startY, double cursorLength, Integer row, int index) {
		LineCluster lineCluster = new LineCluster(' ');
		GRectID grectID =new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, counter);//GRectID added to canvas in in Letter setCanvas method 
		Letter letterSpace = new Letter(lineCluster, grectID);

		ArrayList<Letter> l = textList.get(row);
		if (l==null) {
			textList.put(row, new ArrayList<Letter>());
			l = textList.get(row);
		}
		l.add(index, letterSpace);

		textList.put(row, l);

		//letterA doesnt have to be added to correct index in l since list doesnt have to be ordered since each LineCluster has unique ID
		counter++;//must be add end of every add letter method (addA(), addB()...) once
	}


	public void addLetter(double startX, double startY, double cursorLength, Integer row, char c, int index) {
		if (c=='A') {
			addA( startX,  startY,  cursorLength,  row, index);
		} else if (c=='B') {
			addB(startX,  startY,  cursorLength,  row, index);
		} else if (c=='I') {
			addI(startX,  startY,  cursorLength,  row, index);
		} else if (c==' ') {
			addSpace(startX,  startY,  cursorLength,  row, index);
		}
	}
	
	public void addA(double startX, double startY, double cursorLength, Integer row, int index) {
		LineCluster lineCluster = new LineCluster('A');
		GRectID grectID =new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, counter);//GRectID added to canvas in in Letter setCanvas method 
		Letter letterA = new Letter(lineCluster, grectID);

		double x = cursorLength/2;

		GLine a = new GLine(startX, startY, startX+x, startY-2*x);
		letterA.addLine(a);

		GLine b = new GLine(startX+x, startY-2*x,startX+2*x, startY);
		letterA.addLine(b);

		GLine c = new GLine(startX+x/2, startY-x, startX+1.5*x, startY-x);
		letterA.addLine(c);

		ArrayList<Letter> l = textList.get(row);
		if (l==null) {
			textList.put(row, new ArrayList<Letter>());
			l = textList.get(row);
		}
		l.add(index, letterA);

		textList.put(row, l);

		//letterA doesnt have to be added to correct index in l since list doesnt have to be ordered since each LineCluster has unique ID
		counter++;//must be add end of every add letter method (addA(), addB()...) once
	}

	public void addB(double startX, double startY, double cursorLength, Integer row, int index) {
		LineCluster lineCluster = new LineCluster('B');
		GRectID grectID =new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, counter);//GRectID added to canvas in in Letter setCanvas method 
		Letter letterB = new Letter(lineCluster, grectID);


		double x = cursorLength/2;
		GLine a = new GLine(startX+0.3*x, startY, startX+0.3*x, startY-cursorLength);
		letterB.addLine(a);

		GLine b = new GLine(startX+0.3*x, startY-cursorLength, startX+x, startY-cursorLength);
		letterB.addLine(b);

		GLine c = new GLine(startX+x, startY-cursorLength, startX+x, startY-x);
		letterB.addLine(c);

		GLine d = new GLine(startX+0.3*x, startY-x, startX+1.5*x, startY-x);
		letterB.addLine(d);

		GLine e = new GLine(startX+1.5*x, startY-x, startX+1.5*x, startY);
		letterB.addLine(e);

		GLine f = new GLine(startX+1.5*x, startY, startX+0.3*x, startY);
		letterB.addLine(f);

		ArrayList<Letter> l = textList.get(row);
		if (l==null) {
			textList.put(row, new ArrayList<Letter>());
			l = textList.get(row);
		}
		l.add(index, letterB);

		textList.put(row, l);
		counter++;
	}
	public void getC() {
		/*
		 * 	GLine a = new GLine(startX, startY-0.3*cursorLength, startX+cursorLength, startY-0.3*cursorLength);
		 * 	letterC.addLine(a);
		canvas.add(a);
		GLine b = new GLine(startX, startY, startX, startY+cursorLength);
		letterC.addLine(b);
		canvas.add(b);
		GLine c = new GLine(startX, startY-cursorLength, startX+cursorLength, startY-cursorLength);
		 */

	}
	public void getD() {

	}
	public void getE() {

	}
	public void getF() {

	}
	public void getG() {

	}
	public void getH() {

	}
	public void addI(double startX, double startY, double cursorLength, Integer row, int index) {
		LineCluster lineCluster = new LineCluster('I');
		GRectID grectID =new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, counter);//GRectID added to canvas in in Letter setCanvas method 
		Letter letterI = new Letter(lineCluster, grectID);

		GLine a = new GLine(startX+0.5*cursorLength, startY-0.9*cursorLength, startX+0.5*cursorLength, startY-0.1*cursorLength);
		letterI.addLine(a);

		GLine b = new GLine(startX+0.1*cursorLength, startY-0.9*cursorLength, startX+0.9*cursorLength, startY-0.9*cursorLength);
		letterI.addLine(b);

		GLine c = new GLine(startX+0.1*cursorLength, startY-0.1*cursorLength, startX+0.9*cursorLength, startY-0.1*cursorLength);
		letterI.addLine(c);

		ArrayList<Letter> l = textList.get(row);
		if (l==null) {
			textList.put(row, new ArrayList<Letter>());
			l = textList.get(row);
		}
		l.add(index, letterI);

		textList.put(row, l);
		counter++;
	}
	public void getJ() {

	}
	public void getK() {

	}
	public void getL() {

	}
	public void getM() {

	}
	public void getN() {

	}
	public void getO() {

	}
	public void getP() {

	}
	public void getQ() {

	}
	public void getR() {

	}
	public void getS() {

	}
	public void getT() {

	}
	public void  getU() {

	}
	public void  getV() {

	}
	public void getW() {

	}
	public void getX() {

	}
	public void getY() {

	}
	public void getZ() {

	}


}
