import java.awt.event.MouseEvent;
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
    //TODO
    static GLabel saveButton;
    //TODO
    static GRect boxSave;
    //TODO
    static GRect scrollBar;
    static Map<Integer, ArrayList<Letter>> textList;
    static CursorCoordinates coord;
    static int height;
    static int width;
    static GCanvas canvas;
    static int lastRow;//last row cursor can navigate to
    /*
     *
     *  if you add a setGCanvas method you could have the option to click to different docs this
     * would mean creating one more HashMap textList for each new doc
     * could maybe handle UI where one could see each doc as a square and
     * be able to click on it (main menu). Include side tabs along left side of doc to
     * access them.
     *
     * HomeWordDoc does not change to scale
     *
     */

    public static void setObjects(Text text1, Cursor cursor1, GLabel saveButton1, GRect boxSave1, GRect scrollBar1, Map<Integer, ArrayList<Letter>> textList1, CursorCoordinates coord1, int getWidth, int getHeight, GCanvas g) {
        text = text1;
        cursor = cursor1;
        saveButton = saveButton1;
        boxSave = boxSave1;
        scrollBar = scrollBar1;
        textList = textList1;
        coord = coord1;
        height = getHeight;
        width = getWidth;
        canvas = g;
        lastRow = 0;
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
            System.out.println("ROW>>" + key + " Size: " + textList.get(key).size());

            for (Letter l : textList.get(key)) {
                System.out.print(" CHAR>>" + l.getLineCluster().getChar());
                //System.out.print("<<ID>>"+l.getGRectID().getID()+"<<Y>>"+l.getGRectID().getY()+"<<X>>"+l.getGRectID().getX()+"<<");

            }
            System.out.println("");
        }
        System.out.println("LAST ROW IS " + lastRow);
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
        if (cursor.getY() >= cursor.getHeight()) {
            upOrDownKey(-1);
        }
    }

    public static void downKey() {
        if (cursor.getY() < height - 3 * cursor.getHeight()) {//is 3*cursor.getHeight() so that last row is reserved for buttons to save file and load file
            upOrDownKey(1);
        }
    }


    public static void enter() {
        //need to do text.addBlankLine(row)
        System.out.println("ENTER WAS HIT");
        System.out.println("SIZE:" + textList.size());
        if (textList.size() != 0) {
            //from coord.row+1 to last row, move each row down and reassign to correct row in textList
            for (int i = lastRow + 1; i > coord.row + 1; i--) {
                for (Letter l : textList.get(Integer.valueOf(i - 1))) {
                    l.move(0, cursor.getHeight());
                }
                textList.put(Integer.valueOf(i), textList.get(Integer.valueOf(i - 1)));

            }
            ArrayList<Letter> letters = new ArrayList<Letter>();
            ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row));
            for (int i = coord.col; i < temp.size(); i++) {
                letters.add(temp.get(i));
            }
            //WHAT IF LETTERS.SIZE()==0??
            if (letters.size() != 0) {
                double dx = -letters.get(0).getGRectID().getX();

                for (Letter l : letters) {
                    l.move(dx, cursor.getHeight());
                }
            }
            textList.put(Integer.valueOf(coord.row + 1), letters);
            ArrayList<Letter> temp1 = new ArrayList<Letter>();
            for (int i = 0; i < coord.col; i++) {
                temp1.add(temp.get(i));
            }
            textList.put(Integer.valueOf(coord.row), temp1);

            //row coord.row+1 needs to be moved down and reassigned to coord.row+2
            //and move all letters to the right and on coord.col in coord.row to coord.row+1


                   /* double y = textList.get(Integer.valueOf(textList.size() - 1)).get(0).getGRectID().getY();
                    if (y >= coord.row * cursor.getHeight() && y < height - cursor.getWidth() * 2) {
                        System.out.println("LETTERS SHOULD GO DOWN?");
                        //DOESNT WORK IF THERE IS JUST ONE LINE IE LETTAB.SIZE()==1
                        if (textList.size() > 2) {
                            textList.put(Integer.valueOf(textList.size()), textList.get(Integer.valueOf(textList.size() - 1)));
                            ArrayList<Letter> letters1 = textList.get(Integer.valueOf(textList.size() - 1));
                            if (letters1.size() > 0) {
                                for (int j = 0; j < letters1.size(); j++) {
                                    letters1.get(j).move(0, cursor.getHeight());
                                }
                            }
                            for (int i = textList.size() - 2; i > coord.row + 1; i--) {
                                ArrayList<Letter> letters = textList.get(Integer.valueOf(i - 1));
                                if (letters.size() > 0) {
                                    for (int j = 0; j < letters.size(); j++) {
                                        letters.get(j).move(0, cursor.getHeight());
                                    }
                                }
                                textList.put(Integer.valueOf(i), textList.get(Integer.valueOf(i - 1)));
                            }
                        }

                        ArrayList<Letter> temp = new ArrayList<Letter>();
                        ArrayList<Letter> temp1 = textList.get(Integer.valueOf(coord.row));
                        if (textList.size() == 2) {
                            textList.put(Integer.valueOf(textList.size()), textList.get(Integer.valueOf(textList.size() - 1)));
                        }
                        if (textList.get(Integer.valueOf(coord.row)).size() > coord.col) {
                            int x1 = (int) (temp1.get(coord.col).getGRectID().getX() / cursor.getWidth());
                            for (int i = coord.col; i < temp1.size(); i++) {
                                System.out.println(">>" + temp1.get(i).getGRectID().getID());
                                temp.add(temp1.get(i));
                                //System.out.println("x1: "+x1+ " GRectID X: "+ temp1.get(i).getGRectID().getX() + "cursor width: "+cursor.getWidth());
                                temp1.get(i).move(-x1 * cursor.getWidth(), cursor.getHeight());
                            }
                            textList.put(Integer.valueOf(coord.row + 1), temp);
                            ArrayList<Letter> temp2 = new ArrayList<Letter>();
                            ArrayList<Letter> temp3 = textList.get(Integer.valueOf(coord.row));
                            for (int i = 0; i < coord.col; i++) {
                                temp2.add(temp3.get(i));
                            }
                            textList.put(Integer.valueOf(coord.row), temp2);
                        }
                    }*/


        }
        if (coord.row != lastRow)//if coord.row==lastRow then lastRow++ will be done in moveCursorDownAndLeft()
            lastRow++;//need to do this since enter key moves each line of letters down
        moveCursorDownAndLeft();

    }

    public static void deleteKey() {

		/*	if (cursor.getX()==0&&textList!=null) {
				if (textList.get(Integer.valueOf(coord.row)).size() != 0) {
					//
					//need to get letters on coor.row to the right of letters in coord.row-1 if there is enough space
                    however if there isnt enough space then bring last word on coord.row-1 and put it rigjt in front of
                    first word on coord.row with no spaces in between. if there is now no more space on coord.row, then
                    put last word of coord.row at beginning of coord.row+1, continue this loop until there is enough space in one line
					//if deleting last letters on the last line of do lastRow-- after moving letters on this row up and right
					 //

				}

				return;
			}

			*/
        //canvas.remove(cursor);

        GObject a = canvas.getElementAt(cursor.getX() - 0.4 * cursor.getWidth(), cursor.getY() + 0.6 * cursor.getHeight());
        //cursor.getX()+cursor.getWidth()/2, cursor.getY()+cursor.getHeight()/2);
        //canvas.add(cursor);

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
                //remove(b);
            } else {
                System.out.println("GOT TO HERE");
                int id = ((GRectID) a).getID();
                index2 = text.delete(Integer.valueOf(id), coord.row);
                //remove(a);
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
                    lastRow--;//SHOULD ONLY EXECUTE WHEN CURSOR IS ON LAST LINE OF TEXTLIST
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
                    //NEED TO DO
                    //move all letters below and including coord.row one row up
                    //make a method that does that, once that is done and return method works,
                    //HomeWorkDoc will be pretty functional

                }
            }
        }
    }

    public static void leftKey() {
        moveCursorLeft();
    }

    public static void rightKey() {
        if (cursor.getX() < width - cursor.getWidth()) {
            canvas.remove(cursor);
            GObject o1 = canvas.getElementAt(cursor.getX() + cursor.getWidth() / 2, cursor.getY() + cursor.getHeight() / 2);
            canvas.add(cursor);

            if (o1 != null) {
                moveCursorRight();
            }
        }
    }

    public static void addLetter(char c) {

        canvas.remove(cursor);
        GObject o = canvas.getElementAt(cursor.getX() + cursor.getWidth() / 2, cursor.getY() + cursor.getHeight() / 2);
        canvas.add(cursor);
        ArrayList<Letter> letters;
        if (o != null) {//if null then must not have Letter there
            //textList = text.getTextList();
            letters = textList.get(Integer.valueOf(coord.row));
            for (int i = coord.col; i < letters.size(); i++) {
                letters.get(i).move(cursor.getWidth(), 0);
            }
        }
        text.addLetter(cursor.getX(), cursor.getY() + cursor.getWidth(), cursor.getWidth(), Integer.valueOf(coord.row), c, coord.col);
        /*
         * problem of adding last letter to next row could have to do with an arraylist being
         * referenced by two keys when you are copying them to move every row down, if both keys
         * have same value, then changing the value will change it for both keys. this could be
         * potential problem
         */
        checkIfTooManyLettersInLine();//this method is definitly wrong maybe restart return method almost

    }


    public static void addLastLetterToNextLine(Letter lastLetter) {
        lastLetter.move(0, cursor.getHeight());
        while (lastLetter.getGRectID().getX() > 0)
            lastLetter.move(-cursor.getWidth(), 0);
        //lastLetter.setLocation(0.0,  lastLetter.getGRectID().getY());
        ArrayList<Letter> listLetters = textList.get(Integer.valueOf(coord.row + 1));
        if (listLetters == null)
            listLetters = new ArrayList<Letter>();
        //ArrayList<Letter> listLetters = new ArrayList<Letter>();
        for (Letter l : listLetters)
            System.out.println(">>" + l.getLineCluster().getChar());
        listLetters.add(0, lastLetter);
        textList.put(Integer.valueOf(coord.row + 1), listLetters);
        if (listLetters.size() > 1) {
            for (int i = 1; i < listLetters.size(); i++) {
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
        Letter lastLetter = letters.get(letters.size() - 1);
        if (lastLetter.getGRectID().getX() >= width - cursor.getWidth()) {
            int row = textList.size() - 1;
            for (int i = row; i >= 0; i--) {
                if (textList.get(Integer.valueOf(row)).size() != 0) {
                    row = i;
                    break;
                }

            }
            if (textList.get(Integer.valueOf(row)).get(0).getGRectID().getY() == lastLetter.getGRectID().getY()) {
                if (lastLetter.getGRectID().getY() < height - 2 * cursor.getWidth()) {
                    System.out.println("NO PROBLEM");
                    addLastLetterToNextLine(lastLetter);
                } else {
                    System.out.println("case 1: letter cant go down cause there isnt enough space. Need to create new page below.");
                }
            } else {
                double y = textList.get(Integer.valueOf(textList.size() - 1)).get(0).getGRectID().getY();
                if (y < height - cursor.getWidth() * 2) {


                    ArrayList<Letter> letters1 = textList.get(Integer.valueOf(textList.size() - 1));

                    if (letters1.size() > 0) {
                        for (int j = 0; j < letters1.size(); j++) {
                            letters1.get(j).move(0, cursor.getHeight());
                        }
                    }
                    textList.put(Integer.valueOf(textList.size()), textList.get(Integer.valueOf(textList.size() - 1)));

                    for (int i = textList.size() - 2; i > coord.row + 1; i--) {
                        letters = textList.get(Integer.valueOf(i - 1));
                        if (letters.size() > 0) {
                            for (int j = 0; j < letters.size(); j++) {
                                letters.get(j).move(0, cursor.getHeight());
                            }
                        }
                        textList.put(Integer.valueOf(i), textList.get(Integer.valueOf(i - 1)));
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
        if (cursor.getX() < width - 2 * cursor.getWidth()) {
            moveCursorRight();

        } else {
            //return
            moveCursorDownAndLeft();

        }
    }


    public static void moveCursorRight() {
        if (cursor.getX() < width - 2 * cursor.getWidth()) {
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
        if (cursor.getY() < height - 3 * cursor.getHeight()) {//is 3*cursor.getHeight() so that last row is reserved for buttons to save file and load file
            cursor.move(0, cursor.getHeight());
            cursor.changeLocation(0, cursor.getY());
            if (coord.row == lastRow)
                lastRow++;
            coord.row++;
            coord.col = 0;
        }
    }

    public static int getLastRow() {
        return lastRow;
    }

    public static void mouseClicked(MouseEvent e) {
        if (textList.size() == 0)
            return;
        if (e.getY() < height - cursor.getWidth() && e.getX() < width - cursor.getWidth()) {
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
            if (x.equals(saveButton) || x.equals(boxSave)) {
                System.out.println("SAVE TO FILE HERE AHAHAHAHHAHAHAHAHAHAHAHAHAHAHAHHA");
            }
        }
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

