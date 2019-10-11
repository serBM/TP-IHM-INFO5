package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Home;

public class HomeController {

	/*
	 * function that return the distance between two points
	 * source: https://www.baeldung.com/java-distance-between-two-points
	 */
	public static int calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
		return (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	/*
	 * generates randomly several homes
	 */
	public static List<Home> initRandomHomes(int nbHomes, int xA, int yA, int xB, int yB) {
		Random rand = new Random();

		List<Home> list = new ArrayList<Home>();
				
		for (int i = 0; i < nbHomes; i++) {
			
			int x = returnRandomValue(1, 200);
			int y = returnRandomValue(1, 200);
			
			while((calculateDistanceBetweenPoints(xA, yA, x, y) > 200) || (calculateDistanceBetweenPoints(xB, yB, x, y) > 200)) {
				x = returnRandomValue(1, 200);
				y = returnRandomValue(1, 200);
			}
			
			int a = calculateDistanceBetweenPoints(xA, yA, x, y); // distance to A from 1 to 30			
			int b = calculateDistanceBetweenPoints(xB, yB, x, y);
			
			int r = returnRandomValue(1,7); // number of rooms from 1 to 7
			int p = returnRandomValue(50,500); // price from 50K to 500K
			
			Home h = new Home(x, y, a, b, r, p);
			h.setId(i);
			list.add(h);
		}

		return list;
	}

	/*
	 * return the list which match the criteria
	 */
	public static List<Home> getListCriteria(List<Home> list, int aDistA, int bDistA, int aDistB, int bDistB,
			int aPrice, int bPrice, int aRooms, int bRooms) {

		List<Home> newList = new ArrayList<Home>();

		int distA, distB, price, nbRooms;

		for (int i = 0; i < list.size(); i++) {
			distA = list.get(i).getDistToA();
			distB = list.get(i).getDistToB();
			price = list.get(i).getPrice();
			nbRooms = list.get(i).getRooms();

			if ((aDistA <= distA) && (distA <= bDistA) && (aDistB <= distB) && (distB <= bDistB) && (aPrice <= price)
					&& (price <= bPrice) && (aRooms <= nbRooms) && (nbRooms <= bRooms))
				newList.add(list.get(i));
		}

		return newList;
	}
	
	public static int returnRandomValue(int min, int max) {
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}
}
