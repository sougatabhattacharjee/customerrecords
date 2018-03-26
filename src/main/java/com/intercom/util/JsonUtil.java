package com.intercom.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.intercom.domain.Customer;
import com.intercom.domain.Location;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Sougata Bhattacharjee
 * On 22.03.18
 */
public class JsonUtil {

    private final static String USER_ID = "user_id";
    private final static String NAME = "name";
    private final static String LATITUDE = "latitude";
    private final static String LONGITUDE = "longitude";

    /**
     * The method convert customer record from string to customer object.
     *
     * @param jsonList list of string contains customer details
     * @return list of customer object
     */
    public static List<Customer> convertJsonStringToCustomerObject(final List<String> jsonList) {

        return validateJsonAndSkipInvalidEntries(jsonList)
                .stream()
                .map(jsonObject -> {
                    final Location location = new Location();
                    location.setLatitude(Double.valueOf((String) jsonObject.get(LATITUDE)));
                    location.setLongitude(Double.valueOf((String) jsonObject.get(LONGITUDE)));
                    return new Customer.Builder()
                            .withName((String) jsonObject.get(NAME))
                            .withUserId((int) jsonObject.get(USER_ID))
                            .withLocation(location)
                            .build();
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * validate the json string, if invalid skip them
     *
     * @param jsonList list of string contains customer details
     * @return list of json objects
     */
    private static List<JSONObject> validateJsonAndSkipInvalidEntries(final List<String> jsonList) {
        return jsonList.stream().map(JSONObject::new)
                .filter(IS_JSON_VALID).collect(Collectors.toList());
    }

    private static final Predicate<JSONObject> IS_USER_ID_EXIST =
            jsonObject -> jsonObject.has(USER_ID);

    private static final Predicate<JSONObject> IS_NAME_EXIST =
            jsonObject -> jsonObject.has(NAME);

    private static final Predicate<JSONObject> IS_LATITUDE_EXIST =
            jsonObject -> jsonObject.has(LATITUDE);

    private static final Predicate<JSONObject> IS_LONGITUDE_EXIST =
            jsonObject -> jsonObject.has(LONGITUDE);

    private static final Predicate<JSONObject> IS_JSON_VALID =
            Predicates.and(IS_USER_ID_EXIST, IS_NAME_EXIST, IS_LATITUDE_EXIST, IS_LONGITUDE_EXIST);

}
