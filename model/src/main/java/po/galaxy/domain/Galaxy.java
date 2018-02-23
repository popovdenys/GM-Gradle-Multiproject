/*
 * File : Galaxy.java
 * Description : Galaxy bean
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 * 
 * Modified : 01 Feb, 2018
 * Modified by: Popov Denys
 * 
 * Last modification : Galaxy object  
 */

package po.galaxy.domain;

public class Galaxy {
	private int id;
	private String name;
	private GalaxyType type;
	private String constellation;
	private double distance;
	private String image;
	
	public Galaxy() {
	}
	
	public Galaxy(int id, String name, GalaxyType type, String constellation, double distance, String image) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.constellation = constellation;
		this.distance = distance;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GalaxyType getType() {
		return type;
	}
	public void setType(GalaxyType type) {
		this.type = type;
	}
	public double getDistance() {return distance;}
	public void setDistance(double distance) {this.distance = distance;}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image= image;
	}

	@Override
	public String toString() {
		return "Galaxy '" + name + "' of type " + type + " in constellation '" + constellation + "'";
	}
	
}
