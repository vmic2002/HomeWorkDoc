
import acm.graphics.*;
import acm.program.*;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * @author Victor Micha
 * will need to save what is written to a file and upload it each time so that
 * schedule and hw dates aren't lost each time
 *
 *
 * include feature where you can command from the canvas for example if you type //addClass(FACC 300) it would add that class to arraylist of classes
 * or //saveToFile() or //loadFile()(or use like JButtons would look better)
 * 
 *
 * check wheter there is a class and due date in the textList Map, if there is
 * bold them or highlight them. and/or program orders the lines of text by due date
 * implement auto generated words like once a class is typed, suggest "due date" and then once user types "J" suggest "anuary"
 *
 *
 * maybe have a bar on the side so that you can have like a full on document to scroll up and down with
 *or an up and down arrow to be clicked when u want to 'scroll' up or down by moving each row up or down
 * and by rearanging the Map textList
 *
 * maybe at some point be able to add images??? would be cool to make it look really similar to real google docs
 *
 *
 * you could make feature to allow user to create their own characters
 * this would allow the to click on the screen and each point is connected to the last clicked point
 * user could choose/customize which character button would create their custom character
 * a file of the customized characters would have to be saved to the computer
 *
 *
 * try to make this project into an executable or app or something to have it accessible from the desktop
 *
 * make Highlight button with color choices
 */

public class HomeWorkDoc extends GraphicsProgram {
	private static final long serialVersionUID = 1L;
	

	//possible feature:
	//boolean isAtLeft = true;//if col=0 and user clicks left, this bool is true and if delete is pressed then should 
	//bring the word to the right of the cursor to the row above it at the last col
	//isAtLeft is initialized to true because cursor starts at zeroth col
	//instead of bool make cursor change proportions like thinner and go close to 
	//left edge of screen like a google docs cursor


	//not sure if static is doing anything cause HomeWorkDoc is never instantiated
	public static final int CURSOR_SIZE = 20;//was be 40, made it 20 so that more characters could fit in a single line
	public static final int SAVE_MODE_CURSOR_SIZE = CURSOR_SIZE/2;//cursor is smaller in save mode
	public static final int  WIDTH = 50*CURSOR_SIZE;//was be 25* CURSOR_SIZE
	public static final int HEIGHT = 30*CURSOR_SIZE;//was be 15*CURSOR_SIZE

	private class MyKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode = e.getKeyCode();
			char c = ' ';
			boolean isLetter = false;
			if (keyCode == KeyEvent.VK_A) {
				c = 'A';
				isLetter = true;
			} else if (keyCode == KeyEvent.VK_B) {
				c = 'B';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_C) {
				c = 'C';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_D) {
				c = 'D';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_E) {
				c = 'E';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_F) {
				c = 'F';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_G) {
				c = 'G';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_H) {
				c = 'H';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_I) {
				c = 'I';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_J) {
				c = 'J';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_K) {
				c = 'K';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_L) {
				c = 'L';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_M) {
				c = 'M';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_N) {
				c = 'N';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_O) {
				c = 'O';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_P) {
				c = 'P';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_Q) {
				c = 'Q';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_R) {
				c = 'R';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_S) {
				c = 'S';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_T) {
				c = 'T';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_U) {
				c = 'U';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_V) {
				c = 'V';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_W) {
				c = 'W';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_X) {
				c = 'X';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_Y) {
				c = 'Y';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_Z) {
				c = 'Z';
				isLetter = true;
			} else if (keyCode == KeyEvent.VK_SPACE) {
				c = ' ';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_SLASH) {
				c = '/';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_MINUS) {
				c = '-';
				isLetter = true;
			}else if (keyCode == KeyEvent.VK_PERIOD) {
				c = '.';
				isLetter = true;
			}
			
			if (isLetter) {
				System.out.println(c+" IS ADDED");
				Helper.addLetter(c);
			} else if (keyCode == KeyEvent.VK_LEFT) {
				Helper.leftKey();
			} else if (keyCode == KeyEvent.VK_BACK_SPACE) {
				Helper.deleteKey();
			} else if (keyCode == KeyEvent.VK_RIGHT){
				Helper.rightKey();
			} else if (keyCode == KeyEvent.VK_1) {
				Helper.printOutText();
			} else if (keyCode == KeyEvent.VK_UP) {
				Helper.upKey();
			} else if (keyCode == KeyEvent.VK_DOWN) {
				Helper.downKey();
			} else if (keyCode == KeyEvent.VK_ENTER) {
				Helper.enter();
			} 
			
		}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}
	}

	private class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Helper.mouseClicked(e);

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {


		}

		@Override
		public void mouseExited(MouseEvent e) {


		}

	}


	public void run() {//run method calls main method
		//initialize everything
		addKeyListeners(new MyKeyListener());
		addMouseListeners(new MyMouseListener());

		setSize(WIDTH,HEIGHT);

		CursorCoordinates coord = new CursorCoordinates(0,0);
		//make button that allows user to change size of cursor


		/*
		 * if each letter takes size of the cursor, (if width >= cursor.getwidth()*2)
		 * there can be WIDTH/cursor.getWidth()-1 characters per row. since last col is reserved for buttons
		 */
		Cursor cursor = new Cursor(0, 0, CURSOR_SIZE);

		/**
		 * save button must be on last col not last row
		 * just like the upButton and downButton
		 */
		double buttonSize  = cursor.getWidth()*2/3;//buttonSize has to be less than cursor.getWidth
		//so that last column is reserved for buttons\
		

		GRect upButton = new GRect(getWidth()-buttonSize, getHeight()/2-buttonSize, buttonSize, buttonSize);
		upButton.setFilled(true);
		upButton.setColor(Color.RED);
		GRect downButton = new GRect(getWidth()-buttonSize, getHeight()/2, buttonSize, buttonSize);
		downButton.setFilled(true);
		downButton.setColor(Color.BLUE);

		add(upButton);
		add(downButton);

		cursor.sendToFront();
		add(cursor);
		add(cursor.thinCursor);
		
		GLabel saveModeText = new GLabel("Save Text to a File");
		saveModeText.setColor(Color.WHITE);
		saveModeText.setLocation(WIDTH/2-saveModeText.getWidth()/2, HEIGHT/4-HEIGHT/16+saveModeText.getHeight()/2);
		
		GLabel readModeText = new GLabel("Import Text from a File");
		readModeText.setColor(Color.WHITE);
		readModeText.setLocation(WIDTH/2-readModeText.getWidth()/2, HEIGHT/4-HEIGHT/16+readModeText.getHeight()/2);
		//only one of saveModeText and readModeText will both be on canvas
		//depending on if in save mode or read mode

		GRect modeTextWindow = new GRect(WIDTH/4, HEIGHT/8, WIDTH/2, HEIGHT/8);
		modeTextWindow.setFilled(true);
		modeTextWindow.setColor(Color.GRAY);
		//modeTextWindow is the rectangle behind the "Save Text to a File" and "Read Text from a File"
		
		GLabel saveButton = new GLabel("Save");
		saveButton.setColor(Color.BLUE);
		GRect boxSave = new GRect(getWidth()-buttonSize,getHeight()/2-3*buttonSize, buttonSize, buttonSize);
		add(boxSave);
		boxSave.setFilled(true);
		boxSave.setColor(Color.GRAY);
		add(saveButton, boxSave.getX(), boxSave.getY()+buttonSize);
		
		GLabel readButton = new GLabel("Import");
		readButton.setColor(Color.BLUE);
		GRect boxRead = new GRect(getWidth()-buttonSize,getHeight()/2-5*buttonSize, buttonSize, buttonSize);
		add(boxRead);
		boxRead.setFilled(true);
		boxRead.setColor(Color.MAGENTA);
		add(readButton, boxRead.getX(), boxRead.getY()+buttonSize);



		//GRect fileWindow = new GRect(WIDTH/4, HEIGHT/4, WIDTH/2, HEIGHT/2);
		GRect fileWindow = new GRect(WIDTH/8, HEIGHT/4, WIDTH*3/4, HEIGHT/2);
		fileWindow.setFilled(true);
		fileWindow.setColor(Color.DARK_GRAY);
		//fileWindow and closeFileWindowButton  and fileWindowText and fileWindowButton 
		//are added to canvas (ex: add(fileWindow)) in Helper.saveToFile()

		//closeFileWindowButton is to go back to editing the text if save file button was accidently clicked
		//or user no longer wants to save text to file
		//GRect closeFileWindowButton = new GRect(WIDTH*3/4-1.5*buttonSize, HEIGHT/4+0.5*buttonSize, buttonSize, buttonSize);
		GRect closeFileWindowButton = new GRect(WIDTH*7/8-1.5*buttonSize, HEIGHT/4+0.5*buttonSize, buttonSize, buttonSize);
		closeFileWindowButton.setFilled(true);
		closeFileWindowButton.setColor(Color.RED);
		
		//when fileWindowButton is clicked, then text will be saved to file according to path that user inputed
		//GRect fileWindowButton = new GRect(WIDTH*3/4-1.5*buttonSize, HEIGHT*3/4-1.5*buttonSize, buttonSize, buttonSize);
		GRect fileWindowButton = new GRect(WIDTH*7/8-1.5*buttonSize, HEIGHT*3/4-1.5*buttonSize, buttonSize, buttonSize);
		fileWindowButton.setFilled(true);
		fileWindowButton.setColor(Color.GREEN);
		//fileWindowButton: is in read mode will import a file if in save mode will save to a file
		
		
		GLabel fileWindowText = new GLabel("Input absolute path: (ex:/Users/mayanksolanki/Desktop/demo.docx)");
		fileWindowText.setColor(Color.WHITE);
		
		//fileWindowText.setLocation(WIDTH/4+10, HEIGHT/4+10);
		fileWindowText.setLocation(WIDTH/2-fileWindowText.getWidth()/2, HEIGHT/4+fileWindowText.getHeight());//+10);
		
		GLabel fileWindowErrorMsg = new GLabel("");
		fileWindowErrorMsg.setColor(Color.WHITE);
		
		
		Boolean inFileWindow = false;
		//true if user clicked save to file button or read from file
		//false if user is editing text
		
		Boolean saveOrReadMode = false;
		//if inFileWindow is false, then saveOrReadMode value does not matter
		//if inFileWindow is true, then if saveOrReadMode is true, user clicked on save button
		//and is in save mode. if saveOrReadMode is false, user clicked on read button and is in read from file mode
		


		ArrayList<Letter> filePath = new ArrayList<Letter>();
		//filePath represents the path that the user will input
		//when trying to save the text to a file

		System.out.println("CURSOR: "+cursor.getWidth());
		Text text = new Text(this.getGCanvas());
		Map<Integer, ArrayList<Letter>> textList = text.getTextList();

		this.setBackground(Color.BLACK);

		Helper.setObjects(text, cursor, saveButton, boxSave, upButton, downButton, textList, coord, getWidth(),
				getHeight(), this.getGCanvas(), fileWindow, closeFileWindowButton, inFileWindow, saveOrReadMode, filePath, fileWindowText,
				fileWindowButton, CURSOR_SIZE, SAVE_MODE_CURSOR_SIZE, fileWindowErrorMsg, saveModeText, modeTextWindow,
				readButton, boxRead, readModeText);
		while (true) {
			cursor.thinCursor.setVisible(!cursor.thinCursor.isVisible());
			pause(300);
		}
	}
}
