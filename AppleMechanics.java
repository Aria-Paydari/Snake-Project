import java.awt.Color;
import java.awt.Graphics;

public class AppleMechanics {

		private int appleRow, appleColumn, width, height;
		/**
		 * Gets x and y coordinates and the size of the apple
		 * Pre : x and y and the size are 1 X 1
		 * Post: x and y and the size values are stored
		 * @param xCoor
		 * @param yCoor
		 * @param titleSize
		 */
		public AppleMechanics (int xCoor, int yCoor, int titleSize) {
			appleRow= xCoor;
			appleColumn = yCoor;
			width = titleSize;
			height = titleSize;
		}
		/**
		 * gives the x coordinate to the game panel
		 * Pre : none
		 * Post : x coordinate is returned
		 * @return
		 */
		public int getxCoor() {
			return appleRow;
		}
		/**
		 * gets the new x coordinate from the game 
		 * Pre : none
		 * Post : x coordinate is stored in the apple
		 * @param xCoor
		 */
		public void setxCoor(int xCoor) {
			this.appleRow = xCoor;
		}
		/**
		 * gives the y coordinate to the game panel
		 * Pre : none
		 * Post : y coordinate is returned
		 * @return
		 */
		public int getyCoor() {
			return appleColumn;
		}
		/**
		 * gets the new y coordinate from the game 
		 * Pre : none
		 * Post : y coordinate is stored in the apple
		 * @param yCoor
		 */
		public void setyCoor(int yCoor) {
			appleColumn= yCoor;
		}
		/**
		 * Draws the apple with the given coordinates
		 * Pre : the x, y and the coordinates of the program
		 * Post : apple is drawn 
		 * @param g
		 */
		public void draw(Graphics g) {
			g.setColor(Color.red);
			g.fillRect(appleRow * width, appleColumn * height, width, height);
		}
}
