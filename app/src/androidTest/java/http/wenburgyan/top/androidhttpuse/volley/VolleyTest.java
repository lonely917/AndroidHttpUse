package http.wenburgyan.top.androidhttpuse.volley;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ywb on 2018-11-30.
 */

@RunWith(AndroidJUnit4.class)
public class VolleyTest {
    public static final String TAG = "ywb";

    @Test
    public void volleyTest(){
        Context appContext = InstrumentationRegistry.getTargetContext();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(appContext);
        String url = "https://www.baidu.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Log.d(TAG, "onResponse: "+response),
                error -> Log.d(TAG, "onErrorResponse: "+error.toString()));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.add(stringRequest);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void requestQueue(){
        Context appContext = InstrumentationRegistry.getTargetContext();

        RequestQueue mRequestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(appContext.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        String url ="https://www.baidu.com";

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.d(TAG, "onResponse: "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+error);
                    }
                });

        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
