package http.wenburgyan.top.androidhttpuse.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ywb on 2018-11-26.
 */

public class HttpClientTest {

    @Test
    public void testGet(){

        try{
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://gank.io/api/today");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            System.out.println("status line:\n"+response1.getStatusLine());
            System.out.println("headers length:\n"+response1.getAllHeaders().length);
            HttpEntity entity1 = response1.getEntity();
            String content = EntityUtils.toString(entity1);
            System.out.println("content:\n"+content);
            response1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("end");
        }
    }

    @Test
    public void testPost(){

        try{
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://gank.io/api/today");
            CloseableHttpResponse response1 = httpclient.execute(httpPost);
            System.out.println("status line:\n"+response1.getStatusLine());
            System.out.println("headers length:\n"+response1.getAllHeaders().length);
            HttpEntity entity1 = response1.getEntity();
            String content = EntityUtils.toString(entity1);
            System.out.println("content:\n"+content);
            response1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("end");
        }
    }
}
