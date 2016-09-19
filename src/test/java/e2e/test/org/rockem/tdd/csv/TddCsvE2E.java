package e2e.test.org.rockem.tdd.csv;

import e2e.test.org.rockem.tdd.csv.support.CSVBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.rockem.tdd.csv.Application;

import static e2e.test.org.rockem.tdd.csv.support.CSVClient.uploadedCSV;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TddCsvE2E {

    private static final String CSV = new CSVBuilder()
            .row("3", "milk")
            .row("5", "bread")
            .row("1", "Cheese").toCSV();

    @BeforeClass
    public static void setUp() throws Exception {
        Application.main();
    }

    @Test
    public void retrieveUploadedRawCSV() throws Exception {
        assertThat(uploadedCSV(CSV).get(), is(CSV));
    }

}
