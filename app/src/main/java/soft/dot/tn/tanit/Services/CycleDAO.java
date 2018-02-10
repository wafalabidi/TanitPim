package soft.dot.tn.tanit.Services;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import soft.dot.tn.tanit.Entitites.Cycle;
import soft.dot.tn.tanit.Utils.API.RetrofitClient;

/**
 * Created by sofien on 08/02/2018.
 */

public class CycleDAO {
    public void InsertFirstCycle(long id, Cycle cycle, Callback<Response> callback) {
        RetrofitClient retrofitClient = new RetrofitClient();
        ICycleDAO iUserDAO = retrofitClient.getRetrofit().create(ICycleDAO.class);
        Call<Response> call = iUserDAO.insertFirstCycle(id, cycle);
        call.enqueue(callback);
    }
}
