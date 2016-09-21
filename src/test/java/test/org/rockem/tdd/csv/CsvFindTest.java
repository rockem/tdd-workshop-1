package test.org.rockem.tdd.csv;

import org.junit.Test;
import org.rockem.tdd.csv.CsvFind;
import org.rockem.tdd.csv.common.FindResult;
import test.org.rockem.tdd.csv.support.CSVBuilder;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CsvFindTest {

    @Test
    public void findCellsContainingText() throws Exception {
        String simpleCSV = new CSVBuilder()
                .row("kuku", "28")
                .row("popo", "21")
                .row("pocula", "22").toCSV();

        assertThat(new CsvFind(simpleCSV).find("po"),
                is(asList(
                        new FindResult(2, 1, "popo"),
                        new FindResult(3, 1, "pocula"))));
    }



}