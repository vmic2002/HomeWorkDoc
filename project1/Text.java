
//import java.awt.Color;
//import java.awt.List;

//import java.awt.Graphics;
import java.awt.event.KeyEvent;
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
	class Point{
		double x;
		double y;
		Point(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	private static int numLetters = 0;//is used in delete key method but might be a better way 
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


	public void addLetter(double startX, double startY, double cursorLength, Integer row, char c, int index) {
		LineCluster lineCluster = new LineCluster(c);
		GRectID grectID = new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, numLetters);//GRectID added to canvas in in Letter setCanvas method
		Letter letter = getLetter(startX, startY, cursorLength, lineCluster, grectID, c);
		ArrayList<Letter> l = textList.get(row);
		if (l==null) {
			textList.put(row, new ArrayList<Letter>());
			l = textList.get(row);
		}
	
			l.add(index, letter);
		
		textList.put(row, l);
		numLetters++;
	}

	public Letter getLetter(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID, char c){
		Letter letter = new Letter(lineCluster, grectID);
		if (c=='A') {
			return getLetterA( startX,  startY,  cursorLength,  letter);
		} else if (c=='B') {
			return getLetterB( startX,  startY,  cursorLength,  letter);
		}  else if (c=='C') {
			return getLetterC(startX,  startY,  cursorLength,  letter);
		}else if (c=='D') {
			return getLetterD(startX,  startY,  cursorLength,  letter);
		}else if (c=='E') {
			return getLetterE(startX,  startY,  cursorLength,  letter);
		}else if (c=='F') {
			return getLetterF(startX,  startY,  cursorLength,  letter);
		}else if (c=='G') {
			return getLetterG(startX,  startY,  cursorLength,  letter);
		}else if (c=='H') {
			return getLetterH(startX,  startY,  cursorLength,  letter);
		}else if (c=='I') {
			return getLetterI(startX,  startY,  cursorLength,  letter);
		}else if (c=='J') {
			return getLetterJ(startX,  startY,  cursorLength,  letter);
		}else if (c=='K') {
			return getLetterK(startX,  startY,  cursorLength,  letter);
		}else if (c=='L') {
			return getLetterL(startX,  startY,  cursorLength,  letter);
		}else if (c=='M') {
			return getLetterM(startX,  startY,  cursorLength,  letter);
		}else if (c=='N') {
			return getLetterN(startX,  startY,  cursorLength,  letter);
		}else if (c=='O') {
			return getLetterO(startX,  startY,  cursorLength,  letter);
		}else if (c=='P') {
			return getLetterP(startX,  startY,  cursorLength,  letter);
		}else if (c=='Q') {
			return getLetterQ(startX,  startY,  cursorLength,  letter);
		}else if (c=='R') {
			return getLetterR(startX,  startY,  cursorLength,  letter);
		}else if (c=='S') {
			return getLetterS(startX,  startY,  cursorLength,  letter);
		}else if (c=='T') {
			return getLetterT(startX,  startY,  cursorLength,  letter);
		}else if (c=='U') {
			return getLetterU(startX,  startY,  cursorLength,  letter);
		}else if (c=='V') {
			return getLetterV(startX,  startY,  cursorLength,  letter);
		}else if (c=='W') {
			return getLetterW(startX,  startY,  cursorLength,  letter);
		}else if (c=='X') {
			return getLetterX(startX,  startY,  cursorLength,  letter);
		}else if (c=='Y') {
			return getLetterY(startX,  startY,  cursorLength,  letter);
		}else if (c=='Z') {
			return getLetterZ(startX,  startY,  cursorLength,  letter);
		}
		else {
			return new Letter(lineCluster, grectID);//so if any other letter is hit space is generated
		}/*
		//else if (c==' ') {
			return new Letter(lineCluster, grectID);
		} else {
			System.out.println("PROBLEM IN TEXT getLetter method");
			return null;
		}*/
	}

	public void addGLines(Point[] points, Letter letter){
		for (int i=0; i<points.length; i+=2)
			letter.addLine(new GLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y));
	}

	public Letter getLetterA(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x, startY-7*x/4),
				new Point(startX+x, startY-7*x/4), new Point(startX+7*x/4, startY-x/4),
				new Point(startX+5*x/8, startY-x), new Point(startX+11*x/8, startY-x)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterB(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+x, startY-7*x/4),
				new Point(startX+x, startY-7*x/4), new Point(startX+5*x/4, startY-3*x/2),
				new Point(startX+5*x/4, startY-3*x/2), new Point(startX+5*x/4, startY-5*x/4),
				new Point(startX+5*x/4, startY-5*x/4), new Point(startX+x, startY-x),
				new Point(startX+x, startY-x), new Point(startX+x/4, startY-x),
				new Point(startX+x, startY-x), new Point(startX+3*x/2, startY-x),
				new Point(startX+3*x/2, startY-x), new Point(startX+7*x/4, startY-3*x/4),
				new Point(startX+7*x/4, startY-3*x/4), new Point(startX+7*x/4, startY-x/2),
				new Point(startX+7*x/4, startY-x/2), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}


	public Letter getLetterC(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+7*x/4, startY-7*x/4), new Point(startX+0.25*x, startY-7*x/4),
				new Point(startX+0.25*x, startY-7*x/4), new Point(startX+0.25*x, startY-0.25*x),
				new Point(startX+0.25*x, startY-0.25*x), new Point(startX+7*x/4, startY-0.25*x)};
		addGLines(points, letter);
		return letter;
	}
	
	public Letter getLetterD(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+5*x/4, startY-7*x/4),
				new Point(startX+5*x/4, startY-7*x/4), new Point(startX+7*x/4, startY-5*x/4),
				new Point(startX+7*x/4, startY-5*x/4), new Point(startX+7*x/4, startY-3*x/4),
				new Point(startX+7*x/4, startY-3*x/4), new Point(startX+5*x/4, startY-x/4),
				new Point(startX+5*x/4, startY-x/4), new Point(startX+x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}
	
	public Letter getLetterE(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-x),
				new Point(startX+x/4, startY-x/4), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}
	
	public Letter getLetterF(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-x)};
		addGLines(points, letter);
		return letter;
	}


	public Letter getLetterG(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+7*x/4, startY-3*x/2), new Point(startX+3*x/2, startY-7*x/4),
				new Point(startX+3*x/2, startY-7*x/4), new Point(startX+x/2, startY-7*x/4),
				new Point(startX+x/2, startY-7*x/4), new Point(startX+x/4, startY-3*x/2),
				new Point(startX+x/4, startY-3*x/2), new Point(startX+x/4, startY-x/2),
				new Point(startX+x/4, startY-x/2), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+7*x/4, startY-x),
				new Point(startX+7*x/4, startY-x), new Point(startX+x, startY-x)};
		addGLines(points, letter);
		return letter;
	}


	public Letter getLetterH(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-x),
				new Point(startX+7*x/4, startY-x/4), new Point(startX+7*x/4, startY-7*x/4)};
		addGLines(points, letter);
		return letter;
	}
	
	public Letter getLetterI(double startX, double startY, double cursorLength, Letter letter){
		Point[] points = {new Point(startX+0.5*cursorLength, startY-0.9*cursorLength), new Point(startX+0.5*cursorLength, startY-0.1*cursorLength),
				new Point(startX+0.1*cursorLength, startY-0.9*cursorLength), new Point(startX+0.9*cursorLength, startY-0.9*cursorLength),
				new Point(startX+0.1*cursorLength, startY-0.1*cursorLength), new Point(startX+0.9*cursorLength, startY-0.1*cursorLength)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterJ(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+x, startY-7*x/4), new Point(startX+x, startY-x/3),
				new Point(startX+x, startY-x/3), new Point(startX+x/3, startY-x/4),
				new Point(startX+x/3, startY-x/4), new Point(startX+x/8, startY-x/4)};//last point should be startX+x/4, startY-x/4)
		addGLines(points, letter);
		return letter;
	}
	public Letter getLetterK(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterL(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-x/4), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterM(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/2, startY-7*x/4),
				new Point(startX+x/2, startY-7*x/4), new Point(startX+x, startY-2*x/3),
				new Point(startX+x, startY-2*x/3), new Point(startX+3*x/2, startY-7*x/4),
				new Point(startX+3*x/2, startY-7*x/4), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}
	public Letter getLetterN(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-x/4),
				new Point(startX+7*x/4, startY-x/4), new Point(startX+7*x/4, startY-7*x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterO(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+7*x/4, startY-3*x/2), new Point(startX+3*x/2, startY-7*x/4),
				new Point(startX+3*x/2, startY-7*x/4), new Point(startX+x/2, startY-7*x/4),
				new Point(startX+x/2, startY-7*x/4), new Point(startX+x/4, startY-3*x/2),
				new Point(startX+x/4, startY-3*x/2), new Point(startX+x/4, startY-x/2),
				new Point(startX+x/4, startY-x/2), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+7*x/4, startY-x/2),
				new Point(startX+7*x/4, startY-x/2), new Point(startX+7*x/4, startY-3*x/2)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterP(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+6*x/4, startY-7*x/4),
				new Point(startX+6*x/4, startY-7*x/4), new Point(startX+7*x/4, startY-6*x/4),
				new Point(startX+7*x/4, startY-6*x/4), new Point(startX+7*x/4, startY-5*x/4),
				new Point(startX+7*x/4, startY-5*x/4), new Point(startX+6*x/4, startY-x),
				new Point(startX+6*x/4, startY-x), new Point(startX+x/4, startY-x)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterQ(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		letter = getLetterO(startX, startY, cursorLength, letter);//q is o with line through it
		Point[] points = {new Point(startX+x, startY-x), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterR(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+6*x/4, startY-7*x/4),
				new Point(startX+6*x/4, startY-7*x/4), new Point(startX+7*x/4, startY-6*x/4),
				new Point(startX+7*x/4, startY-6*x/4), new Point(startX+7*x/4, startY-5*x/4),
				new Point(startX+7*x/4, startY-5*x/4), new Point(startX+6*x/4, startY-x),
				new Point(startX+6*x/4, startY-x), new Point(startX+x/4, startY-x),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterS(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+7*x/4, startY-3*x/2), new Point(startX+3*x/2, startY-7*x/4),
				new Point(startX+3*x/2, startY-7*x/4), new Point(startX+x/2, startY-7*x/4),
				new Point(startX+x/2, startY-7*x/4), new Point(startX+x/4, startY-3*x/2),
				new Point(startX+x/4, startY-3*x/2), new Point(startX+x/4, startY-x),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-x),
				new Point(startX+7*x/4, startY-x), new Point(startX+7*x/4, startY-x/2),
				new Point(startX+7*x/4, startY-x/2), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+x/4, startY-x/2)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterT(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+x, startY-7*x/4), new Point(startX+x, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterU(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+x/4, startY-x/2),
				new Point(startX+x/4, startY-x/2), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+7*x/4, startY-x/2),
				new Point(startX+7*x/4, startY-x/2), new Point(startX+7*x/4, startY-7*x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterV(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+x, startY-x/4),
				new Point(startX+x, startY-x/4), new Point(startX+7*x/4, startY-7*x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterW(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+x, startY-5*x/4),
				new Point(startX+x, startY-5*x/4), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+7*x/4, startY-7*x/4)};
		addGLines(points, letter);
		return letter;
	}
	public Letter getLetterX(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-x/4),
				new Point(startX+x/4, startY-x/4), new Point(startX+7*x/4, startY-7*x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterY(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+x, startY-5*x/4),
				new Point(startX+x, startY-5*x/4), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+x, startY-5*x/4), new Point(startX+x, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}


	public Letter getLetterZ(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+7*x/4, startY-7*x/4), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-x/4), new Point(startX+7*x/4, startY-x/4),};
		addGLines(points, letter);
		return letter;
	}



}
