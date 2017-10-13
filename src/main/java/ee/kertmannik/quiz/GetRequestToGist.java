package ee.kertmannik.quiz;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GetRequestToGist {

    public static final String URL =
            "https://gist.githubusercontent.com/KertMannikFortumo/6b17dca9c9ae8ff089d3c50aa7a03329/raw/01cbbd75ed39d917d008881ee6db8f140663a17a/gistfile1.txt";

    public String questionsRequest() {
        try {
            final String resultOkHttp = this.run(URL);
            System.out.println(resultOkHttp);
            return resultOkHttp;
        } catch (IOException e) {
            return "";
        }
    }

    private String run(String url) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return "";
        }
    }
}
