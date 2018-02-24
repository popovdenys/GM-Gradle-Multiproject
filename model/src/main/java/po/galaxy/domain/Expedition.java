/*
 * File : Expedition.java
 * Description : Expedition bean
 *
 * Author : Popov Denys
 * Created : 20/02/18
 *
 * Modified : { date: 23/02/18
 *             ,time: 12:00 PM }
 * Modified by: Popov Denys
 *
 * Last modification : Expedition object
 */

package po.galaxy.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Expedition {
    private long id;
    private Map<Galaxy, Double> itinerary = new ConcurrentHashMap<>();
    private String status;
    private String contractor;

    public Expedition() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItinerary(Map<Galaxy, Double> expeditions) { this.itinerary = expeditions; }

    public void setContractor(String contractor) { this.contractor = contractor; }

    public String getContractor() { return contractor; }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (Galaxy galaxy : itinerary.keySet()) {
            b.append(galaxy.getName() + " : " + itinerary.get(galaxy) + "<br/>");
        }
        return b.toString();
    }

    public void addToExpedition(Galaxy galaxy) {
        itinerary.put(galaxy, galaxy.getDistance());
    }

    public static double getExpeditionDistance(Map<Galaxy, Double> itinerary) {
        return itinerary.keySet().stream()
                .mapToDouble(galaxy->galaxy.getDistance())
                .reduce(0d, (g, g1) -> g + g1);
    }
}
