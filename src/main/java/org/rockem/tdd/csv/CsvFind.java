package org.rockem.tdd.csv;

import org.rockem.tdd.csv.common.FindResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFind {

    private static final String ROW_BREAK = "\n";
    private static final String COLUMN_BREAK = ",";

    private final String[] csvLines;

    public CsvFind(String csv) {
        csvLines = csv.split(ROW_BREAK);
    }

    public List<FindResult> find(String text) {
        List<FindResult> results = new ArrayList<>();
        for (int r = 0; r < csvLines.length; r++) {
            String[] rowVals = csvLines[r].split(COLUMN_BREAK);
            for (int c = 0; c < rowVals.length; c++) {
                if (rowVals[c].contains(text)) {
                    results.add(new FindResult(r + 1, c + 1, rowVals[c]));
                }
            }
        }
        return results;
    }
}
