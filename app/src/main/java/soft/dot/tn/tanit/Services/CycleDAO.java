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
        String url = "/cycle/first/" + id;
        Call<ResponseBody> call = ICycleDAOInstatiter().insertFirstCycle(url, cycle);
        call.enqueue(callback);
    }

    public void EndCycle(int id, Callback<ResponseBody> callBack) {
        String url = "cycle/endPeriod/" + id;
        url.trim();
        Call<ResponseBody> call = ICycleDAOInstatiter().notifyCycleEnd(url);
        call.enqueue(callBack);
    }

    public void GetAverageLength(int id, Callback<ResponseBody> callBack, LegnthName legnthName) {
        String url = "/cycle/getAvgLength/" + legnthName + "/" + id;
        url.trim();
        Call<ResponseBody> call = ICycleDAOInstatiter().notifyCycleStart(url);
        call.enqueue(callBack);

    }

    public void StartCycle(int id, Callback<ResponseBody> callBack) {
        String url = "/cycle/startPeriod/" + id;
        url.trim();
        Call<ResponseBody> call = ICycleDAOInstatiter().notifyCycleStart(url);
        call.enqueue(callBack);
    }

    private ICycleDAO ICycleDAOInstatiter() {

        RetrofitClient retrofitClient = new RetrofitClient();
        return retrofitClient.getRetrofit().create(ICycleDAO.class);
    }
}
