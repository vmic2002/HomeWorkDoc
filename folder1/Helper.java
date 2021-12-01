import java.util.ArrayList;
import java.util.Map;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class Helper {
	static Text text;
	static Cursor cursor;
	static GLabel saveButton;
	static GRect boxSave;
	static GRect scrollBar;
	static Map<Integer, ArrayList<Letter>> textList;
	static CursorCoordinates coord;
	static int height;
	static int width;
	static GCanvas canvas;


	public static void setObjects(Text text1, Cursor cursor1, GLabel saveButton1, GRect boxSave1, GRect scrollBar1, Map<Integer, ArrayList<Letter>> textList1, CursorCoordinates coord1, int getWidth, int getHeight, GCanvas g) {
		text =  text1;
		cursor = cursor1;
		saveButton = saveButton1;
		boxSave =boxSave1;
		scrollBar = scrollBar1;
		textList = textList1;
		coord = coord1;
		height = getHeight;
		width = getWidth;
		canvas = g;
	}
	public static void printOutText() {
		//textList = text.getTextList();
		System.out.println("COL"+coord.col+" ROW"+coord.row);
		if (textList==null||textList.size()==0) {
			return;
		}
		for(Integer key : textList.keySet()) {
			System.out.println("ROW>>"+key+" Size: "+textList.get(key).size());

			for (Letter l: textList.get(key)) {
				System.out.print(" CHAR>>"+l.getLineCluster().getChar());
				//System.out.print("<<ID>>"+l.getGRectID().getID()+"<<Y>>"+l.getGRectID().getY()+"<<X>>"+l.getGRectID().getX()+"<<");

			}
			System.out.println("");

		}
	}
	/*
	@param i - is -1 for upKey and 1 for downKey
	 */
	public static void upOrDownKey(int i){
			ArrayList<Letter> letters = textList.get(Integer.valueOf(coord.row+i));
			if (letters!=null) {
				if (letters.size()==0) {
					cursor.move(0, i*cursor.getHeight());
					cursor.changeLocation(0, cursor.getY());
					coord.col = 0;
				} else if (letters.size()-1>=coord.col) {
					cursor.move(0, i*cursor.getHeight());
				} else {
					Letter l = letters.get(letters.size()-1);
					cursor.changeLocation(l.getGRectID().getX()+cursor.getWidth(),l.getGRectID().getY());
					coord.col = (int) (cursor.getX()/cursor.getWidth());
				}
			}else {
				cursor.move(0, i*cursor.getHeight());
				cursor.changeLocation(0, cursor.getY());
				coord.col =0;
			}
			coord.row+=i;
	}

	public static void upKey() {
		if(cursor.getY()>=cursor.getHeight()) {
			upOrDownKey(-1);
		}
	}
	public static void downKey() {
		if (cursor.getY()<height-3*cursor.getHeight()) {//is 3*cursor.getHeight() so that last row is reserved for buttons to save file and load file
			upOrDownKey(1);
		}
	}

	public static void enter() {
		//textList = text.getTextList();
		//	for (Integer key: textList.keySet()) {
		//	System.out.println("ROW>>"+key);
		//	ArrayList<Letter> letters = textList.get(key);
		//		if (letters.size()>0) {
		//			for (Letter letter :letters) {
		//			System.out.print("CHAR>>" + letter.getLineCluster().getChar()+" ");
		//			}
		//		System.out.println();
		//	}
		//	}
		//check if grect at bigger row (lower) than cursor could go one more down
		//for now enter Key makes cursor go down and to first location
		System.out.println("ENTER WAS HIT");
		if (textList.size()!=0) {
			if (textList.get(Integer.valueOf(textList.size()-1))!=null) {
				if (textList.get(Integer.valueOf(textList.size()-1)).size()!=0) {
					double y = textList.get(Integer.valueOf(textList.size()-1)).get(0).getGRectID().getY();
					if (y>=coord.row*cursor.getHeight()&&y<height-cursor.getWidth()*2) {
						System.out.println("LETTERS SHOULD GO DOWN?");
						//DOESNT WORK IF THERE IS JUST ONE LINE IE LETTAB.SIZE()==1
						if (textList.size()>2) {
							textList.put(Integer.valueOf(textList.size()), textList.get(Integer.valueOf(textList.size()-1)));
							ArrayList<Letter> letters1 = textList.get(Integer.valueOf(textList.size()-1));
							if (letters1.size()>0) {
								for (int  j=0; j<letters1.size(); j++) {
									letters1.get(j).move(0, cursor.getHeight());
								}
							}
							for(int i = textList.size()-2; i>coord.row+1; i--) {
								ArrayList<Letter> letters = textList.get(Integer.valueOf(i-1));
								if (letters.size()>0) {
									for (int  j=0; j<letters.size(); j++) {
										letters.get(j).move(0, cursor.getHeight());
									}
								}
								textList.put(Integer.valueOf(i), textList.get(Integer.valueOf(i-1)));
							}
						}

						ArrayList<Letter> temp = new ArrayList<Letter>();
						ArrayList<Letter> temp1 = textList.get(Integer.valueOf(coord.row));
						if (textList.size()==2) {
							textList.put(Integer.valueOf(textList.size()), textList.get(Integer.valueOf(textList.size()-1)));
						}


						if (textList.get(Integer.valueOf(coord.row)).size()>coord.col){


							int x1 = (int) (temp1.get(coord.col).getGRectID().getX()/cursor.getWidth());


							for (int i=coord.col; i<temp1.size(); i++) {

								System.out.println(">>"+temp1.get(i).getGRectID().getID());
								temp.add(temp1.get(i));
								//System.out.println("x1: "+x1+ " GRectID X: "+ temp1.get(i).getGRectID().getX() + "cursor width: "+cursor.getWidth());
								temp1.get(i).move(-x1*cursor.getWidth(),cursor.getHeight());

							}
							textList.put(Integer.valueOf(coord.row+1), temp);
							ArrayList<Letter> temp2 = new ArrayList<Letter>();
							ArrayList<Letter> temp3 = textList.get(Integer.valueOf(coord.row));
							for (int i=0; i<coord.col; i++) {
								temp2.add(temp3.get(i));

							}
							textList.put(Integer.valueOf(coord.row), temp2);
						}

					}
				}


			}

		}

		moveCursorDownAndLeft();
	}

	public static void deleteKey() {
		if (cursor.getX()==0) {
			/*
			 * if row isnt null:make sure that word to the right of cursor GLine should be move to row--
			 * 
			 *  if a row is null :and delete key is pressed, then every line below that row should move up once
			 */
			return;
		}
		//canvas.remove(cursor);

		GObject a = canvas.getElementAt(cursor.getX()-0.4*cursor.getWidth(), cursor.getY()+0.6*cursor.getHeight());
		//cursor.getX()+cursor.getWidth()/2, cursor.getY()+cursor.getHeight()/2);
		//canvas.add(cursor); 

		//textList = text.getTextList();
		if (a!=null){
			int index2;
			if (a instanceof GLine) {
				canvas.remove(a);
				canvas.remove(cursor);
				GObject b = canvas.getElementAt(cursor.getX()-0.4*cursor.getWidth(), cursor.getY()+0.6*cursor.getHeight());
				canvas.add(cursor);
				canvas.add(a);
				int id = ((GRectID) b).getID();
				index2 = text.delete(Integer.valueOf(id), coord.row);
				//remove(b);
			} else{
				System.out.println("GOT TO HERE");
				int id = ((GRectID) a).getID();
				index2 = text.delete(Integer.valueOf(id), coord.row);
				//remove(a);
			}
			ArrayList<Letter> tempList = textList.get(Integer.valueOf(coord.row));
			for (int i= index2; i<tempList.size(); i++) {
				tempList.get(i).move(-cursor.getWidth(), 0);
			}

			moveCursorLeft();
		}
	}

	public static void leftKey() {
		moveCursorLeft();//System.out.println("COLUMN>>"+col);
	}

	public static void rightKey() {
		if (cursor.getX()<width-cursor.getWidth()) {
			canvas.remove(cursor);
			GObject o1 = canvas.getElementAt(cursor.getX()+cursor.getWidth()/2, cursor.getY()+cursor.getHeight()/2);
			canvas.add(cursor);

			if (o1!=null) {
				moveCursorRight();
			} 
		}
	}
	public static void addLetter(char c) {

		canvas.remove(cursor);
		GObject o = canvas.getElementAt(cursor.getX()+cursor.getWidth()/2, cursor.getY()+cursor.getHeight()/2);
		canvas.add(cursor);
		ArrayList<Letter> letters;
		if (o!=null) {//if null then must not have Letter there 
			//textList = text.getTextList();
			letters = textList.get(Integer.valueOf(coord.row));
			for (int i=coord.col; i<letters.size();i++) {
				letters.get(i).move(cursor.getWidth(), 0);
			}
		}
		text.addLetter(cursor.getX(), cursor.getY()+cursor.getWidth(), cursor.getWidth(), Integer.valueOf(coord.row), c, coord.col);
		/*
		 * problem of adding last letter to next row could have to do with an arraylist being 
		 * referenced by two keys when you are copying them to move every row down, if both keys
		 * have same value, then chaning the value will change it for both keys. this could be
		 * potential problem
		 */
		checkIfTooManyLettersInLine();
		
	}


	public static void addLastLetterToNextLine(Letter lastLetter) {
		lastLetter.move(0, cursor.getHeight());
		while (lastLetter.getGRectID().getX()>0)
			lastLetter.move(-cursor.getWidth(), 0);
		//lastLetter.setLocation(0.0,  lastLetter.getGRectID().getY());
		ArrayList<Letter> listLetters = textList.get(Integer.valueOf(coord.row+1));
		if (listLetters==null)
			listLetters = new ArrayList<Letter>();
		//ArrayList<Letter> listLetters = new ArrayList<Letter>();
		for (Letter l:listLetters)
			System.out.println(">>"+l.getLineCluster().getChar());
		listLetters.add(0,lastLetter);
		textList.put(Integer.valueOf(coord.row+1), listLetters);
		if (listLetters.size()>1) {
			for (int i=1; i<listLetters.size(); i++) {
				listLetters.get(i).move(cursor.getWidth(), 0);
			}
				
		}
		listLetters = textList.get(Integer.valueOf(coord.row));
		listLetters.remove(lastLetter);
		textList.put(Integer.valueOf(coord.row), listLetters);
		
		
		//checkIfTooManyLettersInLine();
	}
	
	public static void checkIfTooManyLettersInLine() {
		ArrayList<Letter> letters = textList.get(Integer.valueOf(coord.row));
		Letter lastLetter = letters.get(letters.size()-1);
		if (lastLetter.getGRectID().getX()>=width-cursor.getWidth()) {
			int row = textList.size()-1;
			for (int i=row; i>=0;  i--) {
				if (textList.get(Integer.valueOf(row)).size()!=0) {
					row = i;
					break;
				}
				
			}
			if (textList.get(Integer.valueOf(row)).get(0).getGRectID().getY()==lastLetter.getGRectID().getY()) {
				if (lastLetter.getGRectID().getY()<height-2*cursor.getWidth()) {
					System.out.println("NO PROBLEM");
					addLastLetterToNextLine(lastLetter);
				} else {
					System.out.println("case 1: letter cant go down cause there isnt enough space. Need to create new page below.");
				}
			} else {
				double y = textList.get(Integer.valueOf(textList.size()-1)).get(0).getGRectID().getY();
				if (y<height-cursor.getWidth()*2) {


					ArrayList<Letter> letters1 = textList.get(Integer.valueOf(textList.size()-1));
					
					if (letters1.size()>0) {
						for (int  j=0; j<letters1.size(); j++) {
							letters1.get(j).move(0, cursor.getHeight());
						}
					}
					textList.put(Integer.valueOf(textList.size()), textList.get(Integer.valueOf(textList.size()-1)));
					
					for(int i = textList.size()-2; i>coord.row+1; i--) {
						letters = textList.get(Integer.valueOf(i-1));
						if (letters.size()>0) {
							for (int  j=0; j<letters.size(); j++) {
								letters.get(j).move(0, cursor.getHeight());
							}
						}
						textList.put(Integer.valueOf(i), textList.get(Integer.valueOf(i-1)));
					}
					
					addLastLetterToNextLine(lastLetter);

				} else {
					System.out.println("case 2: letter cant go down cause there isnt enough space. Need to create new page below.");
				}
			}
			/*
			 * make last letter go down and all the way left, all rows below must be translated down one row
			 */
		}
		if (cursor.getX()<width-2*cursor.getWidth()) {
			moveCursorRight();

		} else {
			//return
			moveCursorDownAndLeft();
		}
	}

	
	public static void moveCursorRight() {
		if(cursor.getX()<width-2*cursor.getWidth()) {
			cursor.move(cursor.getWidth(), 0);
			coord.col++;
			//System.out.println("COL>>"+col);
		}
	}
	public  static void moveCursorLeft() {
		if(cursor.getX()>=cursor.getWidth()) {
			cursor.move(-cursor.getWidth(), 0);
			coord.col--;

		}
	}
	public static void moveCursorDownAndLeft() {
		if (cursor.getY()<height-3*cursor.getHeight()) {//is 3*cursor.getHeight() so that last row is reserved for buttons to save file and load file
			cursor.move(0, cursor.getHeight());
			cursor.changeLocation(0, cursor.getY());
			coord.row++;
			coord.col = 0;
		}
	}
}

