/*
 * File : GalaxiesData.java
 * Description : database mock implementation
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 * 
 * Modified : 01 Feb, 2018
 * Modified by: Popov Denys
 * 
 * Last modification : some search patterns 
 */

package po.galaxy.db;

import po.galaxy.domain.Galaxy;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
	List<Galaxy> galaxies = new ArrayList<Galaxy>();

	public GalaxiesData() {
		galaxies.add(new Galaxy(0, "NGC 1300", "SBbc", "Eridanus"));
		galaxies.add(new Galaxy(1, "NGC 2787", "SB0", "Ursa Major"));
		galaxies.add(new Galaxy(2, "NGC 4314", "SBa", "Coma Berenices"));
		galaxies.add(new Galaxy(3, "NGC 4921", "SBab", "Coma Berenices"));
		galaxies.add(new Galaxy(4, "Messier 95", "SBb", "Leo"));
		galaxies.add(new Galaxy(5, "NGC 3953", "SBbc", "Ursa Major"));
		galaxies.add(new Galaxy(6, "NGC 1073", "SBc", "Cetus"));
		galaxies.add(new Galaxy(7, "Messier 108", "SBcd", "Ursa Major"));
		galaxies.add(new Galaxy(8, "NGC 2903", "SBd", "Leo"));
		galaxies.add(new Galaxy(9, "NGC 5398", "SBdm", "Centaurus"));
		galaxies.add(new Galaxy(10, "NGC 55", "SBm", "Sculptor"));
		galaxies.add(new Galaxy(11, "M58", "SBc", "Virgo"));
		galaxies.add(new Galaxy(12, "M91", "SBb", "Coma Berenices"));
		galaxies.add(new Galaxy(13, "M95", "SBb", "Leo"));
		galaxies.add(new Galaxy(14, "M109", "SBb", "Ursa Major"));
		galaxies.add(new Galaxy(15, "NGC 1365", "SBc", "Fornax"));
		galaxies.add(new Galaxy(16, "NGC 2217", "SBa", "Canis Major"));
		galaxies.add(new Galaxy(17, "Magellanic Clouds", "SBm", "Dorado, Tucana"));
		galaxies.add(new Galaxy(18, "UGC 12158", "SB", "Pegasus"));
		galaxies.add(new Galaxy(19, "NGC 1512", "SB(r)ab", "Horologium"));
		galaxies.add(new Galaxy(20, "IC 5201", "SB", "Grus"));
	}

	/**
	 * DAO methods
	 * @return
	 */

	// get all notes

	public List<Galaxy> getAllGalaxies() {
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
