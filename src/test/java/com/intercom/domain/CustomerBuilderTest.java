package com.intercom.domain;

import org.junit.Test;

/**
 * Created by Sougata Bhattacharjee
 * On 25.03.18
 */
public class CustomerBuilderTest {

    private static final String customerName = "test customer";
    private static final int customerId = 1;
    private static final Location customerLocation = new Location();

    @Test(expected = IllegalArgumentException.class)
    public void testCustomerBuilderWithoutName() {
        new Customer.Builder().withName(null)
                .withUserId(customerId)
                .withLocation(customerLocation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomerBuilderWithoutId() {
        new Customer.Builder().withName(customerName)
                .withUserId(null)
                .withLocation(customerLocation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomerBuilderWithoutLocation() {
        new Customer.Builder().withName(customerName)
                .withUserId(customerId)
                .withLocation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomerBuilderWithoutLongitude() {
        new Customer.Builder().withName(customerName)
                .withUserId(customerId)
                .withLocation(new Location(51.00000, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomerBuilderWithoutLatitude() {
        new Customer.Builder().withName(customerName)
                .withUserId(customerId)
                .withLocation(new Location(null, 51.00000));
    }

    @Test
    public void testCustomerBuilder() {
        new Customer.Builder().withName(customerName)
                .withUserId(customerId)
                .withLocation(new Location(51.00000, 51.00000));
    }
}
