package soft.dot.tn.tanit.Services;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import soft.dot.tn.tanit.Entitites.User;

/**
 * Created by sofien on 07/02/2018.
 */

public interface IUserDAO {
    @POST("/users/")
    Call<ResponseBody> signUp(@Body User user);

}
