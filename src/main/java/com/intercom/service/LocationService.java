package com.intercom.service;

import com.intercom.domain.Location;

/**
 * Created by Sougata Bhattacharjee
 * On 23.03.18
 */
public class LocationService {

    /**
     * The conversion ratio of Nautical to Statute mile is 1.15078,
     * which means 1 Nautical mile = 1,15078 Statute mile (approx)
     */
    private final static double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    /**
     * The conversion ratio of Miles to KM is 1.60934,
     */
    private final static double MILES_TO_KM = 1.60934;

    /**
     * when working in nautical miles,
     * the distance may be calculated directly by converting the central angle in degrees to minutes
     * (i.e. multiply by 60)
     */
    private final static Integer DEGREE_TO_MINUTE = 60;

    /**
     * The great-circle distance or orthodromic distance is the shortest distance
     * between two points on the surface of a sphere,
     * measured along the surface of the sphere (as opposed to a straight line through the sphere's interior).
     * The distance between two points in Euclidean space is the length of a straight line between them,
     * but on the sphere there are no straight lines.
     *
     * @param customerLocation given customer location as latitude and longitude
     * @param officeLocation   given dublin office location as latitude and longitude
     * @return the distance between customer and dublin office in kilometers
     * @see <a href="https://en.wikipedia.org/wiki/Great-circle_distance">https://en.wikipedia.org/wiki/Great-circle_distance</a>
     */
    public static double distanceInKMBetweenTwoLocations(final Location customerLocation,
                                                         final Location officeLocation) {

        final double lat1 = Math.toRadians(customerLocation.getLatitude());
        final double lon1 = Math.toRadians(customerLocation.getLongitude());
        final double lat2 = Math.toRadians(officeLocation.getLatitude());
        final double lon2 = Math.toRadians(officeLocation.getLongitude());

        final double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        final double nauticalMiles = DEGREE_TO_MINUTE * Math.toDegrees(angle);

        return statuteMilesToKM(nauticalMilesToStatuteMiles(nauticalMiles));
    }

    /**
     * Method to convert nautical miles to statute miles
     * Nautical Mile is a unit used in measuring distances at sea, equal to 1,852 metres (approximately 2,025 yards).
     * Statute Mile is a unit of linear measure equal to 1,760 yards (approximately 1.609 kilometres).
     *
     * @param nauticalMiles given nautical miles
     * @return the statute miles
     */
    private static double nauticalMilesToStatuteMiles(final double nauticalMiles) {
        return STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
    }

    /**
     * Method to convert statute miles to kilometers
     *
     * @param statuteMiles given statute miles
     * @return the kilometers
     */
    private static double statuteMilesToKM(final double statuteMiles) {
        return MILES_TO_KM * statuteMiles;
    }

}
