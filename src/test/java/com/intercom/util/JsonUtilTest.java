package com.intercom.util;

import com.intercom.domain.Customer;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Sougata Bhattacharjee
 * On 26.03.18
 */
public class JsonUtilTest {

    private List<String> customerLists;

    @Before
    public void setUp() {
        customerLists = FileReader.fileReader("test.txt");
    }

    @Test
    public void testJson() {
        final List<Customer> actualCustomers = JsonUtil.convertJsonStringToCustomerObject(customerLists);
        Assert.assertThat(actualCustomers.size(), equalTo(customerLists.size()));
        final List<String> customerNames = actualCustomers.stream()
                .map(Customer::getName)
                .collect(Collectors.toList());

        Assert.assertThat(customerNames.size(), equalTo(actualCustomers.size()));
        Assert.assertThat(customerNames, CoreMatchers.hasItems("Christina McArdle", "Alice Cahill"));
    }

    @Test
    public void testInvalidJsonEntries() {
        final List<Customer> actualCustomers =
                JsonUtil.convertJsonStringToCustomerObject(FileReader.fileReader("test_invalid_entries.txt"));

        // skipping all invalid entries and keeping only valid entry
        Assert.assertThat(actualCustomers.size(), equalTo(1));
        Assert.assertThat(actualCustomers.stream()
                .map(Customer::getName)
                .collect(Collectors.toList()), hasItem("Ian McArdle"));

    }
}
