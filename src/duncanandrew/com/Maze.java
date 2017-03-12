package duncanandrew.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
	
	private int[][] mapArray;
	private int mapLengthX;
	private int mapLengthY;
	
	
	public Maze(){
		
		mapArray = buildMap();
		mapLengthY = mapArray.length;
		mapLengthX = mapArray[0].length;
		
	}
	
	public int[][] buildMap(){
		
		ArrayList<ArrayList<Integer>> mapLoadingArray = new ArrayList<ArrayList<Integer>>();
		
		try {
			
			File mapFile = new File("map/map1.txt");
			Scanner mapScanner = new Scanner(mapFile);
			
			while(mapScanner.hasNextLine()){
				
				String mapLine = mapScanner.nextLine();
				ArrayList<Integer> mapLineArray = new ArrayList<Integer>();
				for(int x = 0; x < mapLine.length(); x++){
					// Get character at x, convert it to a string, convert that string to an int
					mapLineArray.set(x, Integer.parseInt(String.valueOf(mapLine.charAt(x)))); 
				}
				mapLoadingArray.add(mapLineArray);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int[][] map = {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,1,1,1,0,0,0,0,1,1,1,0,0,1},
				{1,0,0,1,1,1,0,0,0,0,1,1,1,0,0,1},
				{1,0,0,1,1,1,0,0,0,0,1,1,1,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		return map;
		
	}
	
	public boolean isOpen(int x, int y){
		
		return (this.mapArray[y][x] == 0);
		
	}
	
	public int getLengthX(){
		return this.mapLengthX;
	}
	
	public int getLengthY(){
		return this.mapLengthY;
	}
	
}
