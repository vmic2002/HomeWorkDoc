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
            int size;
            if (textList.get(key)==null)
                size=0;
            else
                size = textList.get(key).size();
            System.out.println("ROW>>" + key + " Size: " + size);
            if (textList.get(key)!=null) {
                for (Letter l : textList.get(key)) {
                    System.out.print(" CHAR>>" + l.getLineCluster().getChar());
                    //System.out.print("<<ID>>"+l.getGRectID().getID()+"<<Y>>"+l.getGRectID().getY()+"<<X>>"+l.getGRectID().getX()+"<<");

                }
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

    public static void leftKey() {
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

        }
        if (coord.row != lastRow)//if coord.row==lastRow then lastRow++ will be done in moveCursorDownAndLeft()
            lastRow++;//need to do this since enter key moves each line of letters down
        moveCursorDownAndLeft();
    }

    public static void deleteKey() {
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
                    
                    ArrayList<Letter> temp = textList.get(Integer.valueOf(coord.row+1));
                    if (temp == null || temp.size() == 0) {
                        moveRowsUpDeleteHelperMethod(2);
                    } else {
                        ArrayList<Letter> lettersOnCursorRow = textList.get(Integer.valueOf(coord.row));
                        boolean isntFull = false;
                        int numFreeSpaces = (int)(width/cursor.getWidth()-1)-lettersOnCursorRow.size();
                        System.out.println("numfreespaces="+numFreeSpaces);
                        if (lettersOnCursorRow==null||numFreeSpaces>0)
                            isntFull = true;
                        if (isntFull){
                               /* take as many chars from left to right at coord.row+1 and concatenate it
                        to end of coord.row and also move all letters on coord.row+1
                            that arent concatenated all the way to the left
                            (method already does this)
                             */
                            int j=0;
                            for (int i=0; j<numFreeSpaces; i++){
                                if (i>=temp.size()) {
                                    break;
                                }
                                Letter l = temp.remove(i);
                                lettersOnCursorRow.add(l);
                                l.setLocation(width-cursor.getWidth()*(numFreeSpaces-j+1), coord.row* cursor.getHeight());
                                i--;
                                j++;
                            }
                            if (temp.size()>0){
                                for (int i=0; i<temp.size(); i++){
                                    temp.get(i).setLocation(cursor.getWidth()*i,(coord.row+1)*cursor.getHeight());
                                }
                            } else{
                                moveRowsUpDeleteHelperMethod(2);
                            }
                        } else{
                             /*delete last char on coord.row and move first char from coord.row+1 and put it at end of coord.row (cursor should be on this char)
                                then move each char on coord.row+1 to the left (code below does this) recursively*/
                            deleteKey();
                            Letter l = temp.remove(0);
                            for (int i=0; i<temp.size(); i++){
                                temp.get(i).move(-cursor.getWidth(),0);
                            }
                            lettersOnCursorRow.add(l);
                            l.setLocation(cursor.getX(), cursor.getY());
                        }
                    }
                }else {
                    ArrayList<Letter> temp  =textList.get(Integer.valueOf(0));
                    if (coord.row==0&&temp!=null&&temp.size()==0) {
                        System.out.println("ON ROW ZERO MOVING ROWS BELOW IT UP");
                        if (lastRow!=0)
                            moveRowsUpDeleteHelperMethod(1);
                    }
                }
            }
        }
    }

    public static void moveRowsUpDeleteHelperMethod(int j){
        for (int i=coord.row+j; i<=lastRow; i++){
            ArrayList<Letter> temp1 = textList.get(Integer.valueOf(i));
            if (temp1!=null) {
                for (Letter l : temp1) {
                    l.move(0, -cursor.getHeight());
                }
            }
            textList.put(Integer.valueOf(i-1), temp1);
        }
        System.out.println("LAST ROW IS :"+ lastRow);
        textList.put(Integer.valueOf(lastRow), null);
        lastRow--;
    }

    public static void addLetter(char c) {
        canvas.remove(cursor);
        GObject o = canvas.getElementAt(cursor.getX() + cursor.getWidth() / 2, cursor.getY() + cursor.getHeight() / 2);
        canvas.add(cursor);
        ArrayList<Letter> letters;
        if (o != null) {//if null then must not have Letter there
            letters = textList.get(Integer.valueOf(coord.row));
            for (int i = coord.col; i < letters.size(); i++) {
                letters.get(i).move(cursor.getWidth(), 0);
            }
        }
        text.addLetter(cursor.getX(), cursor.getY() + cursor.getWidth(), cursor.getWidth(), Integer.valueOf(coord.row), c, coord.col);
        checkIfTooManyLettersInLine();
    }

    public static void checkIfTooManyLettersInLine() {
        ArrayList<Letter> letters = textList.get(Integer.valueOf(coord.row));
        Letter lastLetter = letters.get(letters.size() - 1);
        if (lastLetter.getGRectID().getX() >= width - cursor.getWidth()) {
            System.out.println("MUST MOVE LAST LETTER TO NEXT ROW");
            /*
            all that needs to be done here is bring lastLetter to front of coord.row+1 and move letters on coord.row+1 to the right
            if there is not enough space on coord.row+1 than take last letter of coord.row+1 and put it to front of coord.row+2
            if there is not enough space on coord.row+2...
             */
        }
        if (cursor.getX() < width - 2 * cursor.getWidth()) {
            moveCursorRight();
        } else {
            //  System.out.println("MOVE CURSOR DOWN AND LEFT");
            moveCursorDownAndLeft();
        }
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
                System.out.println("SAVE TO FILE HERE");
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