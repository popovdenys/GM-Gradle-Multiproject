/*
 * File : DAO.java
 * Description : interface of Galaxies' DAO
 *
 * Author : Popov Denys
 * Created : 20/02/18
 *
 * Modified : { date: 20/02/18
 *             ,time: 10:45 PM }
 * Modified by: Popov Denys
 *
 * Last modification : functions to implement
 */

package po.galaxy.db;

import po.galaxy.domain.Expedition;
import po.galaxy.domain.Galaxy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao {

    public List<Expedition> getExpeditionsList();

    public List<Galaxy> createMap(ResultSet results) throws SQLException;

    public List<Galaxy> addNxtGalaxy(ResultSet results) throws SQLException;

    public List<Galaxy> getGalaxiesList();

    public Galaxy getGalaxyById(long id);

    public List<Galaxy> getGalaxiesByName(String name);

    public Expedition setExpedition(String contractor);

    public void addToExpedition(Long id, Galaxy galaxy);

    public void updateExpeditionStatus(Long id, String status);

    public Double getExpetionTotalDistance(Long id);

    public Expedition getExpedition(Long id);
}
