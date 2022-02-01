
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
 * to save to file or load file, maybe use huffman coding to convert text into 1 and 0s
 * looks cool and might save space
 *
 * every ten seconds or something, check wheter there is a class and due date in the textList Map, if there is
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
 * //if no letter is pressed for 5 seconds,
 * make Highlight button with color choices
 */

public class HomeWorkDoc extends GraphicsProgram {
	private static final long serialVersionUID = 1L;
	Text text;
	Cursor cursor;
	GLabel saveButton;
	GRect boxSave;

	GRect upButton;
	GRect downButton;
	Map<Integer, ArrayList<Letter>> textList;
	CursorCoordinates coord;
	//boolean isAtLeft = true;//if col=0 and user clicks left, this bool is true and if delete is pressed then should 
	//bring the word to the right of the cursor to the row above it at the last col
	//isAtLeft is initialized to true because cursor starts at zeroth col
	//instead of bool make cursor change proportions like thinner and go close to 
	//left edge of screen like a google docs cursor
	public static final int  WIDTH = 50*20;//not sure if static is doing anything cause HomeWorkDoc is never instantiated
	public static final int HEIGHT = 30*20;

	
		

	private class MyKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			if (keyCode == KeyEvent.VK_L) {
				Helper.printOutText();
			} else if (keyCode == KeyEvent.VK_UP) {
				Helper.upKey();
			} else if (keyCode == KeyEvent.VK_DOWN) {
				Helper.downKey();
			} else if (keyCode == KeyEvent.VK_ENTER) {
				Helper.enter();
			} else if (keyCode == KeyEvent.VK_LEFT) {
				Helper.leftKey();
			} else if (keyCode == KeyEvent.VK_BACK_SPACE) {
				Helper.deleteKey();
			} else if (keyCode == KeyEvent.VK_RIGHT){
				Helper.rightKey();
			} else {
				char c = ' ';//if any other key than the letters that have been coded
				//are pressed than space is getting added (except for keys taken care of above:L, UP...)
				//temporary measure until all letters can be added to textList
				if (keyCode == KeyEvent.VK_A) {
					c = 'A';
				} else if (keyCode == KeyEvent.VK_B) {
					c = 'B';
				}else if (keyCode == KeyEvent.VK_I) {
					c = 'I';
				}else if (keyCode == KeyEvent.VK_SPACE) {
					c = ' ';
				}else if (keyCode == KeyEvent.VK_C) {
					c = 'C';
				}else if (keyCode == KeyEvent.VK_D) {
					c = 'D';
				}else if (keyCode == KeyEvent.VK_E) {
					c = 'E';
				}else if (keyCode == KeyEvent.VK_F) {
					c = 'F';
				}
				System.out.println(c+" IS ADDED");
				Helper.addLetter(c);	
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
			// TODO Auto-generated method stub

		}

	}


	public void run() {
		addKeyListeners(new MyKeyListener());
		addMouseListeners(new MyMouseListener());
		setSize(WIDTH,HEIGHT);

		coord = new CursorCoordinates(0,0);
		//make button that allows user to change size of cursor


		/*
		 * if each letter takes size of the cursor, there can be (HEIGHT/cursor.getWidth()-2)  characters per row
		 * and (WIDTH/cursor.getWidth()-2) characters per column.
		 */
		cursor = new Cursor(0, 0, 40);

		/**
		 * save button must be on last col not last row
		 * just like the upButton and downButton
		 */
		saveButton = new GLabel("Save");
		boxSave = new GRect(10,getHeight()-2*cursor.getHeight(), saveButton.getWidth(), saveButton.getHeight());
		//add(boxSave);
		boxSave.setFilled(true);
		boxSave.setColor(Color.BLUE);
		//add(saveButton, 10, getHeight()-cursor.getHeight());
		double buttonSize  = cursor.getWidth()*2/3;
		upButton = new GRect(getWidth()-buttonSize, getHeight()/2-buttonSize, buttonSize, buttonSize);
		upButton.setFilled(true);
		upButton.setColor(Color.RED);
		downButton = new GRect(getWidth()-buttonSize, getHeight()/2, buttonSize, buttonSize);
		downButton.setFilled(true);
		downButton.setColor(Color.BLUE);
		
		add(upButton);
		add(downButton);
		
		cursor.sendToFront();
		add(cursor);
		add(cursor.thinCursor);

		System.out.println("CURSOR: "+cursor.getWidth());
		text = new Text(this.getGCanvas());
		textList = text.getTextList();
		
		this.setBackground(Color.BLACK);

		Helper.setObjects(text, cursor, saveButton, boxSave, upButton, downButton, textList, coord, getWidth(), getHeight(), this.getGCanvas());
		while (true) {//will have to make this run in a while true loop
			//cursor.thinCursor.setVisible(!cursor.thinCursor.isVisible());
			cursor.setVisible(!cursor.isVisible());
			pause(300);
		}
		//cursor.thinCursor.setVisible(true);
		
	}



	





}
