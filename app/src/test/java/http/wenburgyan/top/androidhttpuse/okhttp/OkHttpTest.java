package http.wenburgyan.top.androidhttpuse.okhttp;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpTest {

    @Test
    public void testOkHttp(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://raw.github.com/square/okhttp/master/README.md")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.toString());
            System.out.println(response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGankIO() throws IOException, InterruptedException {
        String baseUrl = "https://gank.io/api/";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseUrl+"today")
                .build();

        System.out.println("test synchronized");
        Call callToday = client.newCall(request);
        Response responseToday = callToday.execute();
        System.out.println(responseToday);
        System.out.println(responseToday.headers());
        System.out.println(responseToday.body().string());

//        System.out.println("test asynchronized");
//        Call callToday = client.newCall(request);
//        callToday.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println(response);
//                System.out.println(response.headers());
//                System.out.println(response.body().string());
//            }
//        });
//        Thread.sleep(2000);
    }
}
