package model;

public class Model {

	int rMin;
	float unitSize;

	public Model(int min, int max, int rMin, int rMax) {
		this.rMin = rMin;
		this.unitSize = (float) (rMax - rMin) / (float) (max - min);
	}

	public String getValue(int pos, int wb) {
		float posInSlider;
		int value;
		posInSlider = (pos + (wb / 2)) - rMin;
		value = (int) (posInSlider / unitSize);
		return Integer.toString(value);
	}
}
