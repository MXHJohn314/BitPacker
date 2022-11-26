package models.nightshadeenterprise;

import models.flygingpizza.Point;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Example class to showcase BitPacker.
 */
public class Wompus {
	private static final Random R = new Random();
	/**
	 * 95 printable characters = `floor(log(2, 95)) + 1 = 7` 7-bit.
	 * 22-character multiplier = 22 * 7.
	 * total = 154 bits per string.
	 */
	public String message;
	/**
	 * times two, so total is 2*22*7=
	 */
	public String name;
	String id;
	/**
	 * 2 14-bit packs for a point
	 * */
	public Point center;
	/**
	 * 8 14-bit packs for 4 points
	 * */
	public Point[] bounds;
	/**
	 * It takes 1 bit to represent a boolean
	 * */
	public int angle;
	/*log(2,360)+1=9*/
	List<Map.Entry<Integer, Integer>> packList = List.of();
	
	/**
	 * @param bounds the bounds of a Wompus.
	 * @param center the center of a Wompus' home.
	 * @param name a Wompus' birth name.
	 * @param id a Wompus' state-issued ID number as a string.
	 */
	public Wompus(Point[] bounds, Point center, String name, String id) {
		this.name = name;
		this.center = center;
		this.bounds = bounds;
		this.id = id;
		this.message = "Hello World";
		this.angle = 10;
	}
	
	/**
	 * Randomly generate a Wompus.
	 */
	public Wompus() {
		this.angle = R.nextInt(360);
		this.center = new Point(R.nextInt(20001) + 1, R.nextInt(20001) + 1);
		this.bounds = new Point[] {
		 new Point(R.nextInt(20001) + 1, R.nextInt(20001) + 1),
		 new Point(R.nextInt(20001) + 1, R.nextInt(20001) + 1),
		 new Point(R.nextInt(20001) + 1, R.nextInt(20001) + 1),
		 new Point(R.nextInt(20001) + 1, R.nextInt(20001) + 1),
		 };
		this.name = R.ints(22).toString();
		this.id = R.ints(22).toString();
	}
}


