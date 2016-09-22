package test.org.rockem.tdd.csv;

import org.junit.Test;
import org.rockem.tdd.csv.CsvFind;
import org.rockem.tdd.csv.common.FindResult;
import test.org.rockem.tdd.csv.support.CSVBuilder;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CsvFindTest {

    private static final String CSV = new CSVBuilder()
            .row("kuku", "28")
            .row("popo", "21")
            .row("pocula", "22")
            .row("KuKu", "28")
            .row("kulula", "22").toCSV();


    private final CsvFind csvFind = new CsvFind(CSV);

    @Test
    public void findCellsContainingText() throws Exception {
        assertThat(csvFind.find("po"),
                is(asList(
                        new FindResult(2, 1, "popo"),
                        new FindResult(3, 1, "pocula"))));
    }

    @Test
    public void findByCaseInsensitive() throws Exception {
        assertThat(csvFind.find("ku"),
                is(asList(
                        new FindResult(1, 1, "kuku"),
                        new FindResult(4, 1, "KuKu"),
                        new FindResult(5, 1, "kulula"))));
    }

    @Test
    public void findByCaseSensitive() throws Exception {
        assertThat(csvFind.find("Ku"),
                is(asList(
                        new FindResult(4, 1, "KuKu"))));
    }


}