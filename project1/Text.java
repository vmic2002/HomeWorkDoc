
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
	private static int id = 0;//is used in delete key method but might be a better way
	public int numLetters = 0;
	public Map<Integer, ArrayList<Letter>> textList = new HashMap<Integer, ArrayList<Letter>>();
	public Map<Integer, ArrayList<Letter>> getTextList(){
		return textList;
	}

	/*
	NEED TO:
	could connect each cursive letter from left and right by having 1 extra line per side for each letter
	 */

	public int delete(int id, Integer row) {
		//parameters id and row indicate that this method is called in editing mode
		//MAYBE DONT NEED GRECTIDS in this method IF LETTER IS PASSED AS A PARAMETER
		//(would still need grect ids for highlighting letters for copy paste etc)
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
				numLetters--;
				l.remove(letter);//is this really removing cluster from letterTable?
				textList.put(row, l);
				return i;
			}
			i++;
		}
		System.out.println("BIG MISTAKE");
		return 0;

	}


	public void addLetterEditMode(double startX, double startY, double cursorLength, Integer row, char c, int index) {
		//there are Row or index parameters because this function is only called in editing text mode
		LineCluster lineCluster = new LineCluster(c);
		GRectID grectID = new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, id);//GRectID added to canvas in in Letter setCanvas method
		id++;
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

	public void addLetterSaveMode(double startX, double startY, double cursorLength, char c, ArrayList<Letter> filePath, int index) {
		//no Row or index parameters because this function is only called in save file mode

		LineCluster lineCluster = new LineCluster(c);
		//GRectID grectID = new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, id);
		//passing null grectID might cause a bug when trying to add a null grectID to the canvas
		GRectID grectID = new GRectID(startX, startY-cursorLength, cursorLength, cursorLength, id);//GRectID added to canvas in in Letter setCanvas method
		id++;
		Letter letter = getLetter(startX, startY, cursorLength, lineCluster, grectID, c);
		System.out.println("INDEX IS : "+index);
		filePath.add(index, letter);
	}


	public Letter getLetter(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID, char c){
		Letter letter = new Letter(lineCluster, grectID);
		if (c=='A') return getLetterA( startX,  startY,  cursorLength,  letter);
		else if (c=='B') return getLetterB( startX,  startY,  cursorLength,  letter);
		else if (c=='C') return getLetterC(startX,  startY,  cursorLength,  letter);
		else if (c=='D') return getLetterD(startX,  startY,  cursorLength,  letter);
		else if (c=='E') return getLetterE(startX,  startY,  cursorLength,  letter);
		else if (c=='F') return getLetterF(startX,  startY,  cursorLength,  letter);
		else if (c=='G') return getLetterG(startX,  startY,  cursorLength,  letter);
		else if (c=='H') return getLetterH(startX,  startY,  cursorLength,  letter);
		else if (c=='I') return getLetterI(startX,  startY,  cursorLength,  letter);
		else if (c=='J') return getLetterJ(startX,  startY,  cursorLength,  letter);
		else if (c=='K') return getLetterK(startX,  startY,  cursorLength,  letter);
		else if (c=='L') return getLetterL(startX,  startY,  cursorLength,  letter);
		else if (c=='M') return getLetterM(startX,  startY,  cursorLength,  letter);
		else if (c=='N') return getLetterN(startX,  startY,  cursorLength,  letter);
		else if (c=='O') return getLetterO(startX,  startY,  cursorLength,  letter);
		else if (c=='P') return getLetterP(startX,  startY,  cursorLength,  letter);
		else if (c=='Q') return getLetterQ(startX,  startY,  cursorLength,  letter);
		else if (c=='R') return getLetterR(startX,  startY,  cursorLength,  letter);
		else if (c=='S') return getLetterS(startX,  startY,  cursorLength,  letter);
		else if (c=='T') return getLetterT(startX,  startY,  cursorLength,  letter);
		else if (c=='U') return getLetterU(startX,  startY,  cursorLength,  letter);
		else if (c=='V') return getLetterV(startX,  startY,  cursorLength,  letter);
		else if (c=='W') return getLetterW(startX,  startY,  cursorLength,  letter);
		else if (c=='X') return getLetterX(startX,  startY,  cursorLength,  letter);
		else if (c=='Y') return getLetterY(startX,  startY,  cursorLength,  letter);
		else if (c=='Z') return getLetterZ(startX,  startY,  cursorLength,  letter);
		else if (c=='a') return getLettera( startX,  startY,  cursorLength,  letter);
		else if (c=='b') return getLetterb( startX,  startY,  cursorLength,  letter);
		else if (c=='c') return getLetterc( startX,  startY,  cursorLength,  letter);
		else if (c=='d') return getLetterd( startX,  startY,  cursorLength,  letter);
		else if (c=='e') return getLettere( startX,  startY,  cursorLength,  letter);
		else if (c=='f') return getLetterf( startX,  startY,  cursorLength,  letter);
		else if (c=='g') return getLetterg( startX,  startY,  cursorLength,  letter);
		else if (c=='h') return getLetterh( startX,  startY,  cursorLength,  letter);
		else if (c=='i') return getLetteri( startX,  startY,  cursorLength,  letter);
		else if (c=='j') return getLetterj( startX,  startY,  cursorLength,  letter);
		else if (c=='k') return getLetterk( startX,  startY,  cursorLength,  letter);
		else if (c=='l') return getLetterl( startX,  startY,  cursorLength,  letter);
		else if (c=='m') return getLetterm( startX,  startY,  cursorLength,  letter);
		else if (c=='n') return getLettern( startX,  startY,  cursorLength,  letter);
		else if (c=='o') return getLettero( startX,  startY,  cursorLength,  letter);
		else if (c=='p') return getLetterp( startX,  startY,  cursorLength,  letter);
		else if (c=='q') return getLetterq( startX,  startY,  cursorLength,  letter);
		else if (c=='r') return getLetterr( startX,  startY,  cursorLength,  letter);
		else if (c=='s') return getLetters( startX,  startY,  cursorLength,  letter);
		else if (c=='t') return getLettert( startX,  startY,  cursorLength,  letter);
		else if (c=='u') return getLetteru( startX,  startY,  cursorLength,  letter);
		else if (c=='v') return getLetterv( startX,  startY,  cursorLength,  letter);
		else if (c=='w') return getLetterw( startX,  startY,  cursorLength,  letter);
		else if (c=='x') return getLetterx( startX,  startY,  cursorLength,  letter);
		else if (c=='y') return getLettery( startX,  startY,  cursorLength,  letter);
		else if (c=='z') return getLetterz( startX,  startY,  cursorLength,  letter);
		else if (c=='/') return getSlash(startX,  startY,  cursorLength,  letter);
		else if (c==' ') return letter;//space is letter with no lines
		else if (c=='-') return getMinus(startX,  startY,  cursorLength,  letter);
		else if (c=='.') return getPeriod(startX,  startY,  cursorLength,  letter);
		else if (c==',') return getComma(startX,  startY,  cursorLength,  letter);
		else if (c=='(') return getLeftParentheses(startX, startY,  cursorLength,  letter);
		else if (c==')') return getRightParentheses(startX, startY,  cursorLength,  letter);
		else if (c=='=') return getEqualSign(startX, startY,  cursorLength,  letter);
		else if (c=='_') return getUnderScore(startX, startY,  cursorLength,  letter);
		else if (c=='+') return getPlusSign(startX, startY,  cursorLength,  letter);
		else if (c=='<') return getLessThanSign(startX, startY,  cursorLength,  letter);
		else if (c=='>') return getGreaterThanSign(startX, startY,  cursorLength,  letter);
		else return getLetterX(startX,  startY,  cursorLength,  letter);//x is generated for unknown chars
			//return new Letter(lineCluster, grectID);//so if any other letter is hit space is generated
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


	public Letter getLettera(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x*7/4, startY-x*7/6), new Point(startX+x, startY-x*7/6),
				new Point(startX+x, startY-x*7/6), new Point(startX+x/4, startY-x*5/6),
				new Point(startX+x/4, startY-x*5/6), new Point(startX+x/4, startY-x/3),
				new Point(startX+x/4, startY-x/3), new Point(startX+x, startY-x/4),
				new Point(startX+x, startY-x/4), new Point(startX+x*7/4, startY-x*3/6),
				new Point(startX+x*7/4, startY-x*3/6), new Point(startX+x*11/6, startY-x/9),
				new Point(startX+x*7/4, startY-x*7/6), new Point(startX+x*7/4, startY-x*3/6)};
		addGLines(points, letter);
		return letter;
		/*this array of points would create the letter 'a' but it would be as big as an A
		 * because it would fill up the cursor
		 * since 'a' needs to be 2/3 as tall as 'A', this is achieved by multipling the values that are added to starY
		 * by 2/3 except point at height startY-x/4 (so that all letters start at same height)
		 *
		 * new Point(startX+x*7/4, startY-x*7/4), new Point(startX+x, startY-x*7/4),
					new Point(startX+x, startY-x*7/4), new Point(startX+x/4, startY-x*5/4),
					new Point(startX+x/4, startY-x*5/4), new Point(startX+x/4, startY-x/2),
					new Point(startX+x/4, startY-x/2), new Point(startX+x, startY-x/4),
					new Point(startX+x, startY-x/4), new Point(startX+x*7/4, startY-x*3/4),
					new Point(startX+x*7/4, startY-x*3/4), new Point(startX+x*11/6, startY-x/6),
					new Point(startX+x*7/4, startY-x*7/4), new Point(startX+x*7/4, startY-x*3/4)};
		 */
	}
	
	public Letter getLetterb(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x*7/4), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-x/4), new Point(startX+x*3/2, startY-x/4),
				new Point(startX+x*3/2, startY-x/4), new Point(startX+x*7/4, startY-x/2),
				new Point(startX+x*7/4, startY-x/2), new Point(startX+x*7/4, startY-x),
				new Point(startX+x*7/4, startY-x), new Point(startX+x*3/2, startY-x*5/4),
				new Point(startX+x*3/2, startY-x*5/4), new Point(startX+x/4, startY-x*5/4)};
		addGLines(points, letter);
		//no need to scale 'b' to 2/3 in height to make it smaller because 'b' should be as big as 'B'
		//except point at height startY-x/4 (so that all letters start at same height)
		return letter;
	}
	
	public Letter getLetterc(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+7*x/4, startY-7*x/6), new Point(startX+0.25*x, startY-7*x/6),
				new Point(startX+0.25*x, startY-7*x/6), new Point(startX+0.25*x, startY-x/4),
				new Point(startX+0.25*x, startY-x/4), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
		//letter 'c' is letter 'C' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}
	
	public Letter getLetterd(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+cursorLength-x/4, startY-x*7/4), new Point(startX+cursorLength-x/4, startY-x/4),
				new Point(startX+cursorLength-x/4, startY-x/4), new Point(startX+cursorLength-x*3/2, startY-x/4),
				new Point(startX+cursorLength-x*3/2, startY-x/4), new Point(startX+cursorLength-x*7/4, startY-x/2),
				new Point(startX+cursorLength-x*7/4, startY-x/2), new Point(startX+cursorLength-x*7/4, startY-x),
				new Point(startX+cursorLength-x*7/4, startY-x), new Point(startX+cursorLength-x*3/2, startY-x*5/4),
				new Point(startX+cursorLength-x*3/2, startY-x*5/4), new Point(startX+cursorLength-x/4, startY-x*5/4)};
		addGLines(points, letter);
		return letter;
		
		/*
		 * letter 'd' is letter 'b' reflected accrossed X = startX + cursorLength/2 
		 * change startX + A to startX + cursorLength - A
		 */
	}
	
	public Letter getLettere(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x*2/3), new Point(startX+x, startY-x*2/3),
				new Point(startX+x, startY-x*2/3), new Point(startX+x*6/4, startY-x*5/6),
				new Point(startX+x*6/4, startY-x*5/6), new Point(startX+x*6/4, startY-x*6/6),
				new Point(startX+x*6/4, startY-x*6/6), new Point(startX+x, startY-x*7/6),
				new Point(startX+x, startY-x*7/6), new Point(startX+x*2/4, startY-x*6/6),
				new Point(startX+x*2/4, startY-x*6/6), new Point(startX+x/4, startY-x*2/3),
				new Point(startX+x/4, startY-x*2/3), new Point(startX+x*2/4, startY-x*2/9),
				new Point(startX+x*2/4, startY-x*2/9), new Point(startX+x, startY-x/4),
				new Point(startX+x, startY-x/4), new Point(startX+x*7/4, startY-x*2/6)};
		/*letter 'e' scaled to fit cursor box: (not scaled 2/3 in height)
	//except point at height startY-x/4 (so that all letters start at same height)
		{new Point(startX+x/4, startY-x), new Point(startX+x, startY-x),
				new Point(startX+x, startY-x), new Point(startX+x*6/4, startY-x*5/4),
				new Point(startX+x*6/4, startY-x*5/4), new Point(startX+x*6/4, startY-x*6/4),
				new Point(startX+x*6/4, startY-x*6/4), new Point(startX+x, startY-x*7/4),
				new Point(startX+x, startY-x*7/4), new Point(startX+x*2/4, startY-x*6/4),
				new Point(startX+x*2/4, startY-x*6/4), new Point(startX+x/4, startY-x),
				new Point(startX+x/4, startY-x), new Point(startX+x*2/4, startY-x/3),
				new Point(startX+x*2/4, startY-x/3), new Point(startX+x, startY-x/4),
				new Point(startX+x, startY-x/4), new Point(startX+x*7/4, startY-x*2/4)};
		 */
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterf(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x, startY-x/4), new Point(startX+x, startY-x*6/4),
				new Point(startX+x, startY-x*6/4), new Point(startX+x*5/4, startY-x*1.6),
				new Point(startX+x*5/4, startY-x*1.6), new Point(startX+x*6/4, startY-x*7/4),
				new Point(startX+x*6/4, startY-x*7/4), new Point(startX+x*7/4, startY-x*7/4),
				new Point(startX+x/4, startY-x),  new Point(startX+x*7/4, startY-x)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterg(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x*7/4, startY-x), new Point(startX+x*7/4, startY-x*2/4),
					new Point(startX+x*7/4, startY-x*2/4), new Point(startX+x*5/4, startY),
				new Point(startX+x*5/4, startY), new Point(startX+x/4, startY),
				new Point(startX+x*7/4, startY-x), new Point(startX+x*5.5/4, startY-x*6/4),
				new Point(startX+x*5.5/4, startY-x*6/4), new Point(startX+x*2.5/4, startY-x*6/4),
				new Point(startX+x*2.5/4, startY-x*6/4), new Point(startX+x/4, startY-x),
				new Point(startX+x/4, startY-x), new Point(startX+x*2.5/4, startY-x*2/4),
				new Point(startX+x*2.5/4, startY-x*2/4), new Point(startX+x*5.5/4, startY-x*2/4),
				new Point(startX+x*5.5/4, startY-x*2/4), new Point(startX+x*7/4, startY-x)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterh(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x*7/4), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-x/4), new Point(startX+x*2/4, startY-x*3/4),
				new Point(startX+x*2/4, startY-x*3/4), new Point(startX+x*3/4, startY-x),
				new Point(startX+x*3/4, startY-x), 	new Point(startX+x*5/4, startY-x),
				new Point(startX+x*5/4, startY-x), new Point(startX+x*6/4, startY-x*3/4),
				new Point(startX+x*6/4, startY-x*3/4), new Point(startX+x*7/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetteri(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x*3/4, startY-x/4),
				new Point(startX+x*3/4, startY-x/4), new Point(startX+x, startY-x*2/6),
				new Point(startX+x, startY-x*2/6), new Point(startX+x, startY-x*5/6),
				new Point(startX+x*7/4, startY-x/4), new Point(startX+x*5/4, startY-x/4),
				new Point(startX+x*5/4, startY-x/4), new Point(startX+x, startY-x*2/6),
				new Point(startX+x*4.2/4, startY-x*6/4), new Point(startX+x*4.2/4, startY-x*7/4),
				new Point(startX+x*4.2/4, startY-x*7/4), new Point(startX+x*3.8/4, startY-x*7/4),
				new Point(startX+x*3.8/4, startY-x*7/4), new Point(startX+x*3.8/4, startY-x*6/4),
				new Point(startX+x*3.8/4, startY-x*6/4), new Point(startX+x*4.2/4, startY-x*6/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterj(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x*6/4, startY-x*5/4), new Point(startX+x*6/4, startY-x*2/4),
				new Point(startX+x*6/4, startY-x*2/4), new Point(startX+x, startY),
				new Point(startX+x, startY),  new Point(startX+x/4, startY),
				new Point(startX+x*6.2/4, startY-x*6/4), new Point(startX+x*6.2/4, startY-x*7/4),
				new Point(startX+x*6.2/4, startY-x*7/4), new Point(startX+x*5.8/4, startY-x*7/4),
				new Point(startX+x*5.8/4, startY-x*7/4), new Point(startX+x*5.8/4, startY-x*6/4),
				new Point(startX+x*5.8/4, startY-x*6/4), new Point(startX+x*6.2/4, startY-x*6/4)
		};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterk(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/4), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-x*2/3), new Point(startX+7*x/4, startY-7*x/6),
				new Point(startX+x/4, startY-x*2/3), new Point(startX+7*x/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
		//letter 'k' is letter 'K' scaled to 2/3 in height
		//except that vertical line is not scaled
		//except point at height startY-x/4 (so that all letters start at same height)
	}
	public Letter getLetterl(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x, startY-x*1.5/4),
				new Point(startX+x, startY-x*1.5/4), new Point(startX+x*5.5/4, startY-x*5/4),
				new Point(startX+x*5.5/4, startY-x*5/4), new Point(startX+x*5/4, startY-x*7/4),
				new Point(startX+x*5/4, startY-x*7/4), new Point(startX+x*3/4, startY-x*7/4),
				new Point(startX+x*3/4, startY-x*7/4), new Point(startX+x*2.5/4, startY-x*5/4),
				new Point(startX+x*2.5/4, startY-x*5/4), new Point(startX+x, startY-x*1.5/4),
				new Point(startX+x, startY-x*1.5/4), new Point(startX+x*7/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterm(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-x*7/6),
				new Point(startX+x/4, startY-x),  new Point(startX+x*2/4, startY-x*7/6),
				new Point(startX+x*2/4, startY-x*7/6), new Point(startX+x*3/4, startY-x*7/6),
				new Point(startX+x*3/4, startY-x*7/6), new Point(startX+x, startY-x),
				new Point(startX+x, startY-x), new Point(startX+x*5/4, startY-x*7/6),
				new Point(startX+x*5/4, startY-x*7/6),  new Point(startX+x*6/4, startY-x*7/6),
				new Point(startX+x*6/4, startY-x*7/6), new Point(startX+x*7/4, startY-x),
				new Point(startX+x*7/4, startY-x),  new Point(startX+x*7/4, startY-x/4),
				new Point(startX+x, startY-x), new Point(startX+x, startY-x/4)};
		addGLines(points, letter);
		return letter;
		//all y for startY-y were scaled to 2/3 except for when y=x/4 (startY-x/4)
		//(so that all letters start at same height)
	}

	public Letter getLettern(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-x*7/6),
				new Point(startX+x/4, startY-x),  new Point(startX+x*3/4, startY-x*7/6),
				new Point(startX+x*3/4, startY-x*7/6), new Point(startX+x*5/4, startY-x*7/6),
				new Point(startX+x*5/4, startY-x*7/6), new Point(startX+x*7/4, startY-x),
				new Point(startX+x*7/4, startY-x), new Point(startX+x*7/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
		//all y for startY-y were scaled to 2/3 except for when y=x/4 (startY-x/4)
		//(so that all letters start at same height)
	}


	public Letter getLettero(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+7*x/4, startY-x), new Point(startX+3*x/2, startY-7*x/6),
				new Point(startX+3*x/2, startY-7*x/6), new Point(startX+x/2, startY-7*x/6),
				new Point(startX+x/2, startY-7*x/6), new Point(startX+x/4, startY-x),
				new Point(startX+x/4, startY-x), new Point(startX+x/4, startY-x/3),
				new Point(startX+x/4, startY-x/3), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+7*x/4, startY-x/3),
				new Point(startX+7*x/4, startY-x/3), new Point(startX+7*x/4, startY-x)};
		addGLines(points, letter);
		return letter;
		//letter 'o' is letter 'O' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}

	public Letter getLetterp(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY), new Point(startX+x/4, startY-7*x/6),
				new Point(startX+x/4, startY-7*x/6), new Point(startX+6*x/4, startY-7*x/6),
				new Point(startX+6*x/4, startY-7*x/6), new Point(startX+7*x/4, startY-6*x/6),
				new Point(startX+7*x/4, startY-6*x/6), new Point(startX+7*x/4, startY-5*x/6),
				new Point(startX+7*x/4, startY-5*x/6), new Point(startX+6*x/4, startY-x*2/3),
				new Point(startX+6*x/4, startY-x*2/3), new Point(startX+x/4, startY-x*2/3)};
		addGLines(points, letter);
		//'p' is 'P' scaled to 2/3 and vertical line goes all the way down to startY
		return letter;
	}

	public Letter getLetterq(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x*7/4, startY), new Point(startX+x*7/4, startY-7*x/6),
				new Point(startX+x*7/4, startY-7*x/6), new Point(startX+x*2/4, startY-7*x/6),
				new Point(startX+x*2/4, startY-7*x/6), new Point(startX+x/4, startY-6*x/6),
				new Point(startX+x/4, startY-6*x/6), new Point(startX+x/4, startY-5*x/6),
				new Point(startX+x/4, startY-5*x/6), new Point(startX+x*2/4, startY-x*2/3),
				new Point(startX+x*2/4, startY-x*2/3), new Point(startX+x*7/4, startY-x*2/3)};
		addGLines(points, letter);
		/*
		 * letter 'q' is letter 'p' reflected accrossed X = startX + cursorLength/2
		 * change startX + A to startX + cursorLength - A
		 */
		return letter;
	}

	public Letter getLetterr(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-x*7/6),
				new Point(startX+x/4, startY-x),  new Point(startX+x*3/4, startY-x*7/6),
				new Point(startX+x*3/4, startY-x*7/6), new Point(startX+x*5/4, startY-x*7/6),
				new Point(startX+x*5/4, startY-x*7/6), new Point(startX+x*7/4, startY-x)};
		addGLines(points, letter);
		return letter;
		//letter 'r' is letter 'n' without rightmost vertical line
		//all y for startY-y were scaled to 2/3 except for when y=x/4 (startY-x/4)
		//(so that all letters start at same height)
	}

	public Letter getLetters(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+7*x/4, startY-x), new Point(startX+3*x/2, startY-7*x/6),
				new Point(startX+3*x/2, startY-7*x/6), new Point(startX+x/2, startY-7*x/6),
				new Point(startX+x/2, startY-7*x/6), new Point(startX+x/4, startY-x),
				new Point(startX+x/4, startY-x), new Point(startX+x/4, startY-x*2/3),
				new Point(startX+x/4, startY-x*2/3), new Point(startX+7*x/4, startY-x*2/3),
				new Point(startX+7*x/4, startY-x*2/3), new Point(startX+7*x/4, startY-x/3),
				new Point(startX+7*x/4, startY-x/3), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+x/4, startY-x/3)};
		addGLines(points, letter);
		return letter;
		//letter 's' is letter 'S' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}

	public Letter getLettert(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x, startY-x/4), new Point(startX+x, startY-x*7/4),
				new Point(startX+x/4, startY-x*5/4), new Point(startX+x*7/4, startY-x*5/4)};
		addGLines(points, letter);
		return letter;
		//letter 's' is letter 'S' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}

	public Letter getLetteru(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/6), new Point(startX+x/4, startY-x/3),
				new Point(startX+x/4, startY-x/3), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+7*x/4, startY-x/3),
				new Point(startX+7*x/4, startY-x/3), new Point(startX+7*x/4, startY-7*x/6)};
		addGLines(points, letter);
		return letter;
		//letter 'u' is letter 'U' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}
	public Letter getLetterv(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/6), new Point(startX+x, startY-x/4),
				new Point(startX+x, startY-x/4), new Point(startX+7*x/4, startY-7*x/6)};
		addGLines(points, letter);
		return letter;
		//letter 'v' is letter 'V' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}

	public Letter getLetterw(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/6), new Point(startX+x/2, startY-x/4),
				new Point(startX+x/2, startY-x/4), new Point(startX+x, startY-5*x/6),
				new Point(startX+x, startY-5*x/6), new Point(startX+3*x/2, startY-x/4),
				new Point(startX+3*x/2, startY-x/4), new Point(startX+7*x/4, startY-7*x/6)};
		addGLines(points, letter);
		return letter;
		//letter 'w' is letter 'W' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}
	public Letter getLetterx(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/6), new Point(startX+7*x/4, startY-x/4),
				new Point(startX+x/4, startY-x/4), new Point(startX+7*x/4, startY-7*x/6)};
		addGLines(points, letter);
		return letter;
		//letter 'x' is letter 'X' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}

	public Letter getLettery(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x*7/4, startY-x), new Point(startX+x*7/4, startY-x*2/4),
				new Point(startX+x*7/4, startY-x*2/4), new Point(startX+x*5/4, startY),
				new Point(startX+x*5/4, startY), new Point(startX+x/4, startY),
				new Point(startX+x/4, startY-x), new Point(startX+x*2.5/4, startY-x*2/4),
				new Point(startX+x*2.5/4, startY-x*2/4), new Point(startX+x*5.5/4, startY-x*2/4),
				new Point(startX+x*5.5/4, startY-x*2/4), new Point(startX+x*7/4, startY-x),
				new Point(startX+x/4, startY-x), new Point(startX+x/4, startY-x*7/6),
				new Point(startX+x*7/4, startY-x),  new Point(startX+x*7/4, startY-x*7/6)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getLetterz(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-7*x/6), new Point(startX+7*x/4, startY-7*x/6),
				new Point(startX+7*x/4, startY-7*x/6), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-x/4), new Point(startX+7*x/4, startY-x/4),};
		addGLines(points, letter);
		return letter;
		//letter 'z' is letter 'Z' scaled to 2/3 in height
		//except point at height startY-x/4 (so that all letters start at same height)
	}


	public Letter getSlash(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+7*x/4, startY-7*x/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getMinus(double startX, double startY, double cursorLength, Letter letter){
		letter.addLine(new GLine(startX+cursorLength/8, startY-cursorLength/2, startX+cursorLength*7/8, startY-cursorLength/2 ));
		return letter;
	}

	public Letter getPeriod(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-3*x/8),
				new Point(startX+x/4, startY-3*x/8), new Point(startX+3*x/8, startY-3*x/8),
				new Point(startX+3*x/8, startY-3*x/8), new Point(startX+3*x/8, startY-x/4),
				new Point(startX+3*x/8, startY-x/4), new Point(startX+x/4, startY-x/4)};
		addGLines(points, letter);
		//period is a little square
		return letter;
	}

	public Letter getComma(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x/4), new Point(startX+x/4, startY-3*x/8),
				new Point(startX+x/4, startY-3*x/8), new Point(startX+3*x/8, startY-3*x/8),
				new Point(startX+3*x/8, startY-3*x/8), new Point(startX+3*x/8, startY-x/4),
				new Point(startX+3*x/8, startY-x/4), new Point(startX+x/4, startY-x/4),
				new Point(startX+x/4, startY-x/4), new Point(startX+x/8, startY-x/8)};
		addGLines(points, letter);
		//comma is period with extra line at left
		return letter;
	}

	public Letter getLeftParentheses(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x*7/4, startY-x*7/4), new Point(startX+x, startY-x*6/4),
				new Point(startX+x, startY-x*6/4), new Point(startX+x*3/4, startY-x*5/4),
				new Point(startX+x*3/4, startY-x*5/4), new Point(startX+x*3/4, startY-x*3/4),
				new Point(startX+x*3/4, startY-x*3/4), new Point(startX+x, startY-x*2/4),
				new Point(startX+x, startY-x*2/4), new Point(startX+x*7/4, startY-x/4)};
		addGLines(points, letter);

		return letter;
	}

	public Letter getRightParentheses(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+cursorLength-x*7/4, startY-x*7/4), new Point(startX+cursorLength-x, startY-x*6/4),
				new Point(startX+cursorLength-x, startY-x*6/4), new Point(startX+cursorLength-x*3/4, startY-x*5/4),
				new Point(startX+cursorLength-x*3/4, startY-x*5/4), new Point(startX+cursorLength-x*3/4, startY-x*3/4),
				new Point(startX+cursorLength-x*3/4, startY-x*3/4), new Point(startX+cursorLength-x, startY-x*2/4),
				new Point(startX+cursorLength-x, startY-x*2/4), new Point(startX+cursorLength-x*7/4, startY-x/4)};
		addGLines(points, letter);
		return letter;
		/*
		 * letter ')' is letter '(' reflected accrossed X = startX + cursorLength/2
		 * change startX + A to startX + cursorLength - A
		 */
	}

	public Letter getEqualSign(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		Point[] points = {new Point(startX+x/4, startY-x*5/4), new Point(startX+x*7/4, startY-x*5/4),
				new Point(startX+x/4, startY-x*3/4), new Point(startX+x*7/4, startY-x*3/4)};
		addGLines(points, letter);
		return letter;
	}

	public Letter getUnderScore(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		letter.addLine(new GLine(startX+x/4, startY-x/4, startX+x*7/4, startY-x/4));
		return letter;
	}

	public Letter getPlusSign(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		letter.addLine(new GLine(startX+x/4, startY-x, startX+x*7/4, startY-x));
		letter.addLine(new GLine(startX+x, startY-x*7/4, startX+x, startY-x/4));
		return letter;
	}

	public Letter getLessThanSign(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		letter.addLine(new GLine(startX+x/4, startY-x, startX+x*7/4, startY-x*7/4));
		letter.addLine(new GLine(startX+x/4, startY-x, startX+x*7/4, startY-x/4));
		return letter;
	}

	public Letter getGreaterThanSign(double startX, double startY, double cursorLength, Letter letter){
		double x = cursorLength/2;
		letter.addLine(new GLine(startX+x*7/4, startY-x, startX+x/4, startY-x*7/4));
		letter.addLine(new GLine(startX+x*7/4, startY-x, startX+x/4, startY-x/4));
		return letter;
	}


}
