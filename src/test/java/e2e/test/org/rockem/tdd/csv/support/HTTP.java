package e2e.test.org.rockem.tdd.csv.support;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;

import java.io.IOException;

import static java.lang.String.format;
import static org.apache.tomcat.util.http.fileupload.util.Streams.asString;
import static org.hamcrest.core.Is.is;

public class HTTP {

    private static final String GET_ERROR_MSG = "Failed to get from %s. error: %s";


    public static String get(String url) throws IOException {
        CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(url));
        String body = asString(response.getEntity().getContent());
        Assert.assertThat(format(GET_ERROR_MSG, url, body), response.getStatusLine().getStatusCode(), is(200));
        return body;
    }

}
