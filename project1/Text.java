
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
		GRectID grectID =new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, numLetters);//GRectID added to canvas in in Letter setCanvas method
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
		if (c=='A') {
			return getLetterA( startX,  startY,  cursorLength,  lineCluster, grectID);
		} else if (c=='B') {
			return getLetterB( startX,  startY,  cursorLength,  lineCluster, grectID);
		} else if (c=='I') {
			return getLetterI(startX,  startY,  cursorLength,  lineCluster, grectID);
		} else if (c=='C') {
			return getLetterC(startX,  startY,  cursorLength,  lineCluster, grectID);
		}else if (c=='D') {
			return getLetterD(startX,  startY,  cursorLength,  lineCluster, grectID);
		}else if (c=='E') {
			return getLetterE(startX,  startY,  cursorLength,  lineCluster, grectID);
		}else if (c=='F') {
			return getLetterF(startX,  startY,  cursorLength,  lineCluster, grectID);
		}else if (c==' ') {
			return new Letter(lineCluster, grectID);
		} else {
			System.out.println("PROBLEM IN TEXT getLetter method");
			return null;
		}
	}

	public void addGLines(Point[] points, Letter letter){
		for (int i=0; i<points.length; i+=2)
			letter.addLine(new GLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y));
	}

	public Letter getLetterA(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterA = new Letter(lineCluster, grectID);
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x, startY-7*x/4),
				new Point(startX+x, startY-7*x/4), new Point(startX+7*x/4, startY-x/4),
				new Point(startX+5*x/8, startY-x), new Point(startX+11*x/8, startY-x)};
		addGLines(points, letterA);
		return letterA;
	}

	public Letter getLetterB(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterB = new Letter(lineCluster, grectID);
		double x = cursorLength/2;
		Point[] points = {new Point(startX+0.3*x, startY), new Point(startX+0.3*x, startY-cursorLength),
				new Point(startX+0.3*x, startY-cursorLength), new Point(startX+x, startY-cursorLength),
				new Point(startX+x, startY-cursorLength), new Point(startX+x, startY-x),
				new Point(startX+0.3*x, startY-x), new Point(startX+1.5*x, startY-x),
				new Point(startX+1.5*x, startY-x), new Point(startX+1.5*x, startY),
				new Point(startX+1.5*x, startY), new Point(startX+0.3*x, startY)};
		addGLines(points, letterB);
		return letterB;
	}


	public Letter getLetterC(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterC = new Letter(lineCluster, grectID);
		double x = cursorLength/2;
		Point[] points = {new Point(startX+7*x/4, startY-7*x/4), new Point(startX+0.25*x, startY-7*x/4),
				new Point(startX+0.25*x, startY-7*x/4), new Point(startX+0.25*x, startY-0.25*x),
				new Point(startX+0.25*x, startY-0.25*x), new Point(startX+7*x/4, startY-0.25*x)};
		addGLines(points, letterC);
		return letterC;
	}
	
	public Letter getLetterD(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterD = new Letter(lineCluster, grectID);
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+5*x/4, startY-7*x/4),
				new Point(startX+5*x/4, startY-7*x/4), new Point(startX+7*x/4, startY-5*x/4),
				new Point(startX+7*x/4, startY-5*x/4), new Point(startX+7*x/4, startY-3*x/4),
				new Point(startX+7*x/4, startY-3*x/4), new Point(startX+5*x/4, startY-x/4),
				new Point(startX+5*x/4, startY-x/4), new Point(startX+x/4, startY-x/4)};
		addGLines(points, letterD);
		return letterD;
	}
	
	public Letter getLetterE(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterE = new Letter(lineCluster, grectID);
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-x),
				new Point(startX+x/4, startY-x/4), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letterE);
		return letterE;
	}
	
	public Letter getLetterF(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterF = new Letter(lineCluster, grectID);
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-7*x/4),
				new Point(startX+x/4, startY-7*x/4), new Point(startX+7*x/4, startY-7*x/4),
				new Point(startX+x/4, startY-x), new Point(startX+7*x/4, startY-x)};
		addGLines(points, letterF);
		return letterF;
	}
	
	public Letter getLetterI(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterI = new Letter(lineCluster, grectID);
		Point[] points = {new Point(startX+0.5*cursorLength, startY-0.9*cursorLength), new Point(startX+0.5*cursorLength, startY-0.1*cursorLength),
				new Point(startX+0.1*cursorLength, startY-0.9*cursorLength), new Point(startX+0.9*cursorLength, startY-0.9*cursorLength),
				new Point(startX+0.1*cursorLength, startY-0.1*cursorLength), new Point(startX+0.9*cursorLength, startY-0.1*cursorLength)};
		addGLines(points, letterI);
		return letterI;
	}





}
