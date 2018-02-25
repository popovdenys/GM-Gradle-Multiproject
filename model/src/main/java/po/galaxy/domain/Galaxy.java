/*
 * File : Galaxy.java
 * Description : Galaxy bean
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 *
 * Modified : { date: 24/02/18
 *             ,time: 20:05 PM }
 * Modified by: Popov Denys
 * 
 * Last modification : make object Serializable
 */

package po.galaxy.domain;

import java.io.Serializable;

public class Galaxy implements Serializable {
	private long id;
	private String name;
	private GalaxyType type;
	private String constellation;
	private double distance;
	private String image;
	
	public Galaxy() {
	}
	
	public Galaxy(long id, String name, GalaxyType type, String constellation, double distance, String image) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.constellation = constellation;
		this.distance = distance;
		this.image = image;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
