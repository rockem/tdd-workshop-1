package e2e.test.org.rockem.tdd.csv;

import test.org.rockem.tdd.csv.support.CSVBuilder;
import e2e.test.org.rockem.tdd.csv.support.CSVClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.rockem.tdd.csv.Application;
import org.rockem.tdd.csv.common.FindResult;

import java.util.Arrays;

import static e2e.test.org.rockem.tdd.csv.support.CSVClient.uploadedCSV;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TddCsvE2E {

    private static final String CSV_1 = new CSVBuilder()
            .row("3", "milk")
            .row("5", "bread")
            .row("1", "Cheese")
            .row("6", "broccoli").toCSV();

    private static final String CSV_2 = new CSVBuilder()
            .row("1", "lock").toCSV();

    @BeforeClass
    public static void setUp() throws Exception {
        Application.main();
    }

    @Test
    public void retrieveUploadedRawCSV() throws Exception {
        assertThat(uploadedCSV(CSV_1).get(), is(CSV_1));
    }

    @Test
    public void retrieveMoreThanOneCSV() throws Exception {
        CSVClient csv1 = uploadedCSV(CSV_1);
        CSVClient csv2 = uploadedCSV(CSV_2);
        assertThat(csv1.get(), is(CSV_1));
        assertThat(csv2.get(), is(CSV_2));
    }

    @Test
    public void retrieveCellContainingSpecificText() throws Exception {
        assertThat(uploadedCSV(CSV_1).find("br"), is(
                Arrays.asList(
                        new FindResult(2, 2, "bread"),
                        new FindResult(4, 2, "broccoli")
                )
        ));
    }
}
