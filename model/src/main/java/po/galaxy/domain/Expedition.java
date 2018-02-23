/*
 * File : Expedition.java
 * Description : Expedition bean
 *
 * Author : Popov Denys
 * Created : 20/02/18
 *
 * Modified : { date: 20/02/18
 *             ,time: 10:46 PM }
 * Modified by: Popov Denys
 *
 * Last modification : Expedition object
 */


package po.galaxy.domain;

public class Expedition {
    private int id;
    private String itinerary;
    private String status;

    public Expedition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
