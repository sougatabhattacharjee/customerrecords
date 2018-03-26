package com.intercom.util;

import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sougata Bhattacharjee
 * On 22.03.18
 */
public class FileReader {

    /**
     * Read a file line by line and returns the list of lines
     *
     * @param file the given file
     * @return list of lines
     */
    public static List<String> fileReader(final String file) {
        Preconditions.checkArgument(file != null, "input file cannot be null");
        final InputStreamReader inputStreamReader =
                new InputStreamReader(FileReader.class.getResourceAsStream("/" + file));

        final BufferedReader br = new BufferedReader(inputStreamReader);

        return br.lines().collect(Collectors.toList());
    }
}
