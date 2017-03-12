package duncanandrew.com;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;

public class MazeLevel implements KeyListener {

	private Game game;
	private Maze maze;
	private Player player;
	
	private int SCREEN_X = 17;
	private int SCREEN_Y = 9;
	private int screenOffsetX;
	private int screenOffsetY;
	
	
	
	public MazeLevel(Game game, File mapFile){
		// Get game context
		this.game = game;
		game.getContext().addKeyListener(this);
        game.getContext().setFont(new Font("monospaced", Font.PLAIN, 40));
		
        // Create the map
		maze = new Maze(mapFile);
		
		player = maze.getPlayer();
		
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
		
		
		game.getContext().setText(screenString);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
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
			game.getContext().removeKeyListener(this);
			game.previousMazeLevel();
			printScreen();
		}else if(key.getKeyChar() == 'e'){
			game.getContext().removeKeyListener(this);
			game.nextMazeLevel();
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
