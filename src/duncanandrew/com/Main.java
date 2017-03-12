package duncanandrew.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class Main implements KeyListener{
	
	static Game game;
	
	public static void main(String[] args){
		
		game = new Game();
		
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Hi");
		game.keyPressed(key);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
