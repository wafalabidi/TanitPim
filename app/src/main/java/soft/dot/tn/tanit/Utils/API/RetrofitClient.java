package soft.dot.tn.tanit.Utils.API;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sofien on 04/02/2018.
 */

public class RetrofitClient extends AppCompatActivity {


     //public static final String BASE_URL = "http://192.168.100.191:8080";
    // public static final String BASE_URL = "http://192.168.1.102:8080";
     public static final String BASE_URL = "http://monivulation.azurewebsites.net";
     //public static final String BASE_URL = "http://172.16.247.198:8080";
    // public static final String BASE_URL ="http://10.0.2.2:8888/DivroceBook/";

    protected Retrofit retrofit;
    public Retrofit getRetrofit() {
        return retrofit;
    }


    public RetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}