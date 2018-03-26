package com.intercom.domain;

import com.google.common.base.Preconditions;

/**
 * Created by Sougata Bhattacharjee
 * On 22.03.18
 */
public class Customer {

    private int user_id;
    private String name;
    private Location location;

    public int getUser_id() {
        return this.user_id;
    }

    private void setUser_id(final int userId) {
        this.user_id = userId;
    }

    public String getName() {
        return this.name;
    }

    private void setName(final String name) {
        this.name = name;
    }

    public Location getLocation() {
        return this.location;
    }

    private void setLocation(final Location location) {
        this.location = location;
    }

    public static class Builder {
        private int user_id;
        private String name;
        private Location location;

        public Builder() {
        }

        public Builder withUserId(final Integer userId) {
            Preconditions.checkArgument(userId != null, "UserID cannot be null");
            this.user_id = userId;
            return this;
        }

        public Builder withName(final String name) {
            Preconditions.checkArgument(name != null, "Name cannot be null");
            this.name = name;
            return this;
        }

        public Builder withLocation(final Location location) {
            Preconditions.checkArgument(location != null,
                    "Location cannot be null");
            Preconditions.checkArgument(location.getLatitude() != null,
                    "Latitude cannot be null");
            Preconditions.checkArgument(location.getLongitude() != null,
                    "Longitude cannot be null");
            this.location = location;
            return this;
        }

        public Customer build() {
            final Customer customer = new Customer();
            customer.setUser_id(user_id);
            customer.setName(name);
            customer.setLocation(location);

            return customer;
        }
    }
}
