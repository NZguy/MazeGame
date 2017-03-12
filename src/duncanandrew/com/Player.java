package duncanandrew.com;

public class Player {

	private Coord pos;
	private Maze maze;
	
	public Player(Coord coord, Maze maze){
		pos = coord;
		this.maze = maze;
	}
	
	public Coord getPos(){
		return pos;
	}

	public void move(int deltaX, int deltaY){
		if(maze.isOpen(pos.getX() + deltaX, pos.getY() + deltaY)){
			this.pos.add(deltaX, deltaY);
		}
		
	}
}
