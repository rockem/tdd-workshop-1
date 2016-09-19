package e2e.test.org.rockem.tdd.csv.support;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CSVClient {

    private final String url;

    public CSVClient(String csvUrl) {
        this.url = csvUrl;
    }

    public static CSVClient uploadedCSV(String csv) throws IOException {
        CloseableHttpResponse response = post(csv);
        assertThat("Failed to upload csv", response.getStatusLine().getStatusCode(), is(201));
        return new CSVClient(response.getFirstHeader("Location").getValue());
    }

    private static CloseableHttpResponse post(String csv) throws IOException {
        CloseableHttpClient hc = HttpClients.createDefault();
        return hc.execute(createUploadPost(csv));
    }

    private static HttpPost createUploadPost(String csv) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost("http://localhost:8080/csvc");
        post.setEntity(new StringEntity(csv));
        return post;
    }

    public String get() throws IOException {
        return HTTP.get(url);
    }
}
