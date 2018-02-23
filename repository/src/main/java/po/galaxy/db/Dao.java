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

import java.util.List;

public interface Dao {
    public List<Galaxy> getGalaxiesList();

    public List<Galaxy> findById(int id);

    public List<Galaxy> findByName(String name);

    public Expedition startExpedition(Character client);

    public Double getTotalDistance(int id);
}
