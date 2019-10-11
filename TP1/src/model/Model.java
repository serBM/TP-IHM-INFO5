package model;

public class Model {

	int rMin, min, max;
	float unitSize;

	public Model(int min, int max, int rMin, int rMax) {
		this.rMin = rMin;
		this.min = min;
		this.max = max;
		this.unitSize = (float) (rMax - rMin) / (float) (max - min);
	}

	public String getValue(int pos, int wb) {
		float posInSlider;
		int value;
		posInSlider = (pos + (wb / 2)) - rMin;
		value = (int) (posInSlider / unitSize) + min;
		return Integer.toString(value);
	}

	public int setValue(int n, int wb) {
		return (int) ((float) rMin - (wb / 2) + (float) (n-min) * unitSize);
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}
