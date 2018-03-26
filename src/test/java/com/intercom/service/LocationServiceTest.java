package com.intercom.service;

import com.intercom.domain.Location;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by Sougata Bhattacharjee
 * On 25.03.18
 */
@RunWith(JUnitParamsRunner.class)
public class LocationServiceTest {

    static final Location london = new Location(51.509865, -0.118092);
    static final Location newYork = new Location(40.730610, -73.935242);
    static final Location california = new Location(36.778259, -119.417931);
    static final Location dublin = new Location(53.350140, -6.266155);
    static final Location cork = new Location(51.903614, -8.468399);

    @Test
    @Parameters(method = "params")
    public void test(final String testCase,
                     final Location source,
                     final Location destination,
                     final double approxDistanceInKM) {

        Assert.assertThat(LocationService.distanceInKMBetweenTwoLocations(source, destination),
                greaterThanOrEqualTo(approxDistanceInKM));
    }

    public static Collection<Object[]> params() {

        return Arrays.asList(
                new Object[][]{
                        {"Distance between cork and dublin",
                                cork, dublin, 210
                        },
                        {"Distance between london and new york",
                                london, newYork, 2200
                        }
                }
        );
    }

}
