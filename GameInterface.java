import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
//GameInterface class shows the game mechanics
	public class GameInterface extends JPanel implements Runnable, KeyListener {
		//auto generated
		private static final long serialVersionUTD =1L;
 
		private static final int WIDTH = 500, HEIGHT = 500;
		//thread to execute
		private Thread thread;
		public static int score=0;
		private boolean isRunning;
 
		private boolean right = true, left = false, up = false, down = false;
		//serves as the snake
		private SnakeBody b;
		private ArrayList<SnakeBody> snake;
		//serves as the apple
		private AppleMechanics applePart;
		private ArrayList<AppleMechanics> apples;
		//random coordinates for the apple spawning
		private Random r ;
 
		private int xCoor = 10, yCoor = 10, size = 15;
		private int ticks = 0;
		/**
		 * Creates the size of the JPanel added to the JFrame
		 * keylistener and random number are added
		 * Snake and apple objects are created
		 * random coordinates for apple as well
		 * then executes the method for starting movement
		 * pre:none
		 * post:frame is created with dimensions, snake starts moving
		 */
		public GameInterface () {
			setFocusable(true);
 
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
 
			snake = new ArrayList<SnakeBody>();
			apples = new ArrayList<AppleMechanics>();
			addKeyListener (this);
			r = new Random();
 
			startMoving();
 
		}
		/**
		 * Events that occur when the snake starts moving
		 * pre:none
		 * post:boolean for running is true
		 * new thread is created
		 */
		public void startMoving() {
			isRunning = true;
			//creates a new thread to execute
			thread = new Thread (this);
			thread.start();
		}
		/**
		 * Executed if the snake stops
		 * pre: Snake stops moving
		 * post: Thread is joined, the ending panel from the Main class is created
		 */
		public void stopMoving() {
			isRunning = false;
			Main.gameEnded();
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.out.println("error");
			}
		}
		/**
		 * Used for the movement of the snake
		 * After a certain amount of steps it checks for movement
		 * and if it has stopped (in which case stopMoving will be executed
		 * if the snake hits the apple a new component will be added to the dynamic snake array
		 * and a new apple with random coordinates will be created
		 * pre:none
		 * post: Creates snake and apple if necassery
		 *
		 */
		public void count() {
			//creates snake in beginning
			if (snake.size() == 0) {
				b = new SnakeBody(xCoor, yCoor, 10);
				snake.add(b);
			}
			ticks++;
			/*
			 * This is how often the program checks
			 * the lower it is the more sudden and harder the snake moves
			 * amount depends on the user's selection in the Main class
			 */
			if (ticks > Main.difficulty) {
				//changes the coordinates of the snake depending on the direction
				if (right) xCoor++;
				if (left) xCoor--;
				if (up) yCoor--;
				if (down) yCoor++;
				//resets
				ticks = 0;
				//"draws" a new snake instantaneously with the new coordinates
				//essential adds to th elength of the snake
				b = new SnakeBody (xCoor, yCoor, 10);
				snake.add(b);
				//subtracts if the possible new size exceeds the amount
				if (snake.size() > size) {
					snake.remove(0);
				}
			}
			//draws apple with random coordinates
			if (apples.size() == 0) {
				int xCoor = r.nextInt(49);
				int yCoor = r.nextInt(49);
  
				applePart = new AppleMechanics(xCoor, yCoor, 10);
				apples.add(applePart);
			}
 
			for (int i = 0 ; i < apples.size() ; i++) {
				/*
				 * Checks if the coordiantes of the beginning of the snake is the same as the apple's
				 * (essentially if the snake has eaten it)
				 * if so then the apple will be removed
				 */
				if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
					size++;
					score+=10;
					apples.remove(i);
					i++;
				}
			}
			/*
			 * checks if the snake hits itself
			 * in a for loop each part (block) of the snake array is checked if its coordiantes is the
			 * same as the head (meaning that they have collided)
			 */
			for (int i = 0 ; i < snake.size() ; i++) {
				if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
					/*
					 * exception -
					 * if the array index being checked is the head itself it will be ignored
					 * Otherwise there would be an automatic game over
					 */
					if (i != snake.size() - 1) {
						System.out.println("Game Over");
						stopMoving();
					}
				}
			}
			//game over if the coordinates are more than the edges
			if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {
				System.out.println("Game Over");
				stopMoving();
			}
 
		}
		/**
		 * (non-Javadoc)
		 * @see javax.swing.JComponent#paint(java.awt.Graphics)
		 * paint method draws the colour of the snake, apple and background
		 */
		public void paint (Graphics g) {
			g.clearRect(0, 0, WIDTH, HEIGHT);
 
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
 
			for (int i = 0 ; i < WIDTH/10 ; i++) {
				g.drawLine(i*10, 0, i*10, HEIGHT);
			}
			for (int i = 0 ; i < HEIGHT/10 ; i++) {
				g.drawLine(0, i*10, HEIGHT, i*10);
			}
			for (int i = 0 ; i < snake.size(); i++) {
				snake.get(i).draw(g);
			}
			for (int i = 0 ; i < apples.size(); i++) {
				apples.get(i).draw(g);
			}
		}
		/**
		 * when this method is executed the program works
		 * for as long as it has not stopped
		 * if so then the while loop will not work
		 */
		public void run () {
			while (isRunning) {
				count();
				repaint();
			}
		}
		/**
		 * keyPressed method
		 * method which acts correspondingly with the keys
		 */
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT && !left) {
				right = true;
				down = false;
				up = false;
			}
			if (key == KeyEvent.VK_LEFT && !right) {
				left = true;
				down = false;
				up = false;
			}
			if (key == KeyEvent.VK_UP && !down) {
				up = true;
				left = false;
				right = false;
			}
			if (key == KeyEvent.VK_DOWN && !up) {
				down = true;
				left = false;
				right = false;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
 
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
 
		}
 
	}


