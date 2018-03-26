package com.intercom.domain;

/**
 * Created by Sougata Bhattacharjee
 * On 22.03.18
 */
public class Location {

    private Double latitude;
    private Double longitude;

    public Location() {
    }

    public Location(final Double latitude, final Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }
}
