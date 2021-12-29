
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
	private static int numLetters = 0;
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
		} else if (c==' ') {
			return new Letter(lineCluster, grectID);
		} else {
			System.out.println("PROBLEM IN TEXT getLetter method");
			return null;
		}
	}

	public Letter getLetterA(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterA = new Letter(lineCluster, grectID);
		double x = cursorLength/2;
		GLine a = new GLine(startX, startY, startX+x, startY-2*x);
		letterA.addLine(a);

		GLine b = new GLine(startX+x, startY-2*x,startX+2*x, startY);
		letterA.addLine(b);

		GLine c = new GLine(startX+x/2, startY-x, startX+1.5*x, startY-x);
		letterA.addLine(c);
		return letterA;
	}

	public Letter getLetterB(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
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
		return letterB;
	}

	public Letter getLetterI(double startX, double startY, double cursorLength, LineCluster lineCluster, GRectID grectID){
		Letter letterI = new Letter(lineCluster, grectID);

		GLine a = new GLine(startX+0.5*cursorLength, startY-0.9*cursorLength, startX+0.5*cursorLength, startY-0.1*cursorLength);
		letterI.addLine(a);

		GLine b = new GLine(startX+0.1*cursorLength, startY-0.9*cursorLength, startX+0.9*cursorLength, startY-0.9*cursorLength);
		letterI.addLine(b);

		GLine c = new GLine(startX+0.1*cursorLength, startY-0.1*cursorLength, startX+0.9*cursorLength, startY-0.1*cursorLength);
		letterI.addLine(c);
		return letterI;
	}
	/*

	public void getC() {

		 //	GLine a = new GLine(startX, startY-0.3*cursorLength, startX+cursorLength, startY-0.3*cursorLength);
	 	//letterC.addLine(a);
		//canvas.add(a);
		//GLine b = new GLine(startX, startY, startX, startY+cursorLength);
		//letterC.addLine(b);
		//canvas.add(b);
		//GLine c = new GLine(startX, startY-cursorLength, startX+cursorLength, startY-cursorLength);


	}

*/

}
