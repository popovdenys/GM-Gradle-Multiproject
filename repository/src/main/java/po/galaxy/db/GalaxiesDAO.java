/*
 * File : GalaxiesDAO.java
 * Description : Galaxies DAO
 *
 * Author : Popov Denys
 * Created : 20/02/18
 *
 * Modified : { date: 20/02/18
 *             ,time: 10:18 PM }
 * Modified by: Popov Denys
 *
 * Last modification : h2 database interactions
 */

package po.galaxy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GalaxiesDAO {

    public GalaxiesDAO() {
        GalaxiesData galaxiesData = new GalaxiesData();
        galaxiesData.initDB();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(GalaxiesData.h2GalaxyUrl, "", "");
    }




}
