import com.intercom.InviteeFinder;
import com.intercom.domain.Invitee;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsCollectionContaining.hasItems;

/**
 * Created by Sougata Bhattacharjee
 * On 26.03.18
 */
public class InviteeFinderTest {

    /**
     * test_invitees.txt contains 7 customers.
     * 3 of the customers are from dublin and others are from new york, london, berlin and stockholm
     * The test should assert that the actual invitee list contain only customers from dublin
     * because others will not be within 100 kms from the dublin city office location.
     *
     * @throws IOException
     */
    @Test
    public void testInviteeFinder() throws IOException {
        final InviteeFinder inviteeFinder = new InviteeFinder();
        final List<Invitee> actualInviteesList =
                inviteeFinder.customersToBeInvited("test_invitees.txt");

        Assert.assertNotNull(actualInviteesList);
        Assert.assertThat(actualInviteesList.size(), IsEqual.equalTo(3));
        Assert.assertTrue(isSortedByInviteeId(actualInviteesList));
        Assert.assertThat(actualInviteesList.stream()
                .map(Invitee::getInviteeName)
                .collect(Collectors.toList()),
                hasItems("Jack Dempsey (from dublin)",
                        "Georgina Gallagher (from dublin)",
                        "Rose Enright (from dublin)"));
    }

    private static boolean isSortedByInviteeId(final Iterable<Invitee> invitees) {
        final Iterator<Invitee> iter = invitees.iterator();
        if (!iter.hasNext()) {
            return true;
        }
        Invitee invitee1 = iter.next();
        while (iter.hasNext()) {
            final Invitee invitee2 = iter.next();
            if (invitee1.getInviteeId() > invitee2.getInviteeId()) {
                return false;
            }
            invitee1 = invitee2;
        }
        return true;
    }
}
