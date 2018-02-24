/*
 * File : GalaxiesData.java
 * Description : database mock implementation
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 * 
 * Modified : { date: 20/02/18
 *             ,time: 10:16 PM }
 * Modified by: Popov Denys
 * 
 * Last modification : init h2 database
 */

package po.galaxy.db;

import po.galaxy.domain.Galaxy;
import po.galaxy.domain.GalaxyType;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GalaxiesData {
	private static final String DROP_TABLE_GALAXIES = "DROP TABLE IF EXISTS galaxies;";
	private static final String DROP_TABLE_EXPEDITIONS = "DROP TABLE IF EXISTS expeditions;";
	private static final String CREATE_GALAXIES_TABLE = "CREATE TABLE galaxies(" +
			"id INT PRIMARY KEY" +
            ", name VARCHAR (28)" +
            ", type VARCHAR (8)" +
            ", constellation VARCHAR (19)" +
            ", distance FLOAT" +
			", image VARCHAR (30));";
	public static final String INSERT_INTO_GALAXIES_TABLE = "INSERT INTO galaxies (id, name, type, constellation, distance, image) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String CREATE_EXPEDITIONS_TABLE = "CREATE TABLE expeditions (" +
            "id INT AUTO_INCREMENT PRIMARY KEY" +
            ", itinerary VARBINARY" +
            ", status VARCHAR(28)" +
            ", contractor VARCHAR(17))";

	private List<Galaxy> galaxies;

	protected static final String h2GalaxyUrl = "jdbc:h2:~/galaxy";

	public GalaxiesData() {
		this.galaxies = getGalaxiesList();
	}

	/**
	 * Database Initialize
	 */

	public void initDB() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(h2GalaxyUrl, "", "")) {
			try (PreparedStatement ps = conn.prepareStatement(DROP_TABLE_GALAXIES)) {
				ps.execute();
			}

			try (PreparedStatement ps = conn.prepareStatement(DROP_TABLE_EXPEDITIONS)) {
				ps.execute();
			}

			try (PreparedStatement ps = conn.prepareStatement(CREATE_GALAXIES_TABLE)) {
				ps.execute();
			}

			galaxies = getGalaxiesList();

			galaxies.stream().forEach( galaxy -> {

				try(PreparedStatement ps = conn.prepareStatement(INSERT_INTO_GALAXIES_TABLE);) {
					ps.setLong(1, galaxy.getId());
					ps.setString(2, galaxy.getName());
					ps.setString(3, galaxy.getType().toString());
					ps.setString(4, galaxy.getConstellation());
					ps.setDouble(5, galaxy.getDistance());
					ps.setString(6, galaxy.getImage());
					ps.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});

			try(PreparedStatement ps = conn.prepareStatement(CREATE_EXPEDITIONS_TABLE)) {
				ps.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DAO methods
	 * @return
	 */

	// get all notes

	public List<Galaxy> getGalaxiesList() {

		galaxies = new ArrayList<Galaxy>();

		galaxies.add(new Galaxy(0, "NGC 1300", GalaxyType.SBBC, "Eridanus", 61.3, ""));
		galaxies.add(new Galaxy(1, "NGC 2787", GalaxyType.SB0, "Ursa Major", 24, ""));
		galaxies.add(new Galaxy(2, "NGC 4314", GalaxyType.SBA, "Coma Berenices", 40, ""));
		galaxies.add(new Galaxy(3, "NGC 4921", GalaxyType.SBAB, "Coma Berenices", 320, ""));
		galaxies.add(new Galaxy(4, "Messier 95", GalaxyType.SBB, "Leo", 32.6, ""));
		galaxies.add(new Galaxy(5, "NGC 3953", GalaxyType.SBBC, "Ursa Major", 55.4, ""));
		galaxies.add(new Galaxy(6, "NGC 1073", GalaxyType.SBC, "Cetus", 80, ""));
		galaxies.add(new Galaxy(7, "Messier 108", GalaxyType.SBCD, "Ursa Major", 45, ""));
		galaxies.add(new Galaxy(8, "NGC 2903", GalaxyType.SBD, "Leo", 30.6, ""));
		galaxies.add(new Galaxy(9, "NGC 5398", GalaxyType.SBDM, "Centaurus", 55, ""));
		galaxies.add(new Galaxy(10, "NGC 55", GalaxyType.SBM, "Sculptor", 7.2, ""));
		galaxies.add(new Galaxy(11, "Messier 58", GalaxyType.SABRSB, "Virgo", 62, ""));
		galaxies.add(new Galaxy(12, "Messier 91", GalaxyType.SBB, "Coma Berenices", 63, ""));
		galaxies.add(new Galaxy(13, "Messier 95", GalaxyType.SBB, "Leo", 32.6, ""));
		galaxies.add(new Galaxy(14, "Messier 109", GalaxyType.SBB, "Ursa Major", 83.5, ""));
		galaxies.add(new Galaxy(15, "NGC 1365", GalaxyType.SBBSB, "Fornax", 56.2, ""));
		galaxies.add(new Galaxy(16, "NGC 2217", GalaxyType.SBA, "Canis Major", 65, ""));
		galaxies.add(new Galaxy(17, "Large Magellanic Clouds", GalaxyType.SBM, "Dorado, Mensa", 0.16, ""));
		galaxies.add(new Galaxy(18, "Small Magellanic Clouds", GalaxyType.SBM, "Tucana, Hydrus", 0.2, ""));
		galaxies.add(new Galaxy(19, "UGC 12158", GalaxyType.SB, "Pegasus", 384, ""));
		galaxies.add(new Galaxy(20, "NGC 1512", GalaxyType.SBRAB, "Horologium", 38, ""));
		galaxies.add(new Galaxy(21, "IC 5201", GalaxyType.SB, "Grus", 40, ""));

		return galaxies;
	}

	// find specific note

	public List<Galaxy> find(Predicate<? super Galaxy> criteria) {

		return galaxies.stream().filter(criteria).collect(Collectors.toList());
		
	}

	/**
	 * SEARCH CRITERIA
 	 */

	// by Name

	public static Predicate<? super Galaxy> byName(String searchCriteria) {
		
		return (Galaxy galaxy) -> galaxy.getName().toLowerCase().contains(searchCriteria.toLowerCase());
	}

	// by Id

	public static Predicate<? super Galaxy> byId(int id) {
		
		return (Galaxy galaxy) -> galaxy.getId() == id;
	}

	/**
	 * GET RANDOM ID SECTION
	 * @return Map<Integer, String>(RandomId, MathodName)
	 */

	// Math.random

	public static Callable<Map<Integer, String>> randomId() {
	    int limitIdWith = new GalaxiesData().galaxies.size() - 1;
        return () -> {
            Map<Integer, String> result = new HashMap<>();
            result.put((int)(Math.random() * limitIdWith), "randomId");
            return result;
        };
	}

	// SecureRandom

    public static Callable<Map<Integer, String>> secureRandomId() throws NoSuchAlgorithmException {

        SecureRandom algo = SecureRandom.getInstance("SHA1PRNG");

        int limitIdWith = new GalaxiesData().galaxies.size() - 1;
        return () -> {
            Map<Integer, String> result = new HashMap<>();
            result.put(algo.nextInt(limitIdWith), "secureRandomId");
            return result;
        };

    }

    // random tester mathod

    public static Map<Integer, Long> randomIdTester(Callable<Map<Integer, String>> getRandomMap) throws Exception {

        System.out.println("Start looking for random id ... ");

	    Map<Integer, Long> result = new HashMap<>();

        Instant beginsWith = Instant.now();

        Map<Integer, String> randomMap = getRandomMap.call();

        int foundId = randomMap.entrySet().iterator().next().getKey();

        Instant endsWith = Instant.now();

        Duration itTook = Duration.between(beginsWith, endsWith);

        result.put(foundId, itTook.toNanos());

        System.out.println(result);

        System.out.println(String.format("%s was accomplished in %d nanosec with the result id equals %d%n"
                , randomMap.entrySet().iterator().next().getValue(), itTook.toNanos(), foundId));

        return result;

    }

}
