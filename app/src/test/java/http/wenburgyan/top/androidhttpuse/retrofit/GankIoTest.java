package http.wenburgyan.top.androidhttpuse.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
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
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GankIO gankIO = retrofit.create(GankIO.class);

        System.out.println("call today");
        Call<TodayResponse> call = gankIO.today();
        try {
            Response<TodayResponse> response = call.execute();
            System.out.println(response.toString());
            TodayResponse todayResponse = response.body();
            System.out.println(new Gson().toJson(todayResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("call categories");
        try {
            Call<CategoryResponse> callCategory = gankIO.categaries();
            Response<CategoryResponse> response = callCategory.execute();
            System.out.println(response.toString());
            CategoryResponse categoryResponse = response.body();
            System.out.println(new Gson().toJson(categoryResponse));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void testEnque(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankIO gankIO = retrofit.create(GankIO.class);

        System.out.println("call today");
        Call<TodayResponse> call = gankIO.today();

        call.enqueue(new Callback<TodayResponse>() {
            @Override
            public void onResponse(Call<TodayResponse> call, Response<TodayResponse> response) {
                System.out.println(response.toString());
                TodayResponse todayResponse = response.body();
                System.out.println(new Gson().toJson(todayResponse));
            }

            @Override
            public void onFailure(Call<TodayResponse> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
