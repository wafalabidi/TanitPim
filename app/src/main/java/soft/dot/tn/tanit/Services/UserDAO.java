package soft.dot.tn.tanit.Services;

import android.util.Log;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import soft.dot.tn.tanit.Entitites.User;
import soft.dot.tn.tanit.Utils.API.RetrofitClient;

/**
 * Created by sofien on 07/02/2018.
 */

public class UserDAO {

    public void SignUpUser(User user, Callback<String> callback) {

        RetrofitClient retrofitClient = new RetrofitClient();
        IUserDAO iUserDAO = retrofitClient.getRetrofit().create(IUserDAO.class);
        Call<String> call = iUserDAO.signUp(user);
        call.enqueue(callback);
    }

    public void logIn(User user, Callback<User> callback) {
        RetrofitClient retrofitClient = new RetrofitClient();
        IUserDAO iUserDAO = retrofitClient.getRetrofit().create(IUserDAO.class);
        Call<User> call = iUserDAO.logIn(user);
        call.enqueue(callback);

    }
}
