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

import po.galaxy.domain.Expedition;
import po.galaxy.domain.Galaxy;
import po.galaxy.domain.GalaxyType;
import po.galaxy.domain.StatusType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GalaxiesDAO implements Dao {

    public GalaxiesDAO() {
//        System.out.println("Initialize h2 database");
//        GalaxiesData galaxiesData = new GalaxiesData();
//        galaxiesData.initDB();
    }

    private final Connection getConnection() {
        try { Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) { e.printStackTrace(); }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(GalaxiesData.h2GalaxyUrl, "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


    @Override
    public List<Expedition> getExpeditionsList() {

        List<Expedition> expeditions = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM expeditions");
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Expedition expedition = new Expedition();
                expedition.setId(results.getLong("id"));
                expedition.setStatus(results.getString("status"));
                expedition.setContractor(results.getString("contractor"));
                expedition.setItinerary((Map<Galaxy, Double>) castToMap(results.getBytes("itinerary")));
                expeditions.add(expedition);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expeditions;
    }

    @Override
    public List<Galaxy> createMap(ResultSet results) {
        List<Galaxy> galaxies = new ArrayList<>();
        try {
            while (results.next()) {
                Galaxy galaxy = new Galaxy();
                galaxy.setId(results.getLong("id"));
                galaxy.setName(results.getString("name"));
                galaxy.setType(GalaxyType.valueOf(results.getString("type")));
                galaxy.setConstellation(results.getString("constellation"));
                galaxy.setDistance(results.getDouble("distance"));
                galaxy.setImage(results.getString("image"));
                galaxies.add(galaxy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return galaxies;
    }

    @Override
    public List<Galaxy> addNxtGalaxy(ResultSet results) throws SQLException {
        return null;
    }

    @Override
    public List<Galaxy> getGalaxiesList() {
        List<Galaxy> galaxies = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM galaxies");) {

            ResultSet results = ps.executeQuery();
            galaxies = createMap(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return galaxies;
    }

    // ToDo create unit test!
    @Override
    public Galaxy getGalaxyById(long galaxyId) {
        List<Galaxy> galaxies = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM galaxies WHERE id = ?");){

            ps.setLong(1, galaxyId);

            ResultSet results = ps.executeQuery();
            galaxies = createMap(results);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return (galaxies.isEmpty()) ? null : galaxies.get(0);
    }

    @Override
    public List<Galaxy> getGalaxiesByName(String searchingGalaxy) {
        List<Galaxy> galaxies = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM galaxies WHERE name LIKE ?");){

            ps.setString(1, "%" + searchingGalaxy + "%");

            ResultSet results = ps.executeQuery();
            galaxies = createMap(results);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return galaxies;
    }

    @Override
    public Expedition setExpedition(String contractor) {
        Expedition expedition = new Expedition();
        expedition.setStatus(StatusType.PROJECT.get());
        expedition.setContractor(contractor);

        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO expeditions (status, contractor) values (?, ?)", Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, expedition.getStatus());
            ps.setString(2, expedition.getContractor());

            int expeditionCreated = ps.executeUpdate();
            try(ResultSet newId = ps.getGeneratedKeys()) {
                newId.next();
                long expeditionId = newId.getLong(1);
                expedition.setId(expeditionId);
                System.out.println(String.format("%d expedition created with id = %d", expeditionCreated, expeditionId));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expedition;
    }

    @Override
    public void addToExpedition(Long id, Galaxy galaxy) {
        try(Connection connection = getConnection();
        PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM expeditions WHERE id = ?");
        PreparedStatement psUpdate = connection.prepareStatement( "UPDATE expeditions SET itinerary = ? WHERE id = ?");){
            psSelect.setLong(1, id);
            ResultSet results = psSelect.executeQuery();

            results.next();

            byte[] itineraryInBytes = results.getBytes("itinerary");

            Map<Galaxy, Double> itinerary = castToMap(itineraryInBytes);

            itinerary.put(galaxy, galaxy.getDistance());

            psUpdate.setObject(1, itinerary);
            psUpdate.setLong(2, id);

            int updatedCount = psUpdate.executeUpdate();

            System.out.println(String.format("Updated %d row in expeditions table", updatedCount));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateExpeditionStatus(Long id, String status) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE expeditions SET status = ? WHERE id = ?");) {

            ps.setString(1, status);
            ps.setLong(2, id);

            int updatedRow = ps.executeUpdate();

            System.out.println("Expedition status was updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Double getExpetionTotalDistance(Long id) {
        double distance = 0d;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT itinerary FROM expeditions WHERE id = ?");) {

            ps.setLong(1, id);
            ResultSet results = ps.executeQuery();
            results.next();
            Map<Galaxy, Double> expeditionMap = castToMap(results.getBytes(1));

            distance = Expedition.getExpeditionDistance(expeditionMap);

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return distance;
    }

    @Override
    public Expedition getExpedition(Long id) {

        System.out.println("CALL getExpedition with id = " + id);
        Expedition expedition = null;

        try(Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT  * FROM expeditions WHERE id = ?");) {

            ps.setLong(1, id);
            ResultSet results = ps.executeQuery();

            results.next();

            expedition = new Expedition();
            expedition.setId(results.getLong("id"));
            expedition.setContractor(results.getString("contractor"));
            expedition.setStatus(results.getString("status"));
            expedition.setItinerary( (Map<Galaxy, Double>) castToMap(results.getBytes("itinerary")));

        }catch (SQLException e) {
            System.out.println("Here could be a problem");
            e.printStackTrace();
        }

        return expedition;
    }

    /**
     *
     * @param in
     * @return Map of Galaxies and their distances
     */
    private Map<Galaxy, Double> castToMap(byte[] in){
        Map<Galaxy, Double> itinerary = null;

        if (in != null) {

            ByteArrayInputStream bytes = new ByteArrayInputStream(in);

            try(ObjectInputStream input = new ObjectInputStream(bytes)) {

                itinerary = (Map<Galaxy, Double>) input.readObject();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else
            itinerary = new LinkedHashMap<>();

        return itinerary;
    }
}
