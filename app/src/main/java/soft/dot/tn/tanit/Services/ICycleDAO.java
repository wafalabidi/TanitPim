package soft.dot.tn.tanit.Services;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import soft.dot.tn.tanit.Entitites.Cycle;
import soft.dot.tn.tanit.Entitites.User;

/**
 * Created by sofien on 08/02/2018.
 */

public interface ICycleDAO {
    @POST
    Call<ResponseBody> insertFirstCycle(@Url String url, @Body Cycle user);
    @GET("/cycle/endPeriod/{id}")
    Call<ResponseBody>  notifyCycleEnd (@Path("id") long id );
    @GET("/cycle/startPeriod/{id}")
    Call <ResponseBody> notifyCycleStart(@Path("id") long id);
    @GET("/cycle/getAvgLength/{lengthName}/{id}")
    Call<ResponseBody> getAverageLength (@Path("leghtName") LegnthName lengthName , @Path("id") long id);

}
