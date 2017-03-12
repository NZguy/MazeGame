package duncanandrew.com;

public class Player {

	private Coord pos;
	private Maze maze;
	
	public Player(Coord coord, Maze maze){
		pos = coord;
		this.maze = maze;
	}
	
	public void setPos(Coord pos){
		this.pos = new Coord(pos.getX(), pos.getY());
	}
	
	public Coord getPos(){
		return pos;
	}

	public void move(int deltaX, int deltaY){
		if(maze.isOpen(new Coord(pos.getX() + deltaX, pos.getY() + deltaY))){
			this.pos.add(deltaX, deltaY);
		}
		
	}
}
