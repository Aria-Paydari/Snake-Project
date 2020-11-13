import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
//Main class - generally controls the actions
	public class Main implements ActionListener {
		private static JLabel enterName;
		private static JLabel rules;
		private static JTextField name;
		private static JButton easy;
		private static JButton normal;
		private static JButton hard;
		private static JButton insane;
		private static JLabel highScoreLabel;
		private JFrame intro;
		private static JFrame frame;
		private static String playerName;
		public static int difficulty;
		public static File highScore;
		public static int compareScore=0;
		static String topPlayer="";
		static Scanner sc=null;
		/**
		 * This constructor will create the introduction frame with the labels, buttons etc.
		 * pre:none
		 * post: Creates an introduction frame which gives the instructions, highscore,
		 * asks for the user name and the difficulty to play with.
		 */
		public Main () {
			intro=new JFrame();
			intro.setLayout(null);
  
			//ask how to get name
			//close window
			easy=new JButton("easy");
			normal=new JButton("Normal");
			hard=new JButton("Hard");
			insane=new JButton("Insane");
			name=new JTextField();
			rules=new JLabel("Welcome to the snake game,The snake game is an exciting and thrilling game which prompts a good reaction time as well as good strategy. The difficulty of the game ranges from easy to hard.The rules : If the snake’s head collides with any part of the snake it self or collides with the boundaries of the program, the game is over and your score is displayed.Every apple that is eaten will give you a score of 10. Have fun and get those apples.");
			enterName = new JLabel("Enter name here :");
			rules=new JLabel("<html>Welcome to the snake game,<br><br>The snake game is an exciting and thrilling game which prompts a good reaction time as well as good strategy.<br>The difficulty of the game ranges from easy to hard.<br><br>The rules : <br>If the snake’s head collides with any part of the snake it self or collides with the boundaries of the program, the game is over and your score is displayed.<br>Every apple that is eaten will give you a score of 10. <br><br>Have fun and get those apples.</html>");
			enterName = new JLabel("Enter name here :");
			rules.setBounds(0, 50, 500, 250);
			enterName.setBounds(0, 0, 130, 25);
			name.setBounds(115, 0, 330, 30);
			easy.setBounds(0, 310, 100, 100);
			normal.setBounds(125, 310, 100, 100);
			hard.setBounds(250, 310, 100, 100);
			insane.setBounds(375, 310, 100, 100);
			easy.addActionListener(this);
			normal.addActionListener(this);
			hard.addActionListener(this);
			insane.addActionListener(this);
			intro.add(rules);
			intro.add(easy);
			intro.add(normal);
			intro.add(hard);
			intro.add(insane);
			intro.add(name);
			highScore=new File("highScore.txt");
			//checks for the file with the high scores
			if(highScore.exists()){
				try {
					sc=new Scanner(highScore);
					//attaches scanner to the highScore file to check the lines
				} catch (FileNotFoundException e) {
					System.out.println("Doesn't exist.");
				}
				/*this loop checks every two lines in the h ighScore file
				 * The first line is the player name
				 * second line is the score
				 */
				while(sc.hasNextLine()){
 	
					String player=sc.nextLine();
					int score=sc.nextInt();
					/*if the score is bigger than the previous
 			It will be the high score
 			This is repeated until the true high score is stored
					 */
					if(score>compareScore) {
						compareScore=score;
						topPlayer=player;
					}
					//Places cursor at the beginning of the next line if it exists
					if(sc.hasNextLine())
						sc.nextLine();
				}
  
			}
			else{
				//happens in case there is no text file
				System.out.println("error");
			}
			final int DIMENSION_ONE = 500;
			final int DIMENSION_TWO = 560;
			intro.add(enterName);
			intro.setPreferredSize(new Dimension(DIMENSION_ONE,DIMENSION_TWO));
			intro.pack();
			intro.setVisible(true);
			intro.setLocationRelativeTo(null);
  
			}	
		/**
		 * main method
		 * @param args
		 */
		public static void main(String[] args) {
			new Main();
		}
		/**
		 * executed when game is over
		 * removes game frame and creates the JFrame
		 * This shows the user's score
		 * score and name also saved to highScore
		 * pre:The previous game has ended (executed in stopMoving() in the GameInterface class)
		 * post:new (final) panel is created, the user's name and score are recorded in the text file.
		 */
		public static void gameEnded(){
			//removes game frame
			frame.dispose();
			JFrame endFrame  = new JFrame();
			if(GameInterface.score>compareScore) {
				JLabel highScoreMessage=new JLabel("<html><h1>Congratulations!! "+playerName+" you set out a new record of "+"<br>"+GameInterface.score +"!</html>");
				highScoreMessage.setBounds(150, 0, 250,250);
				endFrame.add(highScoreMessage);
			}
			else {
				highScoreLabel= new JLabel("<html><h2><br><br>High score: "+ compareScore + " by " +topPlayer +"</html>");
				highScoreLabel.setBounds(150, 100, 200, 200);
				endFrame.add(highScoreLabel);
				JLabel finalScore=new JLabel("<html><h1>Game Over!<br>Player: "+playerName+"<br>Score: "+GameInterface.score +"</html>");
				finalScore.setBounds(150, 0, 250,250);
				endFrame.add(finalScore);
			}
			endFrame.setLayout(null);
			endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			endFrame.setPreferredSize(new Dimension(500,400));
			endFrame.pack();
			endFrame.setVisible(true);
			endFrame.setLocationRelativeTo(null);
			//Writes name and score on the text files
			Writer writer=null;
			try {
				writer=new FileWriter("highScore.txt",true);
	
			}
			catch(IOException ex) {
				System.out.println("Can't write the desired.");
			}
 
			PrintWriter output=new PrintWriter(writer);

			output.println("\n"+playerName);
			output.print(GameInterface.score);
			output.close();
		}
		/**
		 * executes when one of the buttons of the intro page is pressed
		 * Opens a frame with the gamepanel JPanel
		 * This contains the actual game mechanics
		 * pre:none (button pressed)
		 * post:game frame with snake game is created
		 */
		public void gamePlayed() {
  
			playerName=name.getText();
			this.intro.dispose();
			frame  = new JFrame();
			GameInterface gamepanel = new GameInterface();
			frame.add(gamepanel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Snake Game");
			frame.pack();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		}
		@Override
		/**
		 * actionlistener which executes when button is pressed
		 * Based on which button is pressed the difficulty level is chosen
		 * pre: A button is pressed
		 * post:difficulty is obtained based on the one chosen, executes gamePlayed to create the actual snake game frame
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==easy)
				difficulty=200000;
			else if(e.getSource()==normal) 
				difficulty=150000;
			else if (e.getSource()==hard)
				difficulty=100000;
			else if (e.getSource()==insane)
				difficulty=50000;
			gamePlayed();
  
		}
	}

