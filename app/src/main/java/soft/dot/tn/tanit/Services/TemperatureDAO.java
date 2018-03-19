package soft.dot.tn.tanit.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import soft.dot.tn.tanit.Entitites.TemperatureData;
import soft.dot.tn.tanit.Utils.API.RetrofitClient;

/**
 * Created by sofien on 26/02/2018.
 */

public class TemperatureDAO {


    private ITemperatureDAO ItemperatureInstiater() {
        RetrofitClient retrofitClient = new RetrofitClient();
        return retrofitClient.getRetrofit().create(ITemperatureDAO.class);
    }

    public void AddTemperature(int id, TemperatureData temperatureData, Callback<String> callback) {
        String url = "/temperatureData/add/" + id;
        url.trim();
        Call<String> call = ItemperatureInstiater().AddTemperature(url, temperatureData);
        call.enqueue(callback);
    }

    public void GetTemperatureAt(int id, TemperatureData temperatureData, Callback<TemperatureData> callback) {
        String url = "/temperatureData/getAt/" + id;
        url.trim();
        Call<TemperatureData> call = ItemperatureInstiater().GetTemperatureAt(url, temperatureData);
        call.enqueue(callback);
    }

    public void GetTemperatureBetween(int id, String startDate, String endDate, Callback<List<TemperatureData>> callback) {
        String url = "/temperatureData/getBetween/" + id;
        url.trim();
        Call<List<TemperatureData>> call = ItemperatureInstiater().GetTemperatureBetween(url, startDate, endDate);
        call.enqueue(callback);
    }
}
