package ee.kertmannik.quiz;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class QuestionSupplier {

    private String url;

    QuestionSupplier(String url) {
        this.url = url;
    }

    public String questionsRequest() {
        try {
            final String resultOkHttp = this.run(this.url);
            return resultOkHttp;
        } catch (IOException e) {
            throw new RuntimeException("Could not read raw questions at the url." + this.url);
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
            throw new RuntimeException("Could not read raw questions at the url." + this.url);
        }
    }
}
