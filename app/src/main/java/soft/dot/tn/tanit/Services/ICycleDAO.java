package soft.dot.tn.tanit.Services;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import soft.dot.tn.tanit.Entitites.Cycle;
import soft.dot.tn.tanit.Entitites.User;

/**
 * Created by sofien on 08/02/2018.
 */

public interface ICycleDAO {
    @POST("/cycle/first/{id}")
    Call<Response> insertFirstCycle(@Path("id") long id , @Body Cycle user);
    @GET("/cycle/endPeriod/{id}")
    Call<Response>  notifyCycleEnd (@Path("id") long id );
    @GET("/cycle/startPeriod/{id}")
    Call <Response> notifyCycleStart(@Path("id") long id);
    @GET("/cycle/getAvgLength/{lengthName}/{id}")
    Call<Response> getAverageLength (@Path("leghtName") LegnthName lengthName , @Path("id") long id);

}
