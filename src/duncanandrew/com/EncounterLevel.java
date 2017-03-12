package duncanandrew.com;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EncounterLevel implements KeyListener {

	private int encounterNumber;
	private Game game;
	private boolean attackSelected = true;
	private boolean enemyDead = false;
	private boolean encounterOver = false;
	
	private int enemyHealth = 10;
	
	public EncounterLevel(Game game, int encounterNumber){
		this.game = game;
		this.encounterNumber = encounterNumber;
		
		game.getContext().addKeyListener(this);
        game.getContext().setFont(new Font("monospaced", Font.PLAIN, 28));
        
        printScreen();
	}
	
	public void printScreen(){
		String screenString = "";
		
		screenString += "# # # # # # # # # # # # # # # # # # # # # # # # #\n";
		for(int i = 0; i < 8; i++){
			if(i == 3 && enemyDead){
				screenString += "#                    You Win                    #\n";
			}else if(i == 3 && !enemyDead){
				screenString += "#                 Enemy Health                  #\n";
			}else if(i == 4 && !enemyDead){
				int enemyHealthbars = ((21*enemyHealth)/10);
				screenString += "#             ";
				for(int j = 0; j < enemyHealthbars; j++){
					screenString += "/";
				}
				for(int j = 0; j < (21 - enemyHealthbars); j++){
					screenString += " ";
				}
				screenString += "             #\n";
			}else{
				screenString += "#                                               #\n";
			}
		}
		screenString += "# # # # # # # # # # # # # # # # # # # # # # # # #";
		screenString += "\n";

		if(enemyDead){
			screenString += "                     >Leave                      ";
		}else{
			if(attackSelected){
				screenString += "                >Attack | Run                    ";
			}else{
				screenString += "                 Attack | Run<                   ";
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
		if(Character.toLowerCase(key.getKeyChar()) == 'a'){
			attackSelected = true;
			printScreen();
		}else if(Character.toLowerCase(key.getKeyChar()) == 'd'){
			attackSelected = false;
			printScreen();
		}else if(key.getKeyCode() == KeyEvent.VK_ENTER){
			if(enemyDead){
				game.getContext().removeKeyListener(this);
				game.backToMazeLevel();
			}else{
				if(attackSelected){
					this.enemyHealth = enemyHealth - 2;
					if(enemyHealth <= 0){
						this.enemyDead = true;
					}
					printScreen();
				}else{
					game.getContext().removeKeyListener(this);
					game.backToMazeLevel();
				}
			}
		}
		
		if(encounterOver){
			game.getContext().removeKeyListener(this);
			game.backToMazeLevel();
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
