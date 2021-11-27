package pl.pawit.NYT.Articles.adapter;


import com.google.gson.Gson;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.pawit.NYT.Articles.model.ListOfArticlesPOJO;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Setter
@Component
public class NytApiConnector {

    private final static String KEY = "U6RGpZmzNDVJTLwF3u1IGAjH99jbSYgm";
    private HttpClient client;
    private int responseCode;

    public NytApiConnector() {
        this.client = HttpClient.newHttpClient();
    }

    private HttpRequest createHttpRequest(String section) {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://api.nytimes.com/svc/topstories/v2/"
                        + section + ".json?api-key="
                        + KEY))
                .build();
    }

    public HttpResponse<String> getHttpResponse(String section) {
        HttpResponse<String> response = null;
        try {
            return client.send(createHttpRequest(section),
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ListOfArticlesPOJO createPOJOArticlesFromJson(String section) {

        if (getHttpResponse(section).statusCode() == 200) {
            Gson gson = new Gson();
            return gson.fromJson(getHttpResponse(section).body(), ListOfArticlesPOJO.class);
        }
        throw new IllegalArgumentException("Bad argument of http request");
    }

    public static void main(String[] args) {


        NytApiConnector nytApiConnector = new NytApiConnector();
        try {
            ListOfArticlesPOJO list = nytApiConnector.createPOJOArticlesFromJson("world");
        } catch (IllegalArgumentException e) {
            nytApiConnector.responseCode = HttpStatus.NOT_FOUND.value();
            System.out.println(e.getMessage());
        }


    }
}
