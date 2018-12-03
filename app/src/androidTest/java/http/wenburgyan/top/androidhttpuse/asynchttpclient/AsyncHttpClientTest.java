package http.wenburgyan.top.androidhttpuse.asynchttpclient;

import android.content.Context;
import android.os.Handler;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.junit.Test;
import org.junit.runner.RunWith;

import cz.msebera.android.httpclient.Header;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AsyncHttpClientTest {
    static final String TAG = "ywb";

    @Test
    public void asyncHttpClientTest(){
        System.out.println("start");
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals(false, appContext.getMainLooper().isCurrentThread());
        new Handler(appContext.getMainLooper()).post(() -> http());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void http(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.baidu.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                Log.d(TAG, "onSuccess: "+"statusCode"+statusCode);
                Log.d(TAG, "onSuccess: "+new String(response));
                System.out.println("ywb:"+ new String(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d(TAG, "onFailure: "+"statusCode"+statusCode);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Log.d(TAG, "onRetry: "+"retryNo"+retryNo);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish: "+"onFinish");
                super.onFinish();
            }
        });
    }
}
