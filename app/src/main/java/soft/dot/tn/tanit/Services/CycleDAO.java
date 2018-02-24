package soft.dot.tn.tanit.Services;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import soft.dot.tn.tanit.Entitites.Cycle;
import soft.dot.tn.tanit.Utils.API.RetrofitClient;

/**
 * Created by sofien on 08/02/2018.
 */

public class CycleDAO {
    public void InsertFirstCycle(int id, Cycle cycle, Callback<ResponseBody> callback) {
        RetrofitClient retrofitClient = new RetrofitClient();
        ICycleDAO iUserDAO = retrofitClient.getRetrofit().create(ICycleDAO.class);
        String url = "/cycle/first/" + id;
        Call<ResponseBody> call = iUserDAO.insertFirstCycle(url, cycle);
        call.enqueue(callback);
    }
}
