/*
 * File : Expedition.java
 * Description : Expedition bean
 *
 * Author : Popov Denys
 * Created : 20/02/18
 *
 * Modified : { date: 24/02/18
 *             ,time: 8:06 PM }
 * Modified by: Popov Denys
 *
 * Last modification : itinerary to LinkedHashMap
 */

package po.galaxy.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Expedition {
    private long id;
    private Map<Galaxy, Double> itinerary = new LinkedHashMap<>();
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

    public Map<Galaxy, Double> getItinerary() { return itinerary; }

    public void setContractor(String contractor) { this.contractor = contractor; }

    public String getContractor() { return contractor; }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (Galaxy galaxy : itinerary.keySet()) {
            b.append("<br/>" + galaxy.getName() + " : " + itinerary.get(galaxy) + "(Mly)");
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
