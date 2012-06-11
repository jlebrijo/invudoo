package invudoo.view;

public class Point {
	private int count;
	private String hour;

	public Point(String hour, int count) {
		super();
		this.hour = hour;
		this.count = count;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
