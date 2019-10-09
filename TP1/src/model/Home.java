package model;


public class Home {
	int distToA;
	int distToB;
	int roomsNb;
	int price;


	public Home(int distToA, int distToB, int roomsNb, int price) {
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
}
