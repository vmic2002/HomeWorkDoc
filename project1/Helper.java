import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.awt.Color;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class Helper {
	static Text text;
	static Cursor cursor;
	static Map<Integer, ArrayList<Letter>> textList;
	static CursorCoordinates coord;
	static int height;
	static int width;
	static GCanvas canvas;
	static int lastRow;//last row cursor can navigate to
	static GRect upButton;
	static GRect downButton;
	static int smallestRow;
	static GLabel saveButton;
	static GRect boxSave;

	static GRect fileWindow;
	static GRect closeFileWindowButton;
	static Boolean inFileWindow;
	static Boolean saveOrReadMode;
	static ArrayList<Letter> filePath;
	static GLabel fileWindowText;
	static GRect fileWindowButton;
	static GLabel fileWindowErrorMsg;
	static GLabel saveModeText;
	static GLabel readModeText;
	static GRect modeTextWindow;

	static int editModeCursorSize;
	static int saveModeCursorSize;

	static GLabel readButton;
	static GRect boxRead;






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
	 *
	 *could implement auto correct or something
	 *
	 *when at row 0 col 0, hitting left key does nothing but it should move the text down if smallestRow<0
	 *and move cursor to appropriate position
	 *
	 *BE ABLE TO DO COMMANT T AND COMMAND W TO OPEN OTHER WINDOWS OF TEXT DOCUMENTS (COULD HAVE A COMMAND THAT OPEN ANOTHER
	 *WINDOW BUT A GAME SHOWS UP. YOU COULD DO COMMAND W TO GET RID OF IT AND GO BACK TO TEXT WINDOW BUT THIS GAME COUKD BE
	 *THE NEXT BIG PROJECT U DO
	 *
	 * IMPLEMENT TAB KEY (COULD JUST BE 5 SPACES BUT SPACES CONTAIN A BOOLEAN WHICH IS TRUE IF PART OF TAB)
	 * would be useful to implement tab key if we want to import code into this text editor
	 */


	/**
	 * 
	 * @param text1
	 * @param cursor1
	 * @param saveButton1
	 * @param boxSave1
	 * @param upButton1
	 * @param downButton1
	 * @param textList1
	 * @param coord1
	 * @param getWidth
	 * @param getHeight
	 * @param canvas1
	 * @param fileWindow1
	 * @param closeFileWindowButton1
	 * @param inFileWindow1
	 * @param saveOrReadMode1
	 * @param filePath1
	 * @param fileWindowText1
	 * @param fileWindowButton1
	 * @param editModeCursorSize1
	 * @param saveModeCursorSize1
	 * @param fileWindowErrorMsg1
	 * @param saveModeText1
	 * @param modeTextWindow1
	 * @param readButton1
	 * @param boxRead1
	 * @param readModeText1
	 */
	public static void setObjects(Text text1, Cursor cursor1, GLabel saveButton1, GRect boxSave1, GRect upButton1, 
			GRect downButton1, Map<Integer, ArrayList<Letter>> textList1, CursorCoordinates coord1, int getWidth, 
			int getHeight, GCanvas canvas1, GRect fileWindow1, GRect closeFileWindowButton1, Boolean inFileWindow1, Boolean saveOrReadMode1,
			ArrayList<Letter> filePath1, GLabel fileWindowText1, GRect fileWindowButton1, int editModeCursorSize1,
			int saveModeCursorSize1, GLabel fileWindowErrorMsg1, GLabel saveModeText1, GRect modeTextWindow1,
			GLabel readButton1, GRect boxRead1, GLabel readModeText1) {
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
		canvas = canvas1;
		lastRow = 0;
		smallestRow = 0;
		fileWindow = fileWindow1;
		closeFileWindowButton = closeFileWindowButton1;
		inFileWindow = inFileWindow1;
		saveOrReadMode = saveOrReadMode1;
		filePath = filePath1;
		fileWindowText = fileWindowText1;
		fileWindowButton = fileWindowButton1;
		editModeCursorSize = editModeCursorSize1;
		saveModeCursorSize = saveModeCursorSize1;
		fileWindowErrorMsg = fileWindowErrorMsg1;
		saveModeText = saveModeText1;
		modeTextWindow = modeTextWindow1;
		readButton = readButton1;
		boxRead = boxRead1;
		readModeText = readModeText1;
	}


	public static void printOutText() {
		//System.out.println("COL" + coord.col + " ROW" + coord.row);
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
					System.out.print(l.getLineCluster().getChar());
					//System.out.print("<<ID>>"+l.getGRectID().getID()+"<<Y>>"+l.getGRectID().getY()+"<<X>>"+l.getGRectID().getX()+"<<");

				}
			}
			System.out.println("");
		}
		System.out.println("LAST ROW IS " + lastRow);
		System.out.println("SMALLEST ROW IS " + smallestRow);
		System.out.println("WIDTH: "+width);
		System.out.println("HEIGHT: "+height);
		System.out.println("cursor row: "+coord.row);
		System.out.println("cursor col: "+coord.col);
		//System.out.println("Cursor width: "+cursor.getWidth());
		//System.out.println("Cursor height: "+cursor.getHeight());
		//System.out.println((cursor.getY() < height-cursor.getHeight()) + "<<>>"+ (coord.row == lastRow));

	}

	/**Moves cursor up or down depending on param i
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


	/**
	 * up key pressed
	 */
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

	/**
	 * down key pressed
	 */
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

	/**
	 * left key pressed
	 */
	public static void leftKey() {
		if (inFileWindow) {
			//if ((int) (cursor.getX()/cursor.getWidth()) > (int) ((width/4)/cursor.getWidth())+1)
			if (getCurrentColSaveMode()!=0)
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

	/**
	 * right key pressed
	 */
	public static void rightKey() {
		if (inFileWindow) {
			//int currentCol  = (int) (cursor.getX()/cursor.getWidth()) - (int) ((width/4)/cursor.getWidth())-1;
			//if (cursor.getX() < width*3/4 - 2*cursor.getWidth()&&currentCol<filePath.size())
			if (cursor.getX() < width*7/8 - 2*cursor.getWidth()&&getCurrentColSaveMode()<filePath.size())//7/8 because saveFileWindow is 6/8*Width long
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

	/**
	 * return key pressed
	 */
	public static void enter() {
		if (inFileWindow)
			return;
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


	/**
	 * delete key pressed
	 */
	public static void deleteKey() {
		if (inFileWindow) {
			deleteKeyInSaveMode();
			return;
		}
		//GObject a = canvas.getElementAt(cursor.getX() - 0.4 * cursor.getWidth(), cursor.getY() + 0.6 * cursor.getHeight());
		GRectID a = getGRectIDAt(cursor.getX() - 0.4 * cursor.getWidth(), cursor.getY() + 0.6 * cursor.getHeight());
		if (a != null) {
			int index2 = text.delete(Integer.valueOf(a.getID()), coord.row);
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
		//int currentCol  = (int) (cursor.getX()/cursor.getWidth()) - (int) ((width/4)/cursor.getWidth())-1;

		//currentCol = i refers to ith index of filePath
		int currentCol = getCurrentColSaveMode();
		if (currentCol>0) {
			//does nothing if currentCol is 0
			//delete key at current col and move all keys that need to be moved to the left
			System.out.println("CURRENT COL is: "+currentCol);
			//	deleteLetter(currentCol-1);
			Letter letter = filePath.get(currentCol-1);
			deleteLetter(letter);
			filePath.remove(letter);
			for (int i = currentCol-1; i<filePath.size(); i++)
				filePath.get(i).move(-cursor.getWidth(), 0);
			cursor.move(-cursor.getWidth(), 0);
			//not using moveCursorLeft() because there is no need to update coord.row or coord.col in save mode
		}
	}



	/**
	 * does not remove letter from a data structure (textList or filePath)
	 * @param letter to be removed from canvas
	 */
	public static void deleteLetter(Letter letter) {
		GRectID grectID = letter.getGRectID();
		LineCluster cluster = letter.getLineCluster();
		for (GLine line:cluster.getLines())
			canvas.remove(line);
		canvas.remove(grectID);

	}


	/**
	 * 
	 * @param c char to be added to canvas and textList or filePath
	 */
	public static void addLetter(char c) {

		//if inFileWindow is false, then HomeWorkDoc is in editing mode, not saving mode or read from file mode

		if (inFileWindow)
			addLetterSavingMode(c);
		else
			addLetterEditingMode(c);
	}

	/**
	 * 
	 * @param c char to be added to canvas and filePath
	 */
	public static void addLetterSavingMode(char c) {

		//BUG NOT REMOVING LAST LETTER OF FILEPATH CORRECTLY
		canvas.remove(cursor);
		GObject o = canvas.getElementAt(cursor.getX() + cursor.getWidth() / 2, cursor.getY() + cursor.getHeight() / 2);
		canvas.add(cursor);

		//int currentCol  = (int) (cursor.getX()/cursor.getWidth()) - (int) ((width/4)/cursor.getWidth())-1;
		int currentCol  = getCurrentColSaveMode();
		if (o != null) {//if null then must not have <Letter> there


			for (int i = currentCol; i < filePath.size(); i++) {
				filePath.get(i).move(cursor.getWidth(), 0);
				System.out.println("LETTER IS :" + filePath.get(i).getLineCluster().getChar());
			}
			//checkIfTooManyLettersInLine
			//if (filePath.size()>=(width/2)/cursor.getWidth()-2) {
			if (filePath.size()>=(width*3/4)/cursor.getWidth()-2) {
				//must delete last element in filePath
			}
		}

		text.addLetterSaveMode(cursor.getX(), cursor.getY() + cursor.getWidth(), saveModeCursorSize, c, filePath, currentCol);
		//if (cursor.getX() < width*3/4-2*cursor.getWidth())  {
		if (cursor.getX() < width*7/8-2*cursor.getWidth())  {
			cursor.move(cursor.getWidth(), 0);
			//not using moveCursorRight() because there is no need to update coord.row or coord.col in save mode
		}//else the cursor can only move left if user hits the left key
	}


	/**
	 * 
	 * @param c char to be added to canvas and textList
	 */
	public static void addLetterEditingMode(char c) {

		if (coord.col == width / cursor.getWidth() - 1) {
			if (coord.row == (int) (height / cursor.getHeight())) {
				//WHEN CURSOR IS ON LAST ROW OF GCANVAS AND LAST COL AND LETTERS ARE TYPED THE TEXT SHOULD BE
				//MOVED UP
				moveTextUp();

				lastRow++;//to counter the lastRow-- in moveTextUp
				//NEED TO FIX BUG HERE: LASTROWW++ SHOULD NOT BE DONE IF THERE ARE MORE LINES BELOW THE SCREEN
				//FOR NOW AN EXTRA LINE IS ADDED AT THE END OF TEXTLIST (LASTROW++ IS DONE WHEN IT SHOULDNT BE DONE)

				cursor.changeLocation(0, cursor.getY());
				coord.col = 0;

			} else {
				moveCursorDownAndLeft();
			}
			ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row));
			if ((temp == null || temp.size() == 0) && coord.row < lastRow) {
				System.out.println("THIS SHOUDL HAPPPPPPPPPPEN SOME TIMEMEMEMMEMEME");
				moveTextDown(coord.row + 1);
			}
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
		//if (cursor.getX() < width - 2 * cursor.getWidth()){
		if (cursor.getX() < width -  cursor.getWidth()){
			moveCursorRight();
		} else {
			//MIGHT BE ABLE TO REMOVE THIS WHOLE ELSE STATMENT
			System.out.println("THIS SHOULD NEVER HAPPPPPPPPPPPPPPPPPPPPPPPPPPPEN?????");
			/*
			System.out.println("GOT HEREREREREERERERRERERERRERERET");


			//System.out.println("HEIGHT/CURSORHEIGHT>>" + height / cursor.getHeight());
			//System.out.println("CURSOR ROW>>" + coord.row);
			System.out.println("BUGGGGGGGGGGG");
			System.out.println("coord row: "+ coord.row);
			//System.out.println("last possible row: "+((int) (height / cursor.getHeight())-1));

			if (coord.row == (int) (height / cursor.getHeight())) {
				//THIS IF STATEMENT MIGHT NOT BE NECCESSARY
				//if statement at beginning of method might cover this:
				 //WHEN CURSOR IS ON LAST ROW OF GCANVAS AND LAST COL AND LETTERS ARE TYPED THE TEXT SHOULD BE
				//MOVED UP BUT
				moveTextUp();
				lastRow++;//to counter the lastRow-- in moveTextUp
				//cursor.setLocation(0, cursor.getY());
				cursor.changeLocation(0, cursor.getY());
				coord.col = 0;

			} else {
				moveCursorDownAndLeft();

			}
			ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row));
			if ((temp == null || temp.size() == 0) && coord.row < lastRow) {
				System.out.println("THIS SHOUDL HAPPPPPPPPPPEN SOME TIMEMEMEMMEMEME");
				moveTextDown(coord.row + 1);
			}
			 */
		}
	}

	/**
	 * 
	 * @param row row number to check if last letter in row needs to be moved to beginning of row+1
	 */
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

	/**
	 * move all rows of textList up by one unit of cursorLength
	 */
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

	/**
	 * moves cursor along if text is being moved up or down
	 * so that it is still visible
	 */
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

	/**
	 * move cursor to the right of the rightmost char in row
	 */
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
		canvas.remove(fileWindowButton);
		canvas.remove(fileWindowErrorMsg);
		canvas.remove(modeTextWindow);
		if (saveOrReadMode)
			canvas.remove(saveModeText);
		else
			canvas.remove(readModeText);
		fileWindowErrorMsg.setLabel("");
		inFileWindow = false;
		cursor.setSize(editModeCursorSize);


		//to put cursor back to its original location since coord.col and coord.row dont change while in save mode
		cursor.changeLocation(coord.col*cursor.getWidth(), coord.row*cursor.getHeight());


		//coord.row = 0;
		//coord.col = 0;
		if (filePath.size()!=0) {
			for (int i=filePath.size()-1; i>=0; i--) {
				//System.out.println("INDEX I ISSSS: "+i);
				//deleteLetter(i);
				Letter letter = filePath.get(i);
				deleteLetter(letter);
				filePath.remove(letter);

			}
		}
	}

	/**
	 * 
	 * @param b true if in save mode false if in read/import mode
	 */
	public static void revertBackToFileMode(boolean b) {
		//this function allows user to go back to saving mode
		//from edit text mode
		canvas.add(fileWindow);
		canvas.add(closeFileWindowButton);
		canvas.add(fileWindowButton);
		fileWindowButton.sendToFront();
		canvas.add(fileWindowText);
		canvas.add(fileWindowErrorMsg);
		canvas.add(modeTextWindow);
		if (b)
			canvas.add(saveModeText);
		else
			canvas.add(readModeText);
		inFileWindow = true;
		saveOrReadMode = b;
		System.out.println("SAVE OR READ MODE IS: "+saveOrReadMode);
		cursor.sendToFront();
		cursor.setSize(saveModeCursorSize);
		//cursor.changeLocation(width/4+cursor.getWidth(),height/4+editModeCursorSize);
		cursor.changeLocation(width/8+cursor.getWidth(),height/4+2*editModeCursorSize);
		//no need to update coord.row or coord.col because in saving mode they are not used
		//set cursor to appropriate location so user can input file path in reverbackToSaving()
	}

	/**
	 * 
	 * @param path File path
	 * @return error/success message
	 */
	public static String saveToFile(String path) {
		String textAsString;
		textAsString = setTextListToString();
		return FileHelper.writeToFile(textAsString, path, text.numLetters);
	}

	/**
	 * 
	 * @param path File path
	 * @return error/success message
	 */
	public static String readFromFile(String path) {


		String[] arr = FileHelper.getFileAsString(path);
		System.out.println("PRINTING FILE METHOD");
		System.out.println(arr[0]);
		//if there is error then arr[0] will be null  
		System.out.println("END OF file");
		System.out.println("ERRORMSG: "+arr[1]);
		if (arr[0]!= null) {
			/* TRANSLATE STRING ARR[0] AS TEXT IN HOMEWORKDOC
			 * USING CURSOR AND ADD LETTERIN EDIT MODE
			 * AT END OF EACH LINE USE RETURN KEY
			 * go back to edit mode and translate string into text in homework doc editor
			 * then go back to file mode so that the user can see the displayed error message (arr[1]): "File successfully imported"
			 * then user must exit file mode to go back to editing mode to see the imported text.
			 * 
			 */
			String fileAsString = arr[0];
			revertBackToEditingMode();




			for (Integer i : textList.keySet()) {
				if (textList.get(i)!=null) {
					for (Letter l : textList.get(i)) {
						deleteLetter(l);
					}
				}
				textList.put(i, new ArrayList<Letter>());
				//any text that was in text editor is not saved and deleted
				//maybe would want to implement a backup so that text can be recuperated
			}
			lastRow = 0;
			smallestRow = 0;
			cursor.changeLocation(0, 0);
			coord.row = 0;
			coord.col = 0;
			//by now text editor is empty and re initialized

			for (int i = 0; i<fileAsString.length(); i++) {
				char c = fileAsString.charAt(i);

				if (c=='\n') {
					enter();
					//System.out.println();
				}
				else {

					addLetterEditingMode(c);
					//System.out.print(c);
					//if char is unknown, X will be printed to text
				}

			}

			//while loops scrolls text so user sees the top
			//System.out.println("SMALLEST ROW IS " + smallestRow);
			while (smallestRow < 0) {
				moveTextDown(smallestRow);
				smallestRow++;
				//System.out.println("SMALLEST ROW IS " + smallestRow);
			}

			cursor.changeLocation(0, 0);
			coord.row = 0;
			coord.col = 0;

			revertBackToFileMode(false);



		}
		return arr[1];
	}

	/**
	 * save/read to file depending on value of saveOrReadMode
	 */
	public static void fileWindowButtonPressed() {
		System.out.println("FILE BUTTON PRESSED");


		String path = "";
		for (Letter l:filePath)//translate ArrayList<Letter> filePath to string
			path+=l.getLineCluster().getChar();

		System.out.println("Path is: "+path);

		String error;
		String result= "";
		if (saveOrReadMode) {
			error = saveToFile(path);
		} else {
			error = readFromFile(path);
		}

		//is passing text.numLetters always going to ensure that the buffer will be big enough?
		//MAKE SURE MAX SIZE OF STRING IS BIG ENOUGH FOR THE WHOLE TEXT TO GO IN IT
		//figure what is the max number of characters that textList should hold
		//or max number of characters that can be saved to a file

		fileWindowErrorMsg.setLabel(error);
		fileWindowErrorMsg.setLocation(width/2-fileWindowErrorMsg.getWidth()/2, height*3/4);//-fileWindowErrorMsg.getHeight());
		//revertBackToEditingMode() is only done once user clicks red box to exit save mode

	}




	public static void mouseClicked(MouseEvent e) {
		//if (textList.size() == 0)
		//	return;
		if (inFileWindow) {
			GObject x = canvas.getElementAt(e.getX(), e.getY());
			if (x == null)
				return;
			if (x.equals(closeFileWindowButton)) {
				System.out.println("closeFileWindowButton PRESSED");
				revertBackToEditingMode();
			} else if (x.equals(fileWindowButton)) {
				fileWindowButtonPressed();
			}
			return;

		}
		if (e.getX() < width - cursor.getWidth()) {
			GRectID a = getGRectIDAt(e.getX(), e.getY());
			int rowT = (int) (e.getY() / cursor.getHeight());
			int column = (int) (e.getX() / cursor.getWidth());
			if (rowT > Helper.getLastRow()) {
				ArrayList<Letter> lastRow = textList.get(Helper.getLastRow());
				rowT = Helper.getLastRow();
				if (lastRow == null) {
					column = 0;
				} else if (column >= lastRow.size())
					column = lastRow.size();
			}
			if (a!=null){
				cursor.changeLocation(a.getX(), a.getY());
			}else{
				ArrayList<Letter> letters = textList.get(Integer.valueOf(rowT));
				double x;
				if (letters == null || letters.size() == 0) {
					x = 0;
					column = 0;
				} else {
					if (column>=letters.size()) {
						x = letters.get(letters.size() - 1).getGRectID().getX() + cursor.getWidth();
						column = (int) (x / cursor.getWidth());
					}
				}
				cursor.changeLocation(cursor.getWidth() * column, cursor.getHeight() * rowT);
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
				if (textList.size() == 0 || text.numLetters==0)
					return;//this is because Buffer Size has to be greater than 0 in FileHelper class
				revertBackToFileMode(true);
				//fileWindow and closeFileWindowButton are only added to canvas (add()) when save button is clicked
			} else if (x.equals(readButton) || x.equals(boxRead)) {
				System.out.println("NEED  TO READ FROM A FILE HEERE");
				revertBackToFileMode(false);
			}
		}
	}
	
	
	/**
	 * 
	 * @return String comprised of all chars in textList, including '\n'
	 */
	public static String setTextListToString() {

		String str = "";
		if (textList == null || textList.size() == 0) {
			str = "EMPTY";
			//will never happen because user cannot change to save mode if text is empty
			return str;
		}
		//to check :WHETHER THE NUMBER OF CHARS IS GETTING TOO BIG FOR A SINGLE STRING TO HOLD
		/*
		 * The size of int in Java is 4 bytes (included a signed bit, i.e. MSB).
		 * The range of integer data type is -231 to 231-1 (-2147483648 to 2147483647). 
		 * Remember that we cannot use negative values for indexing. The indexing is done within the maximum range.
		 * It means that we cannot store the 2147483648th character. 
		 * Therefore, the maximum length of String in Java is 0 to 2147483647. 
		 * So, we can have a String with the length of 2,147,483,647 characters, theoretically.
		 */
		//text.numLetters<=Integer.MAX_VALUE

		Set<Integer> keys = textList.keySet();
		//ArrayList<Integer> orderedKeys = new ArrayList(new TreeSet(keys));
		TreeSet<Integer> orderedKeys = new TreeSet(keys);
		//NOTE: this method only works if the for loop loops through the textList
		//in increasing order of rows, from smallestRow to lastRow
		//which is why we are using a TreeSet
		for (Integer key : orderedKeys) {
			if (key>=smallestRow && key<=lastRow) {
				if (textList.get(key) != null) {
					for (Letter l : textList.get(key)) {
						str = str + l.getLineCluster().getChar();
					}
				}
				//System.out.println("");
				if (key!=lastRow)
					str = str + '\n';
			}
		}
		return str;

	}


/**]
 * 
 * @param x
 * @param y
 * @return GRectID at x, y coordinate, null if none exists
 */
	public static GRectID getGRectIDAt(double x, double y) {
		canvas.remove(cursor);
		GObject a = canvas.getElementAt(x, y);
		//canvas.add(cursor);
		if (a != null) {
			if (a instanceof GLine) {
				canvas.remove(a);
				//canvas.remove(cursor);
				GRectID b = getGRectIDAt(x,y);
				canvas.add(a);
				canvas.add(cursor);
				return (b);
			} else {
				return ((GRectID) a);
			}
		}
		return null;
	}
	
	/**
	 * @return col of cursor in save mode or index of filePath
	 */
	public static int getCurrentColSaveMode() {
		return (int) (cursor.getX()/cursor.getWidth()) - (int) ((width/8)/cursor.getWidth())-1;
	}


	public static GRectID getGRectIDAtCursor() {
		return getGRectIDAt(cursor.getX() + 0.6 * cursor.getWidth(), cursor.getY() + 0.6 * cursor.getHeight());
	}
}