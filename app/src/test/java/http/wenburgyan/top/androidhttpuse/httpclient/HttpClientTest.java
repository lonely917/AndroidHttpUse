package http.wenburgyan.top.androidhttpuse.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import cz.msebera.android.httpclient.HttpEntity;
//import cz.msebera.android.httpclient.HttpResponse;
//import cz.msebera.android.httpclient.NameValuePair;
//import cz.msebera.android.httpclient.client.ClientProtocolException;
//import cz.msebera.android.httpclient.client.ResponseHandler;
//import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
//import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
//import cz.msebera.android.httpclient.client.methods.HttpGet;
//import cz.msebera.android.httpclient.client.methods.HttpPost;
//import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
//import cz.msebera.android.httpclient.impl.client.HttpClients;
//import cz.msebera.android.httpclient.message.BasicNameValuePair;
//import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by ywb on 2018-11-26.
 */

public class HttpClientTest {

    //get的使用
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

    //post的使用
    @Test
    public void testPost(){

        try{
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://gank.io/api/today");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vi=p"));
            nvps.add(new BasicNameValuePair("password", "s=/ecret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            System.out.println(httpPost.toString());
            System.out.println(EntityUtils.toString(httpPost.getEntity()));
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

    //responsehandler的使用
    @Test
    public void testResponseHandler(){
        try{
            ResponseHandler<String> handlerString = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    System.out.println("handleResponse");
                    System.out.println("status line:\n"+response.getStatusLine());
                    System.out.println("headers length:\n"+response.getAllHeaders().length);
                    HttpEntity entity1 = response.getEntity();
                    String content = EntityUtils.toString(entity1);
                    return content;
                }
            };

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://gank.io/api/today");
            String responseBody = httpclient.execute(httpGet, handlerString);
            System.out.println("responseBody:\n"+responseBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("end");
        }
    }

    //fluent api 的使用 org.apache.httpcomponents:fluent-hc:4.3.5
    @Test
    public void testFluent(){
        try {
            String content = Request.Get("https://gank.io/api/today")
                                .connectTimeout(3000)
                                .socketTimeout(3000)
                                .execute().returnContent().asString();
            System.out.println(content);

//            content = Request.Post("https://gank.io/api/today").execute().returnContent().asString();
//            System.out.println(content);
            HttpResponse response = Request.Post("https://gank.io/api/today")
                                            .bodyForm(Form.form().add("username",  "v=ip").add("password",  "=secret").build())
                                            .execute().returnResponse();
            System.out.println(response.toString());// status line + headergroups
            System.out.println(response.getStatusLine());// status line
            System.out.println(EntityUtils.toString( response.getEntity()));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("finish");
        }
    }

}
