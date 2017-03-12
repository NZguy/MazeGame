package duncanandrew.com;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Game implements KeyListener {

	JTextArea context;
	private Maze maze;
	private Player player;
	
	public Game(){
		
		JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 530));
		
		context = new JTextArea();
        context.addKeyListener(this);
        Font font = new Font("monospaced", Font.PLAIN, 40);
        context.setFont(font);
        frame.add(context);
		
		maze = new Maze();
		maze.buildMap();
		
		player = new Player(new Coord(2, 2), maze);
		
		frame.pack();
        frame.setVisible(true);
		
		printScreen();
		
	}
	
	public void printScreen(){
		
		String screenString = "";
		
//		for(int i = 0; i <= 100; i++){
//			println("");
//		}
		
		for(int y = 0; y < maze.getLengthY(); y++){
			for(int x = 0; x < maze.getLengthX(); x++){
				if(player.getPos().equals(new Coord(x, y))){
					screenString += " G";
				}else if(maze.isOpen(x, y)){
					screenString += "  ";
				}else{
					screenString += " #";
				}
			}
			screenString +="\n";
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
		// TODO Auto-generated method stub
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
