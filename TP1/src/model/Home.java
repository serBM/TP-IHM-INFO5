package model;


public class Home {
	
	int distToA; //distance to A
	int distToB; //distance to B
	int roomsNb; //number of rooms
	int price; //price
	int x; //coordinate on x
	int y; //coordinate on y
	int id; //id


	public Home(int x, int y, int distToA, int distToB, int roomsNb, int price) {
		this.x = x;
		this.y = y;
		this.distToA = distToA;
		this.distToB = distToB;
		this.roomsNb = roomsNb;
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getRooms() {
		return roomsNb;
	}
	
	public int getDistToB() {
		return distToB;
	}
	
	public int getDistToA() {
		return distToA;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
