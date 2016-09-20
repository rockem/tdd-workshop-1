package test.org.rockem.tdd.csv.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class CSVBuilder {

    private List<String[]> rows = new ArrayList<>();

    public CSVBuilder row(String... values) {
        rows.add(values);
        return this;
    }

    public String toCSV() {
        return rows.stream().map(r ->
                Arrays.stream(r).collect(joining(","))
        ).collect(joining("\n"));
    }
}
