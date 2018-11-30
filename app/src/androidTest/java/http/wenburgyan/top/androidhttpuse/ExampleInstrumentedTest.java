package http.wenburgyan.top.androidhttpuse;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.junit.Test;
import org.junit.runner.RunWith;

import cz.msebera.android.httpclient.Header;
import top.wenburgyan.http.MainActivity;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private MainActivity mainActivity;

    static final String TAG = "ywb";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("http.wenburgyan.top.androidhttpuse", appContext.getPackageName());

    }

    @Test
    public void test(){
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
