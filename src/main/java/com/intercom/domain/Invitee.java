package com.intercom.domain;

/**
 * Created by Sougata Bhattacharjee
 * On 24.03.18
 */
public class Invitee {

    private int inviteeId;
    private String inviteeName;

    public Invitee(final int inviteeId, final String inviteeName) {
        this.inviteeId = inviteeId;
        this.inviteeName = inviteeName;
    }

    public int getInviteeId() {
        return this.inviteeId;
    }

    public String getInviteeName() {
        return this.inviteeName;
    }
}
