package soft.dot.tn.tanit.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import soft.dot.tn.tanit.Entitites.WeightData;
import soft.dot.tn.tanit.Utils.API.RetrofitClient;

/**
 * Created by sofien on 10/03/2018.
 */

public class WeightDAO {

    private IWeightDAO IWeightInstantiater() {
        RetrofitClient retrofitClient = new RetrofitClient();
        return retrofitClient.getRetrofit().create(IWeightDAO.class);
    }

    public void AddTemperature(int id, WeightData weightData, Callback<String> callback) {
        String url = "/weightData/add/" + id;
        url.trim();
        Call<String> call = IWeightInstantiater().AddWeight(url, weightData);
        call.enqueue(callback);
    }

    public void GetTemperatureAt(int id, WeightData weightData, Callback<WeightData> callback) {
        String url = "/weightData/getAt/" + id;
        url.trim();
        Call<WeightData> call = IWeightInstantiater().GetWeightAt(url, weightData);
        call.enqueue(callback);
    }

    public void GetTemperatureBetween(int id, String startDate, String endDate, Callback<List<WeightData>> callback) {
        String url = "/weightData/getBetween/" + id;
        url.trim();
        Call<List<WeightData>> call = IWeightInstantiater().GetWeightBetween(url, startDate, endDate);
        call.enqueue(callback);


    }
}
