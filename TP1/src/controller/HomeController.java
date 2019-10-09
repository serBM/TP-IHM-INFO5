package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Home;

public class HomeController {
	/*
	 * generates randomly several homes
	 */
	public static List<Home> initRandomHomes(int nbHomes) {
		Random rand = new Random();

		List<Home> list = new ArrayList<Home>();

		for (int i = 0; i < nbHomes; i++) {
			int a = rand.nextInt(30 - 1 + 1) + 1; // distance to A from 1 to 30
			int b = rand.nextInt(30 - 1 + 1) + 1; // distance to A from 1 to 30
			int r = rand.nextInt(7 - 1 + 1) + 1; // number of rooms from 1 to 7
			int p = rand.nextInt(500 - 50 + 50) + 50; // price from 50K to 500K
			list.add(new Home(a, b, r, p));
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
			
			if((aDistA <= distA) && (distA <= bDistA) && (aDistB <= distB) && (distB <= bDistB) && (aPrice <= price) && (price <= bPrice) && (aRooms <= nbRooms) && (nbRooms <= bRooms))				
				newList.add(list.get(i));
		}

		return newList;
	}
}
