import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Color;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class Helper {
	static Text text;
	static Cursor cursor;
	//TODO
	static GLabel saveButton;
	//TODO
	static GRect boxSave;
	//TODO

	static Map<Integer, ArrayList<Letter>> textList;
	static CursorCoordinates coord;
	static int height;
	static int width;
	static GCanvas canvas;
	static GRect fileWindow;
	static GRect closeFileWindowButton;
	static Boolean inFileWindow;
	static ArrayList<Letter> filePath;
	static GLabel fileWindowText;
	static GRect saveToFileButton;

	static int lastRow;//last row cursor can navigate to
	static GRect upButton;
	static GRect downButton;
	static int smallestRow;


	/*
	 *
	 *  if you add a setGCanvas method you could have the option to click to different docs this
	 * would mean creating one more HashMap textList for each new doc
	 * could maybe handle UI where one could see each doc as a square and
	 * be able to click on it (main menu). Include side tabs along left side of doc to
	 * access them.
	 *
	 *
	 *
	 * HomeWordDoc does not change to scale
	 *
	 *
	 *
	 *
	 * BUG WHEN ADDING LETTER TO LAST POSSIBLE ROW ON SCREEN WHEN OTHER LETTERS ARE IN FRONT OF IT
	 * WILL INCREASE LASTROW BY ONE WHEN IT SHOULDNT
	 * bug is slightly fixed in checkiftoomanyletteras...
	 *hasmap of hashmap
	 *
	 *
	 *MAYBE USE JPA OR SOMETHING TO USE DATABASES IN THIS PROJECT COULD BE COOL
	 *could implement auto correct or something
	 *
	 *when at row 0 col 0, hitting left key does nothing but it should move the text down if smallestRow<0
	 *and move cursor to appropriate position
	 *
	 *BE ABLE TO DO COMMANT T AND COMMAND W TO OPEN OTHER WINDOWS OF TEXT DOCUMENTS (COULD HAVE A COMMAND THAT OPEN ANOTHER
	 *WINDOW BUT A GAME SHOWS UP. YOU COULD DO COMMAND W TO GET RID OF IT AND GO BACK TO TEXT WINDOW BUT THIS GAME COUKD BE
	 *THE NEXT BIG PROJECT U DO
	 */

	public static void setObjects(Text text1, Cursor cursor1, GLabel saveButton1, GRect boxSave1, GRect upButton1, 
			GRect downButton1, Map<Integer, ArrayList<Letter>> textList1, CursorCoordinates coord1, int getWidth, 
			int getHeight, GCanvas g, GRect fileWindow1, GRect closeFileWindowButton1, Boolean inFileWindow1,
			ArrayList<Letter> filePath1, GLabel fileWindowText1, GRect saveToFileButton1) {
		text = text1;
		cursor = cursor1;
		saveButton = saveButton1;
		boxSave = boxSave1;
		upButton = upButton1;
		downButton = downButton1;
		textList = textList1;
		coord = coord1;
		height = getHeight;
		width = getWidth;
		canvas = g;
		lastRow = 0;
		smallestRow = 0;
		fileWindow = fileWindow1;
		closeFileWindowButton = closeFileWindowButton1;
		inFileWindow = inFileWindow1;
		filePath = filePath1;
		fileWindowText = fileWindowText1;
		saveToFileButton = saveToFileButton1;
	}

	public static void printOutText() {

		System.out.println("COL" + coord.col + " ROW" + coord.row);
		if (textList == null) {
			System.out.println("textListNull");
			return;
		}
		if (textList.size() == 0) {
			System.out.println("textlist size = 0");
			return;
		}
		for (Integer key : textList.keySet()) {
			int size;
			if (textList.get(key) == null)
				size = 0;
			else
				size = textList.get(key).size();
			System.out.println("ROW>>" + key + " Size: " + size);
			if (textList.get(key) != null) {
				for (Letter l : textList.get(key)) {
					System.out.print(" CHAR>>" + l.getLineCluster().getChar());
					//System.out.print("<<ID>>"+l.getGRectID().getID()+"<<Y>>"+l.getGRectID().getY()+"<<X>>"+l.getGRectID().getX()+"<<");

				}
			}
			System.out.println("");
		}
		System.out.println("LAST ROW IS " + lastRow);
		System.out.println("SMALLEST ROW IS " + smallestRow);
		System.out.println("WIDTH: "+width);
		System.out.println("HEIGHT: "+height);
		System.out.println("Cursor width: "+cursor.getWidth());
		System.out.println("Cursor height: "+cursor.getHeight());
		//System.out.println((cursor.getY() < height-cursor.getHeight()) + "<<>>"+ (coord.row == lastRow));

	}

	/**
	 * @param i - is -1 for upKey and 1 for downKey
	 */
	public static void upOrDownKey(int i) {
		ArrayList<Letter> letters = textList.get(Integer.valueOf(coord.row + i));

		if (i == 1 && coord.row >= lastRow)//cant go down if on last line using down key
			return;
		if (letters != null) {
			if (letters.size() == 0) {

				cursor.move(0, i * cursor.getHeight());
				cursor.changeLocation(0, cursor.getY());
				coord.col = 0;
			} else if (letters.size() - 1 >= coord.col) {
				cursor.move(0, i * cursor.getHeight());
			} else {
				Letter l = letters.get(letters.size() - 1);
				cursor.changeLocation(l.getGRectID().getX() + cursor.getWidth(), l.getGRectID().getY());
				coord.col = (int) (cursor.getX() / cursor.getWidth());
			}
		} else {
			cursor.move(0, i * cursor.getHeight());
			cursor.changeLocation(0, cursor.getY());
			coord.col = 0;
		}
		coord.row += i;
	}

	public static void upKey() {
		if (inFileWindow)
			return;
		if (cursor.getY() >= cursor.getHeight()) {
			upOrDownKey(-1);
		} else if (smallestRow < 0) {
			moveTextDown(smallestRow);
			smallestRow++;
			upAndDownButtonHelper();
			//System.out.println("SHOULD MOVE TEXT DOWN");
		}
	}


	public static void downKey() {
		if (inFileWindow)
			return;
		if (cursor.getY() < height - cursor.getHeight()) {
			upOrDownKey(1);
		} else if (lastRow >= height / cursor.getHeight()) {
			moveTextUp();
			upAndDownButtonHelper();
			//System.out.println("SHOULD MOVE TEXT UP");
		}
	}

	public static void leftKey() {
		if (inFileWindow) {
			if ((int) (cursor.getX()/cursor.getWidth()) > (int) ((width/4)/cursor.getWidth())+1)
				cursor.move(-cursor.getWidth(), 0);//not using moveCursorLeft() because there is no need to update coord.row or coord.col in save mode
			return;
		}
		if (coord.col > 0) {
			moveCursorLeft();
		} else if (coord.row > 0) {
			ArrayList<Letter> letters = textList.get(Integer.valueOf(coord.row - 1));
			int column;
			if (letters == null || letters.size() == 0) {
				column = 0;
			} else {
				column = letters.size();
			}
			cursor.changeLocation(column * cursor.getWidth(), cursor.getY() - cursor.getHeight());
			coord.col = column;
			coord.row--;
		}
	}

	public static void rightKey() {
		if (inFileWindow) {
			int currentCol  = (int) (cursor.getX()/cursor.getWidth()) - (int) ((width/4)/cursor.getWidth())-1;
			if (cursor.getX() < width*3/4 - 2*cursor.getWidth()&&currentCol<filePath.size())
				cursor.move(cursor.getWidth(), 0);//not using moveCursorRight() because there is no need to update coord.row or coord.col in save mode
			return;
		}
		if (cursor.getX() <= width - cursor.getWidth()) {
			canvas.remove(cursor);
			GObject o1 = canvas.getElementAt(cursor.getX() + cursor.getWidth() / 2, cursor.getY() + cursor.getHeight() / 2);
			canvas.add(cursor);
			if (o1 != null) {
				moveCursorRight();
			} else if (coord.row != lastRow) {//reason behind 'if': rightKey shouldn't move cursor down if it is on last row (only return key can)
				moveCursorDownAndLeft();
			}
		}
	}

	public static void enter() {
		System.out.println("ENTER WAS HIT");
		System.out.println("SIZE:" + textList.size());
		if (textList.size() != 0) {
			//from coord.row+1 to last row, move each row down and reassign to correct row in textList
			for (int i = lastRow + 1; i > coord.row + 1; i--) {
				if (textList.get(Integer.valueOf(i - 1)) == null) {
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println("BUG 1 WAS HAPPENING HERE AHAHAHAHAHAHHAhAHAHAHHA");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
				} else {
					for (Letter l : textList.get(Integer.valueOf(i - 1))) {
						l.move(0, cursor.getHeight());
					}
				}
				textList.put(Integer.valueOf(i), textList.get(Integer.valueOf(i - 1)));
			}
			//if (lastRow==coord.row && coord.row==(int)(height/cursor.getHeight()))

			ArrayList<Letter> letters = new ArrayList<Letter>();
			ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row));
			if (temp != null) {
				//System.out.println("BUG 2 WAS HAPPENING HERE AHAHAHAHAHAHHAhAHAHAHHA");//in for loop below

				for (int i = coord.col; i < temp.size(); i++) {
					letters.add(temp.get(i));
				}
				//WHAT IF LETTERS.SIZE()==0??
				if (letters.size() != 0) {
					double dx = -letters.get(0).getGRectID().getX();

					for (Letter l : letters) {
						//if ((int)(height/cursor.getHeight())==coord.row){
						//	l.move(dx, 0);
						//}else {
						l.move(dx, cursor.getHeight());
						//}
						System.out.println(l.getLineCluster().getChar() + " <<char XXXXXXXXXXXXX x>>" + l.getGRectID().getX() + " row>>" + l.getGRectID().getY() / cursor.getHeight());
						System.out.println("CURSOR ROW: " + coord.row);
					}
				}
			}

			//if ((int)(height/cursor.getHeight())==coord.row) {
			//	textList.put(Integer.valueOf(coord.row), letters);
			//} else {
			//if (coord.row+1>(int)(height/cursor.getHeight()))
			//	lastRow++;
			textList.put(Integer.valueOf(coord.row + 1), letters);
			//}
			ArrayList<Letter> temp1 = new ArrayList<Letter>();
			for (int i = 0; i < coord.col; i++) {
				temp1.add(temp.get(i));
				System.out.println("CHAR ADDED IS :" + temp.get(i).getLineCluster().getChar());
			}
			//if ((int)(height/cursor.getHeight())==coord.row) {
			//	textList.put(Integer.valueOf(coord.row+1), temp1);
			//} else {
			textList.put(Integer.valueOf(coord.row), temp1);
			//	}
			//

		}


		System.out.println("LAST ROW:" + lastRow + " coord.row:" + coord.row + " <<>>" + height / cursor.getHeight());
		if (lastRow == (int) (height / cursor.getHeight()) && coord.row == lastRow) {
			//System.out.println("LAST ROW>>" + lastRow + " iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii>>" + height / cursor.getHeight());
			moveTextUp();
			cursor.changeLocation(0, cursor.getY());
			coord.col = 0;
			//lastRow+=2;
			lastRow++;//to counter lastRow-- in moveTextUp()
			System.out.println("GOT TO THIS LINENEENENENENENNENE");
			ArrayList<Letter> temp = textList.get(Integer.valueOf((int) (height / cursor.getHeight()) + 1));
			for (Letter l : temp)
				l.move(0, -cursor.getHeight());
			textList.put(Integer.valueOf((int) (height / cursor.getHeight())), temp);
			textList.put(Integer.valueOf((int) (height / cursor.getHeight()) + 1), null);
			return;


		} else {
			if (coord.row != lastRow)//if coord.row==lastRow then lastRow++ will be done in moveCursorDownAndLeft()
				lastRow++;//need to do this since enter key moves each line of letters down
			moveCursorDownAndLeft();
		}
	}


	public static void deleteKey() {
		if (inFileWindow) {
			deleteKeyInSaveMode();
			return;
		}
		GObject a = canvas.getElementAt(cursor.getX() - 0.4 * cursor.getWidth(), cursor.getY() + 0.6 * cursor.getHeight());
		if (a != null) {
			int index2;
			if (a instanceof GLine) {
				canvas.remove(a);
				canvas.remove(cursor);
				GObject b = canvas.getElementAt(cursor.getX() - 0.4 * cursor.getWidth(), cursor.getY() + 0.6 * cursor.getHeight());
				canvas.add(cursor);
				canvas.add(a);
				int id = ((GRectID) b).getID();
				index2 = text.delete(Integer.valueOf(id), coord.row);
			} else {
				int id = ((GRectID) a).getID();
				index2 = text.delete(Integer.valueOf(id), coord.row);
			}
			ArrayList<Letter> tempList = textList.get(Integer.valueOf(coord.row));
			for (int i = index2; i < tempList.size(); i++) {
				tempList.get(i).move(-cursor.getWidth(), 0);
			}
			moveCursorLeft();
		} else {
			System.out.println("OBJECT IS NULL");
			if (coord.col == 0) {//might not be needed since if a==null normally cursor should be at col 0
				if (coord.row > 0) {
					//make cursor go to last col on coord.row-1
					double x;
					double y = cursor.getY() - cursor.getHeight();
					if (textList.get(Integer.valueOf(coord.row - 1)) == null || textList.get(Integer.valueOf(coord.row - 1)).size() == 0) {
						x = 0.0;
					} else {
						ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row - 1));
						x = temp.get(temp.size() - 1).getGRectID().getX() + cursor.getWidth();
						coord.col = (int) (x / cursor.getWidth());
					}
					coord.row--;
					System.out.println("NEW CURSOR X >>" + x + " NEW CURSOR Y >> " + y);
					System.out.println("NEW coord.col >>" + coord.col + " NEW COORD.ROW >> " + coord.row);
					cursor.changeLocation(x, y);

					ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row + 1));
					if (temp == null || temp.size() == 0) {
						moveRowsUpDeleteHelperMethod(2);
					} else {
						ArrayList<Letter> lettersOnCursorRow = textList.get(Integer.valueOf(coord.row));
						boolean isntFull = false;
						int numFreeSpaces = (int) (width / cursor.getWidth() - 1) - lettersOnCursorRow.size();
						System.out.println("numfreespaces=" + numFreeSpaces);
						if (lettersOnCursorRow == null || numFreeSpaces > 0)
							isntFull = true;
						if (isntFull) {
							/* take as many chars from left to right at coord.row+1 and concatenate it
                        to end of coord.row and also move all letters on coord.row+1
                            that arent concatenated all the way to the left
                            (method already does this)
							 */
							int j = 0;
							for (int i = 0; j < numFreeSpaces; i++) {
								if (i >= temp.size()) {
									break;
								}
								Letter l = temp.remove(i);
								lettersOnCursorRow.add(l);
								l.setLocation(width - cursor.getWidth() * (numFreeSpaces - j + 1), coord.row * cursor.getHeight());
								i--;
								j++;
							}
							if (temp.size() > 0) {
								for (int i = 0; i < temp.size(); i++) {
									temp.get(i).setLocation(cursor.getWidth() * i, (coord.row + 1) * cursor.getHeight());
								}
							} else {
								moveRowsUpDeleteHelperMethod(2);
							}
						} else {
							/*delete last char on coord.row and move first char from coord.row+1 and put it at end of coord.row (cursor should be on this char)
                                then move each char on coord.row+1 to the left (code below does this) recursively*/
							deleteKey();
							Letter l = temp.remove(0);
							for (int i = 0; i < temp.size(); i++) {
								temp.get(i).move(-cursor.getWidth(), 0);
							}
							lettersOnCursorRow.add(l);
							l.setLocation(cursor.getX(), cursor.getY());
						}
					}
				} else {
					ArrayList<Letter> temp = textList.get(Integer.valueOf(0));
					if (smallestRow < 0) {

						moveTextDown(smallestRow);
						smallestRow++;
						cursor.move(0, cursor.getHeight());
						coord.row++;
						deleteKey();

						//lastRow--;//to counter lastRow++ in moveTextDown
						//	cursor.move(0,  cursor.getHeight());
						//	deleteKey();
						//moveCursorToRightmostPosition();
					} else if (coord.row == 0 && temp != null && temp.size() == 0) {
						System.out.println("ON ROW ZERO MOVING ROWS BELOW IT UP");

						if (lastRow != 0)
							moveRowsUpDeleteHelperMethod(1);

					}
				}
			}
		}
	}

	public static void moveRowsUpDeleteHelperMethod(int j) {
		for (int i = coord.row + j; i <= lastRow; i++) {
			ArrayList<Letter> temp1 = textList.get(Integer.valueOf(i));
			if (temp1 != null) {
				for (Letter l : temp1) {
					l.move(0, -cursor.getHeight());
				}
			}
			textList.put(Integer.valueOf(i - 1), temp1);
		}
		System.out.println("LAST ROW IS :" + lastRow);
		textList.put(Integer.valueOf(lastRow), null);
		lastRow--;
	}

	public static void deleteKeyInSaveMode() {
		int currentCol  = (int) (cursor.getX()/cursor.getWidth()) - (int) ((width/4)/cursor.getWidth())-1;
		//currentCol = i refers to ith index of filePath
		if (currentCol>0) {
			//does nothing if currentCol is 0
			//delete key at current col and move all keys that need to be moved to the left
			System.out.println("CURRENT COL is: "+currentCol);
			deleteLetter(currentCol-1);
			for (int i = currentCol-1; i<filePath.size(); i++)
				filePath.get(i).move(-cursor.getWidth(), 0);
			cursor.move(-cursor.getWidth(), 0);
			//not using moveCursorLeft() because there is no need to update coord.row or coord.col in save mode
		}
	}

	/**
	 * 
	 * @param key index of value to delete in ArrayList<Letter> filePath
	 */
	public static void deleteLetter(int key) {
		//System.out.println("KEY IS : "+ key);
		Letter letter = filePath.get(key);
		GRectID grectID = letter.getGRectID();
		LineCluster cluster = letter.getLineCluster();
		for (GLine line:cluster.getLines())
			canvas.remove(line);
		canvas.remove(grectID);
		filePath.remove(letter);

	}


	public static void addLetter(char c) {
		//NEED TO: if inFileWindow -> add letter to canvas but also to ArrayList<Letter> filePath

		//if inFileWindow is false, then HomeWorkDoc is in editing mode, not saving mode

		if (inFileWindow)
			addLetterSavingMode(c);
		else
			addLetterEditingMode(c);
	}

	public static void addLetterSavingMode(char c) {

		//BUG NOT REMOVING LAST LETTER OF FILEPATH CORRECTLY
		canvas.remove(cursor);
		GObject o = canvas.getElementAt(cursor.getX() + cursor.getWidth() / 2, cursor.getY() + cursor.getHeight() / 2);
		canvas.add(cursor);

		int currentCol  = (int) (cursor.getX()/cursor.getWidth()) - (int) ((width/4)/cursor.getWidth())-1;
		if (o != null) {//if null then must not have <Letter> there


			for (int i = currentCol; i < filePath.size(); i++) {
				filePath.get(i).move(cursor.getWidth(), 0);
				System.out.println("LETTER IS :" + filePath.get(i).getLineCluster().getChar());
			}
			//checkIfTooManyLettersInLine
			if (filePath.size()>=(width/2)/cursor.getWidth()-2) {
				//NEED TO DELETE last element in filePath
				deleteLetter(filePath.size()-1);
			}
		}

		text.addLetterSaveMode(cursor.getX(), cursor.getY() + cursor.getWidth(), cursor.getWidth(), c, filePath, currentCol);
		if (cursor.getX() < width*3/4-2*cursor.getWidth())  {
			cursor.move(cursor.getWidth(), 0);
			//not using moveCursorRight() because there is no need to update coord.row or coord.col in save mode
		}//else the cursor can only move left if user hits the left key
	}


	public static void addLetterEditingMode(char c) {

		if (coord.col == width / cursor.getWidth() - 1) {
			moveCursorDownAndLeft();
			//System.out.println("1111111111ERRORORORROROROROROROROROROROROROR");
		}
		canvas.remove(cursor);
		GObject o = canvas.getElementAt(cursor.getX() + cursor.getWidth() / 2, cursor.getY() + cursor.getHeight() / 2);
		canvas.add(cursor);
		ArrayList<Letter> letters;

		if (o != null) {//if null then must not have <Letter> there
			letters = textList.get(Integer.valueOf(coord.row));
			for (int i = coord.col; i < letters.size(); i++) {
				letters.get(i).move(cursor.getWidth(), 0);
				//System.out.println("LETTER IS :" + letters.get(i).getLineCluster().getChar());
			}
			checkIfTooManyLettersInLine(coord.row);
		}

		//System.out.println("COLLLLLLLL: " + coord.col + " letter: " + c);
		text.addLetterEditMode(cursor.getX(), cursor.getY() + cursor.getWidth(), cursor.getWidth(), Integer.valueOf(coord.row), c, coord.col);
		//checkIfTooManyLettersInLine();
		if (cursor.getX() < width - 2 * cursor.getWidth()){
			moveCursorRight();
		} else {
			//System.out.println("GOT HEREREREREERERERRERERERRERERET");


			//System.out.println("HEIGHT/CURSORHEIGHT>>" + height / cursor.getHeight());
			//System.out.println("CURSOR ROW>>" + coord.row);
			if (coord.row == (int) (height / cursor.getHeight())) {
				moveTextUp();
				lastRow++;//to counter the lastRow-- in moveTextUp
				//cursor.setLocation(0, cursor.getY());
				cursor.changeLocation(0, cursor.getY());
				coord.col = 0;

			} else {
				moveCursorDownAndLeft();
				//System.out.println("22222222ERRORORORROROROROROROROROROROROROR");
			}
			ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row));
			if ((temp == null || temp.size() == 0) && coord.row < lastRow) {
				moveTextDown(coord.row + 1);
			}
		}
	}

	public static void checkIfTooManyLettersInLine(int row) {

		/*
        all that needs to be done here is bring lastLetter to front of coord.row+1 and move letters on coord.row+1 to the right
        if there is not enough space on coord.row+1 than take last letter of coord.row+1 and put it to front of coord.row+2
        if there is not enough space on coord.row+2...
		 */
		if (row > lastRow) {
			return;
			//	System.out.println("row>lastROW in check if too many ...");
		}
		ArrayList<Letter> letters = textList.get(Integer.valueOf(row));
		Letter lastLetter = letters.get(letters.size() - 1);
		if (lastLetter.getGRectID().getX() < width - cursor.getWidth()) {//base case
			return;
		} else {
			letters.remove(letters.size() - 1);
			letters = textList.get(row + 1);
			if (letters == null || letters.size() == 0) {
				letters = new ArrayList<Letter>();
				if (row < lastRow) {
					//move all letters below by one row
					moveTextDown(row + 1);

				} else {
					lastRow++;//
					/*
					 * this fixes te bug where adding a letter to row 14 at not the last col doenst add letters to
					 * the next row
					 * however by fixing this bug, another row is added at the end  which is not what we want
					 */
				}

			}
			letters.add(0, lastLetter);
			lastLetter.setLocation(0, lastLetter.getGRectID().getY() + cursor.getHeight());
			for (int i = 1; i < letters.size(); i++)
				letters.get(i).move(cursor.getWidth(), 0);
			if (row == lastRow && lastRow != (int) (height / cursor.getHeight())) {
				lastRow++;


			}
			for (Letter l : letters) {
				System.out.println("char>>" + l.getLineCluster().getChar());
			}
			row++;
			System.out.println("THIS IS WHAT WE ARE INTERESTED INrow>>" + row);
			printOutText();
			textList.put(Integer.valueOf(row), letters);//might not be necessary because of reference type rules
			checkIfTooManyLettersInLine(row);

		}


	}

	/**
	 * moves all rows of text below and including param row down by one row
	 *
	 * @param row
	 */
	public static void moveTextDown(int row) {
		moveTextDown(row, lastRow);
	}

	public static void moveTextDown(int row, int endRow) {
		System.out.println("SHOULD MOVE TEXT DOWN HERERERE");
		for (int i = endRow; i >= row; i--) {
			ArrayList<Letter> temp1 = textList.get(Integer.valueOf(i));
			if (temp1 != null) {
				for (Letter l : temp1) {
					l.move(0, cursor.getHeight());
				}
			}
			textList.put(Integer.valueOf(i + 1), temp1);
		}
		System.out.println("LAST ROW IS :" + lastRow);
		textList.put(Integer.valueOf(row), null);
		lastRow++;
	}

	public static void moveTextUp() {
		System.out.println("SHOULD MOVE TEXT UP HERERERE");
		for (int i = smallestRow; i <= lastRow; i++) {
			ArrayList<Letter> temp1 = textList.get(Integer.valueOf(i));
			if (temp1 != null) {
				for (Letter l : temp1) {
					l.move(0, -cursor.getHeight());
				}
			}
			textList.put(Integer.valueOf(i - 1), temp1);
		}
		textList.put(Integer.valueOf(lastRow), null);
		lastRow--;
		smallestRow--;
	}


	public static void moveCursorRight() {
		if (cursor.getX() < width - cursor.getWidth()) {
			cursor.move(cursor.getWidth(), 0);
			coord.col++;
			//System.out.println("COL>>"+col);
		}
	}

	public static void moveCursorLeft() {
		if (cursor.getX() >= cursor.getWidth()) {
			cursor.move(-cursor.getWidth(), 0);
			coord.col--;
		}
	}

	public static void moveCursorDownAndLeft() {
		//if statement below might not be useful if we want doc to be infinitely long going downwards
		//could maybe have buttons on the right or something
		//if (cursor.getY() < height-cursor.getHeight()) {
		if (cursor.getY() < height - cursor.getHeight()) {
			System.out.println("IN BOUNDSS");
		}
		System.out.println("EXECUTING MOVE CURSOR DOWN AND LEFT");
		cursor.move(0, cursor.getHeight());
		cursor.changeLocation(0, cursor.getY());
		if (coord.row == lastRow)
			lastRow++;
		coord.row++;
		coord.col = 0;
		//}
	}

	public static int getLastRow() {
		return lastRow;
	}

	public static void upAndDownButtonHelper() {
		canvas.remove(cursor);
		GObject o1 = canvas.getElementAt(cursor.getX() + cursor.getWidth() / 2, cursor.getY() + cursor.getHeight() / 2);
		canvas.add(cursor);
		if (o1 == null) {
			System.out.println("OBJECT IS NULLLLLLLLLLLLLLLLLLLLLL");
			moveCursorToRightmostPosition();
		} else {
			System.out.println("OBJECT IS NOTTTTTTTT NULLLLLLLLLLLLLLLLLLLLLL");
		}

	}

	public static void moveCursorToRightmostPosition() {
		ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row));
		int col;
		if (temp == null || temp.size() == 0) {
			col = 0;
		} else {
			col = temp.size();
		}
		System.out.println("COL in move cursor rightmost>> " + col);
		cursor.changeLocation(col * cursor.getWidth(), cursor.getY());
		coord.col = col;
	}

	public static void revertBackToEditingMode() {
		//this function allows user to go back to editing mode
		//from save file mode
		canvas.remove(closeFileWindowButton);
		canvas.remove(fileWindow);
		canvas.remove(fileWindowText);
		canvas.remove(saveToFileButton);
		inFileWindow = false;
		cursor.changeLocation(coord.col*cursor.getWidth(), coord.row*cursor.getHeight());
		//coord.row = 0;
		//coord.col = 0;
		if (filePath.size()!=0) {
			for (int i=filePath.size()-1; i>=0; i--) {
				//System.out.println("INDEX I ISSSS: "+i);
				deleteLetter(i);
			}
		}
	}

	public static void revertBackToSavingMode() {
		//this function allows user to go back to saving mode
		//from edit text mode
		canvas.add(fileWindow);
		canvas.add(closeFileWindowButton);
		canvas.add(saveToFileButton);
		saveToFileButton.sendToFront();
		canvas.add(fileWindowText);
		inFileWindow = true;
		cursor.sendToFront();
		cursor.changeLocation(width/4+cursor.getWidth(),height/4+cursor.getHeight());
		//no need to update coord.row or coord.col because in saving mode they are not used
		//set cursor to appropriate location so user can input file path in reverbackToSaving()
	}

	public static void mouseClicked(MouseEvent e) {
		if (textList.size() == 0)
			return;
		if (inFileWindow) {
			GObject x = canvas.getElementAt(e.getX(), e.getY());
			if (x == null)
				return;
			if (x.equals(closeFileWindowButton)) {
				System.out.println("closeFileWindowButton PRESSED");
				revertBackToEditingMode();
			} else if (x.equals(saveToFileButton)) {
				System.out.println("SAVE TO FILE BUTTON PRESSED");

				String path = "";
				for (Letter l:filePath)//translate ArrayList<Letter> filePath to string
					path+=l.getLineCluster().getChar();
				String textAsString = "";
				System.out.println("Path is: "+path);
				//setTextListToString(textAsString);
				//UNCOMMENT PREVIOUS LINE

				/*
				 * NEED TO: make sure that setTextListToString and FileHelper.writeToFile(textAsString, path)
				 * both work
				 */
				
				//FileHelper.writeToFile(textAsString, path);
				//UNCOMMENT PREVIOUS LINE
				//MAKE SURE MAX SIZE OF STRING IS BIG ENOUGH FOR THE WHOLE TEXT TO GO IN IT
				//figure what is the max number of characters that textList should hold
				//or max number of characters that can be saved to a file

				revertBackToEditingMode();

			}
			return;

		}
		if (e.getX() < width - cursor.getWidth()) {
			int rowT = (int) (e.getY() / cursor.getHeight());
			int column = (int) (e.getX() / cursor.getWidth());
			if (rowT > Helper.getLastRow()) {
				//System.out.println("SCENARIO row>lastRow");

				ArrayList<Letter> lastRow = textList.get(Helper.getLastRow());
				rowT = Helper.getLastRow();
				if (lastRow == null) {
					column = 0;
				} else if (column >= lastRow.size())
					column = lastRow.size();
				cursor.changeLocation(cursor.getWidth() * column, cursor.getHeight() * rowT);
			} else if (getGRectIDAt(1.0 * e.getX(), 1.0 * e.getY()) != null) {
				//  System.out.println("SCENARIO GRECTID IS  NOT  NULL HERE");
				cursor.changeLocation(cursor.getWidth() * column, cursor.getHeight() * rowT);
			} else {
				// System.out.println("SCENARIO GRECTID IS NULL HERE");
				ArrayList<Letter> letters = textList.get(Integer.valueOf(rowT));
				double x;
				if (letters == null || letters.size() == 0) {
					x = 0;
					column = 0;
				} else {
					x = letters.get(letters.size() - 1).getGRectID().getX() + cursor.getWidth();
					column = (int) (x / cursor.getWidth());
				}
				cursor.changeLocation(x, cursor.getHeight() * rowT);
			}
			coord.row = rowT;
			coord.col = column;
		} else {
			GObject x = canvas.getElementAt(e.getX(), e.getY());
			if (x == null)
				return;
			if (x.equals(upButton)) {
				System.out.println("UPBUTTON PRESSED");
				if (smallestRow < 0) {
					moveTextDown(smallestRow);
					smallestRow++;
					if (coord.row < height / cursor.getHeight() - 1) {
						cursor.move(0, cursor.getHeight());
						coord.row++;
						//check to make sure there is a letter there or move cursor to rightmost avaliable position in line

					}
					upAndDownButtonHelper();
					System.out.println("SHOULD MOVE TEXT DOWN");
				}

			} else if (x.equals(downButton)) {
				System.out.println("DOWNBUTTON PRESSED");
				if (lastRow >= height / cursor.getHeight()) {
					moveTextUp();
					if (coord.row > 0) {
						cursor.move(0, -cursor.getHeight());
						coord.row--;
					}

					upAndDownButtonHelper();
					System.out.println("SHOULD MOVE TEXT UP");
				}
			} else if (x.equals(saveButton) || x.equals(boxSave)) {
				System.out.println("SAVE TO FILE HERE");
				revertBackToSavingMode();

				//fileWindow and closeFileWindowButton are only added to canvas (add()) when save button is clicked
			}
		}
	}

	public static void setTextListToString(String str) {
		if (textList == null || textList.size() == 0) {
			str = " ";
			//Feauture: trying to save a file if textlist is empty will create a file that is contains just a space
			return;
		}
		//NEED TO CHECK WHETHER THE NUMBER OF CHARS IS GETTING TOO BIG FOR A SINGLE STRING TO HOLD
		//text.numLetters<=String max size?

		for (Integer key : textList.keySet()) {
			//int size;
			// if (textList.get(key) == null)
			//   size = 0;
			//else
			//  size = textList.get(key).size();
			//System.out.println("ROW>>" + key + " Size: " + size);
			if (key>=smallestRow && key<=lastRow) {
				if (textList.get(key) != null) {
					for (Letter l : textList.get(key)) {
						//System.out.print(" CHAR>>" + l.getLineCluster().getChar());
						//System.out.print("<<ID>>"+l.getGRectID().getID()+"<<Y>>"+l.getGRectID().getY()+"<<X>>"+l.getGRectID().getX()+"<<");
						str+=l.getLineCluster().getChar();
					}
				}
				//System.out.println("");
				str+='\n';
			}
		}
		//NOTE: this method only works if the for loop loops through the textList
		//in increasing order of rows, from smallestRow to lastRow
	}



	public static GRectID getGRectIDAt(double x, double y) {
		canvas.remove(cursor);
		GObject a = canvas.getElementAt(x, y);
		canvas.add(cursor);
		if (a != null) {
			if (a instanceof GLine) {
				canvas.remove(a);
				canvas.remove(cursor);
				GObject b = canvas.getElementAt(x, y);
				canvas.add(cursor);
				canvas.add(a);
				return ((GRectID) b);

			} else {
				return ((GRectID) a);
			}
		}
		return null;
	}



	public static GRectID getGRectIDAtCursor() {
		return getGRectIDAt(cursor.getX() + 0.6 * cursor.getWidth(), cursor.getY() + 0.6 * cursor.getHeight());
	}
}