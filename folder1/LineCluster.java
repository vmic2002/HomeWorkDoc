import java.util.ArrayList;

import acm.graphics.GLine;

public class LineCluster{
		private ArrayList<GLine> lines;
		//private int id;
		private char ch;
		LineCluster(char ch){
			lines = new ArrayList<GLine>();
			//this.id = counter;
			this.ch = ch;
		
		}
		
		public char getChar() {
			return ch;
		}
		
		public void addLine(GLine line) {
			lines.add(line);
		}
		
		//public int getID() {
		//	return id;
		//}
		public ArrayList<GLine> getLines(){
			return lines;
		}
		
	}