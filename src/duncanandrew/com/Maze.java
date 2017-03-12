package duncanandrew.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Maze {
	
	private File[] mapFileArray;
	private int currentFile;
	private ArrayList<ArrayList<Integer>> mapArray;
	private int mapLengthX;
	private int mapLengthY;
	
	private Player player;
	private Coord mapEntrance;
	private Coord mapExit;
	
	
	public Maze(){
		
		getMapFiles();
		currentFile = 0;
		
		player = new Player(new Coord(2, 2), this);
		
		buildMap();
		
		
		
	}
	
	public void getMapFiles(){
		File folder = new File("maps");
		mapFileArray = folder.listFiles();
	}
	
	public void buildMap(){
		
		System.out.println("Current Map is: " + currentFile);
		
		ArrayList<ArrayList<Integer>> mapLoadingArray = new ArrayList<ArrayList<Integer>>();
		
		try {
			
			File mapFile = mapFileArray[currentFile];
			Scanner mapScanner = new Scanner(mapFile);
			
			int y = 0;
			while(mapScanner.hasNextLine()){
				
				String mapLine = mapScanner.nextLine();
				ArrayList<Integer> mapLineArray = new ArrayList<Integer>();
				for(int x = 0; x < mapLine.length(); x++){
					
					if(Integer.parseInt(String.valueOf(mapLine.charAt(x))) == 2){
						mapEntrance = new Coord(x, y);
					}else if(Integer.parseInt(String.valueOf(mapLine.charAt(x))) == 3){
						mapExit = new Coord(x, y);
					}
					// Get character at x, convert it to a string, convert that string to an int
					mapLineArray.add(Integer.parseInt(String.valueOf(mapLine.charAt(x)))); 
				}
				mapLoadingArray.add(mapLineArray);
				y++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mapLengthY = mapLoadingArray.size();
		mapLengthX = mapLoadingArray.get(0).size();
		
		player.setPos(mapEntrance);
		
		this.mapArray = mapLoadingArray;
		
	}
	
	public int numberOfMaps(){
		return mapFileArray.length;
	}
	
	public void setCurrentMap(int mapNumber){
		if(mapNumber >= 0 && mapNumber < numberOfMaps()){
			this.currentFile = mapNumber;
			buildMap();
		}
	}
	
	public void nextMap(){
		if((currentFile + 1) < numberOfMaps()){
			this.currentFile++;
			buildMap();
		}
	}
	
	public void previousMap(){
		if((currentFile - 1) >= 0){
			this.currentFile--;
			buildMap();
		}
	}
	
	public boolean isLastMap(){
		return (currentFile == (numberOfMaps() - 1));
	}
	
	public boolean isOpen(Coord pos){
		
		return (this.mapArray.get(pos.getY()).get(pos.getX()) != 1);
		
	}
	
	public int getLengthX(){
		return this.mapLengthX;
	}
	
	public int getLengthY(){
		return this.mapLengthY;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Coord getEntrance(){
		return this.mapEntrance;
	}
	
	public Coord getExit(){
		return this.mapExit;
	}
	
}
