package e2e.test.org.rockem.tdd.csv.support;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import static org.apache.tomcat.util.http.fileupload.util.Streams.asString;

public class HTTP {

    public static String get(String url) throws IOException {
        return asString(HttpClients.createDefault().execute(new HttpGet(url)).getEntity().getContent());
    }

}
