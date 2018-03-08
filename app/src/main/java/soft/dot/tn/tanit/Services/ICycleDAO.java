package soft.dot.tn.tanit.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import soft.dot.tn.tanit.Entitites.Cycle;

/**
 * Created by sofien on 08/02/2018.
 */

public interface ICycleDAO {
    @POST
    Call<ResponseBody> insertFirstCycle(@Url String url, @Body Cycle user);
    @GET()
    Call<ResponseBody>  notifyCycleEnd (@Url String  url );
    @GET()
    Call <ResponseBody> notifyCycleStart(@Url String url);
    @GET()
    Call<ResponseBody> getAverageLength (@Url String url);

}
