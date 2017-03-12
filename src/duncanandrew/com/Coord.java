package duncanandrew.com;

public class Coord {
	
	private int x;
	private int y;
	
	public Coord(int x, int y){
		this.x = x;
		this.y = y;
		
	}
	
	public void add(int deltaX, int deltaY){
		this.x += deltaX;
		this.y += deltaY;
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean equals(Coord other){
		return (other.getX() == this.x) && (other.getY() == this.y);
	}
	
	public String toString(){
		return "x: " + x + ", y: " + y;
	}

}
