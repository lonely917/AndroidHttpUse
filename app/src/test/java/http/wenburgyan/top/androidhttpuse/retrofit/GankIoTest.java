package http.wenburgyan.top.androidhttpuse.retrofit;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by ywb on 2018-11-21.
 */

public class GankIoTest {
    public static final String API_URL = "https://gank.io/api/";

    public class TodayResponse{
        String error;
    }

    public class CategoryResponse{
        String error;
        List<Category> results;
    }

    public class Category{
        String _id;
        String en_name;
        String name;
        String rank;
    }
    public interface GankIO{
        @GET("today")
        Call<TodayResponse> today();

        @GET("xiandu/categories")
        Call<CategoryResponse> categaries();
    }

    @Test
    public void test() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankIO gankIO = retrofit.create(GankIO.class);

        Call<TodayResponse> call = gankIO.today();
        try {
            Response<TodayResponse> response = call.execute();
            System.out.println(response.toString());
            TodayResponse todayResponse = response.body();
            System.out.println(todayResponse.error);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Call<CategoryResponse> callCategory = gankIO.categaries();
//        CategoryResponse categoryResponse = callCategory.execute().body();
//        System.out.println(categoryResponse.results.size());
    }
}
