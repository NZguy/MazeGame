package duncanandrew.com;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Game implements KeyListener {

	private JTextArea context;
	private Maze maze;
	private Player player;
	
	private int SCREEN_X = 17;
	private int SCREEN_Y = 9;
	private int screenOffsetX;
	private int screenOffsetY;
	
	private Boolean gameOver = false;
	
	public Game(){
		
		JFrame frame = new JFrame("MazeGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(850, 530));
		
		context = new JTextArea();
        context.addKeyListener(this);
        Font font = new Font("monospaced", Font.PLAIN, 40);
        context.setFont(font);
        frame.add(context);
		
		maze = new Maze();
		maze.buildMap();
		
		player = maze.getPlayer();
		
		frame.pack();
        frame.setVisible(true);
		
		printScreen();
		
	}
	
	public void printScreen(){
		
		// Move screen to player
		if(player.getPos().getX() < SCREEN_X/2){
			screenOffsetX = 0;
		}else if(player.getPos().getX() < (maze.getLengthX() - (SCREEN_X/2))){
			screenOffsetX = player.getPos().getX() - (SCREEN_X/2);
		}else{
			screenOffsetX = maze.getLengthX() - SCREEN_X;
		}
		if(player.getPos().getY() < SCREEN_Y/2){
			screenOffsetY = 0;
		}else if(player.getPos().getY() < (maze.getLengthY() - (SCREEN_Y/2))){
			screenOffsetY = player.getPos().getY() - (SCREEN_Y/2);
		}else{
			screenOffsetY = maze.getLengthY() - SCREEN_Y;
		}
		
		String screenString = "";
		
		for(int y = 0; y < maze.getLengthY(); y++){
			for(int x = 0; x < maze.getLengthX(); x++){
				
				// Only print if inside screen
				if((x >= screenOffsetX && x < (screenOffsetX + SCREEN_X)) && 
						(y >= screenOffsetY && y < (screenOffsetY + SCREEN_Y))){
					Coord currentPos = new Coord(x, y);
					if(player.getPos().equals(currentPos)){
						screenString += " G";
					}else if(maze.getEntrance().equals(currentPos)){
						screenString += " F";
					}else if(maze.getExit().equals(currentPos)){
						screenString += " E";
					}else if(maze.isOpen(currentPos)){
						screenString += "  ";
					}else{
						screenString += " #";
					}
				}
				
			}
			// Only print if inside screen
			if((y >= screenOffsetY && y < (screenOffsetY + SCREEN_Y))){
				screenString +="\n";
			}
		}
		
		
		context.setText(screenString);
	}
	
	public void print(String str){
		System.out.print(str);
	}
	
	public void println(String str){
		System.out.println(str);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// Handle user input
		if(key.getKeyChar() == 'w'){
			player.move(0, -1);
			printScreen();
		}else if(key.getKeyChar() == 'a'){
			player.move(-1, 0);
			printScreen();
		}else if(key.getKeyChar() == 's'){
			player.move(0, 1);
			printScreen();
		}else if(key.getKeyChar() == 'd'){
			player.move(1, 0);
			printScreen();
		}else if(key.getKeyChar() == 'q'){
			maze.previousMap();
			printScreen();
		}else if(key.getKeyChar() == 'e'){
			maze.nextMap();
			printScreen();
		}
		
		// Go to next map on exit
		if(player.getPos().equals(maze.getExit())){
			if(maze.isLastMap()){
				context.setText("You win");
			}else{
				maze.nextMap();
				printScreen();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		char c = key.getKeyChar();
        if (!Character.isDigit(c)) {
            key.consume(); // Stop the event from propagating.
        }
	}
}
