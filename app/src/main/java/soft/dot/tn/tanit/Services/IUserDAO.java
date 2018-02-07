package soft.dot.tn.tanit.Services;


import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import soft.dot.tn.tanit.Entitites.User;

/**
 * Created by sofien on 07/02/2018.
 */

public interface IUserDAO {
    @POST("/temperatureData/add/")
    Call<Response> signUp (@Body User user);

}
