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
}
