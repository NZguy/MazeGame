package duncanandrew.com;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;

public class Game implements KeyListener {

	private JTextArea context;;
	private Boolean gameOver = false;
	
	private File[] mazeFileArray;
	private int mazeNumber;
	
	public Game(){
		
		JFrame frame = new JFrame("MazeGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(850, 530));
		
		context = new JTextArea();
        context.addKeyListener(this);
        context.setFont(new Font("monospaced", Font.PLAIN, 40));
        frame.add(context);
		
		frame.pack();
        frame.setVisible(true);
        
        getMazeFiles();
        mazeNumber = 0;
		
		context.setText("Press G to start game!");
	}
	
	public void startMazeLevel(int mazeNumber){
		 MazeLevel level = new MazeLevel(this, mazeFileArray[mazeNumber]);
		 System.out.println("Current Maze is: " + mazeNumber);
	}
	
	public void getMazeFiles(){
		File folder = new File("maps");
		mazeFileArray = folder.listFiles();
	}
	
	public void previousMazeLevel(){
		if(mazeNumber - 1 >= 0){
			this.mazeNumber--;
			startMazeLevel(mazeNumber);
		}
	}
	
	public void nextMazeLevel(){
		if(mazeNumber + 1 < mazeFileArray.length){
			this.mazeNumber++;
			startMazeLevel(mazeNumber);
		}else{
			// Last level, end game
			context.setText("Game over press G to play again!");
		}
	}
	
	public JTextArea getContext(){
		return this.context;
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
		if(Character.toLowerCase(key.getKeyChar()) == 'g'){
			this.mazeNumber = 0;
			startMazeLevel(mazeNumber);
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
