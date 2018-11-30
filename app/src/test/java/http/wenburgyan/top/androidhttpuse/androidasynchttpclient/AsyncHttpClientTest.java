package http.wenburgyan.top.androidhttpuse.androidasynchttpclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.junit.Test;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ywb on 2018-11-29.
 */

public class AsyncHttpClientTest {

    //demo,没有Android上下文，运行会失败，需要在Android test中运行
    @Test
    public void test(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://gank.io/api/today", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                System.out.println("onStart");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                System.out.println(new String(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                System.out.println(new String(errorResponse));
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                System.out.println("onRetry :" + retryNo);
            }
        });
    }
}
