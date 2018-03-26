package com.intercom;

import com.intercom.domain.Customer;
import com.intercom.domain.Invitee;
import com.intercom.domain.Location;
import com.intercom.service.LocationService;
import com.intercom.util.FileReader;
import com.intercom.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sougata Bhattacharjee
 * On 22.03.18
 */
public class InviteeFinder {
    private static final Logger LOGGER = LoggerFactory.getLogger(InviteeFinder.class);
    private static final String CUSTOMERS_LIST = "gistfile1.txt";

    private static final double DUBLIN_OFFICE_LATITUDE = 53.339428;
    private static final double DUBLIN_OFFICE_LONGITUDE = -6.257664;

    /**
     * The method takes a text file as input
     * and returns the invitee lists who are within 100km from Dublin office location.
     *
     * @param customersList customers list as json format in a text file
     * @return invitee list sorted by id
     * @throws IOException
     */
    public List<Invitee> customersToBeInvited(final String customersList) throws IOException {

        final Location dublinOffice =
                new Location(DUBLIN_OFFICE_LATITUDE, DUBLIN_OFFICE_LONGITUDE);

        final List<Customer> customers = JsonUtil.convertJsonStringToCustomerObject(FileReader.fileReader(customersList));

        return customers.stream()
                .filter(customer ->
                        (LocationService.distanceInKMBetweenTwoLocations(customer.getLocation(), dublinOffice) <= 100)).
                        map(customer -> new Invitee(customer.getUser_id(), customer.getName())).
                        sorted(Comparator.comparing(Invitee::getInviteeId)).
                        collect(Collectors.toList());
    }


    public static void main(final String[] args) throws IOException {
        InviteeFinder inviteeFinder = new InviteeFinder();
        inviteeFinder.customersToBeInvited(CUSTOMERS_LIST).forEach(invitee ->
                LOGGER.info("Invitee with id -> {} and name -> {}",
                        invitee.getInviteeId(), invitee.getInviteeName()));
    }
}
