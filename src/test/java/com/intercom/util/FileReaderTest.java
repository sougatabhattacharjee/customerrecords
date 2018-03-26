package com.intercom.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Sougata Bhattacharjee
 * On 26.03.18
 */
public class FileReaderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFileReaderWithoutFile() {
        FileReader.fileReader(null);
    }

    @Test
    public void testFileReader() {
        final List<String> actualLines = FileReader.fileReader("test.txt");
        final String expectedLine1 = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        final String expectedLine2 = "{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}";

        Assert.assertThat(actualLines.size(), equalTo(2));
        actualLines.forEach(line -> Assert.assertThat(line, isOneOf(expectedLine1, expectedLine2)));
    }

}
