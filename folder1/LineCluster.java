import java.util.ArrayList;

import acm.graphics.GLine;

public class LineCluster{
		private ArrayList<GLine> lines;
		private char ch;
		LineCluster(char ch){
			lines = new ArrayList<GLine>();
			this.ch = ch;
		}
		public char getChar() {
			return ch;
		}
		
		public void addLine(GLine line) {
			lines.add(line);
		}

		public ArrayList<GLine> getLines(){
			return lines;
		}
	}