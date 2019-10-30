public class Menu {

	int rayon;
	int nbElement;
	public String elemNames[];

	public Menu(int rayon, int nbElement, String elemNames[]) {
		this.rayon = rayon;
		this.nbElement = nbElement;
		this.elemNames = elemNames;
	}

	public int getArea(int centerX, int centerY, int x, int y) {
		double dist = Math.sqrt((centerX - x) * (centerX - x) + (centerY - y) * (centerY - y));
		if (dist > rayon) {
			return 0;
		} else {
			double angle = Math.toDegrees(Math.atan2(centerY - centerY, centerX - (centerX + rayon)) -
					Math.atan2(centerY - y, centerX - x));
			int area = (int) ((angle*nbElement)/360) + 1;
			return area;
		}
	}
}
