package org.rockem.tdd.csv;

import org.rockem.tdd.csv.common.FindResult;

import java.util.ArrayList;
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
                if (new TextComparator(rowVals[c]).contains(text)) {
                    results.add(new FindResult(r + 1, c + 1, rowVals[c]));
                }
            }
        }
        return results;
    }


    private class TextComparator {

        private final String text;

        public TextComparator(String text) {
            this.text = text;
        }

        private boolean contains(String searchTerm) {
            if (isInLowercase(searchTerm)) {
                return text.toLowerCase().contains(searchTerm.toLowerCase());
            } else {
                return text.contains(searchTerm);
            }
        }

        private boolean isInLowercase(String str) {
            return str.toLowerCase().equals(str);
        }

    }

}
