package http.wenburgyan.top.androidhttpuse.retrofit;

import org.junit.Test;

import java.io.IOException;

public class RetrofitTest {

    @Test
    public void testSimpleService() throws IOException {
        SimpleService.main("");
    }

    @Test
    public void testSimpleMockService() throws IOException {
        SimpleMockService.main("");
    }

    @Test
    public void testGankio(){

    }
}
