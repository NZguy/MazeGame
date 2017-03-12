package duncanandrew.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Maze {
	
	private ArrayList<ArrayList<Integer>> mazeArray;
	private int mazeLengthX;
	private int mazeLengthY;
	
	private Player player;
	private Coord mazeEntrance;
	private Coord mazeExit;
	private File mazeFile;
	
	public Maze(File mazeFile){
		
		player = new Player(new Coord(2, 2), this);
		this.mazeFile = mazeFile;
		buildmaze();
	}
	
	public void buildmaze(){
		
		
		
		ArrayList<ArrayList<Integer>> mazeLoadingArray = new ArrayList<ArrayList<Integer>>();
		
		try {
			
			Scanner mazeScanner = new Scanner(mazeFile);
			
			int y = 0;
			while(mazeScanner.hasNextLine()){
				
				String mazeLine = mazeScanner.nextLine();
				ArrayList<Integer> mazeLineArray = new ArrayList<Integer>();
				for(int x = 0; x < mazeLine.length(); x++){
					
					if(Integer.parseInt(String.valueOf(mazeLine.charAt(x))) == 2){
						mazeEntrance = new Coord(x, y);
					}else if(Integer.parseInt(String.valueOf(mazeLine.charAt(x))) == 3){
						mazeExit = new Coord(x, y);
					}
					// Get character at x, convert it to a string, convert that string to an int
					mazeLineArray.add(Integer.parseInt(String.valueOf(mazeLine.charAt(x)))); 
				}
				mazeLoadingArray.add(mazeLineArray);
				y++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mazeLengthY = mazeLoadingArray.size();
		mazeLengthX = mazeLoadingArray.get(0).size();
		
		player.setPos(mazeEntrance);
		
		this.mazeArray = mazeLoadingArray;
		
	}
	
	public boolean isOpen(Coord pos){
		
		return (this.mazeArray.get(pos.getY()).get(pos.getX()) != 1);
		
	}
	
	public int getLengthX(){
		return this.mazeLengthX;
	}
	
	public int getLengthY(){
		return this.mazeLengthY;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Coord getEntrance(){
		return this.mazeEntrance;
	}
	
	public Coord getExit(){
		return this.mazeExit;
	}
	
}
