package soft.dot.tn.tanit.Services;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import soft.dot.tn.tanit.Entitites.User;
import soft.dot.tn.tanit.Utils.API.RetrofitClient;

/**
 * Created by sofien on 07/02/2018.
 */

public class UserDAO {

    public void SignUpUser (User user,Callback<Response> callback){
        RetrofitClient retrofitClient = new RetrofitClient() ;
        IUserDAO   iUserDAO = retrofitClient.getRetrofit().create(IUserDAO.class);
        Call<Response> call = iUserDAO.signUp(user);
        call.enqueue(callback);
    }

}
