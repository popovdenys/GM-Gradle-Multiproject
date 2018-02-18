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
	private String type;
	private String constellation;
	
	public Galaxy() {
	}
	
	public Galaxy(int id, String name, String type, String constellation) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.constellation = constellation;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	@Override
	public String toString() {
		return "Galaxy '" + name + "' of type " + type + " in constellation '" + constellation + "'";
	}
	
}
