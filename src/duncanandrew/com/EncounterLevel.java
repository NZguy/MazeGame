package duncanandrew.com;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EncounterLevel implements KeyListener {

	private int encounterNumber;
	private Game game;
	
	public EncounterLevel(Game game, int encounterNumber){
		this.game = game;
		this.encounterNumber = encounterNumber;
		
		game.getContext().addKeyListener(this);
        game.getContext().setFont(new Font("monospaced", Font.PLAIN, 30));
        
        printScreen();
	}
	
	public void printScreen(){
		game.getContext().setText("Press W to win battle");
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if(Character.toLowerCase(key.getKeyChar()) == 'w'){
			game.getContext().removeKeyListener(this);
			game.backToMazeLevel();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
