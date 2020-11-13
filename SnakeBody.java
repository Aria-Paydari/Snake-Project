import java.awt.Color;
import java.awt.Graphics;

public class SnakeBody {

		private int snakeX, snakeY, width, height;
		/**
		 * Gets x and y coordinates and the size of the snake
		 * Pre : x and y and size are greater than 0 and less than 50
		 * Post: x and y and the size values are stored
		 * @param xCoor
		 * @param yCoor
		 * @param titleSize
		 */
		public SnakeBody (int xCoor, int yCoor, int titleSize) {
			snakeX= xCoor;
			snakeY = yCoor;
			width = titleSize;
			height = titleSize;
		}
		/**
		 * Draws the snake with the given coordinates
		 * Pre : the x, y and the coordinates of the program
		 * Post : snake is drawn 
		 * @param g
		 */
		public void draw(Graphics g) {
			g.setColor(Color.green);
			g.fillRect(snakeX * width, snakeY * height, width, height);
		}
		/**
		 * gives the x coordinate to the game panel
		 * Pre : none
		 * Post : x coordinate is returned
		 * @return
		 */
		public int getxCoor() {
			return snakeX;
		}
		/**
		 * gets the new x coordinate from the game 
		 * Pre : none
		 * Post : x coordinate is stored in the snake
		 * @param xCoor
		 */
		public void setxCoor(int xCoor) {
			snakeX = xCoor;
		}
		/**
		 * gives the y coordinate to the game panel
		 * Pre : none
		 * Post : y coordinate is returned
		 * @return
		 */
		public int getyCoor() {
			return snakeY;
		}
		/**
		 * gets the new y coordinate from the game 
		 * Pre : none
		 * Post : y coordinate is stored in the snake
		 * @param yCoor
		 */
		public void setyCoor(int yCoor) {
			snakeY= yCoor;
		}
}
