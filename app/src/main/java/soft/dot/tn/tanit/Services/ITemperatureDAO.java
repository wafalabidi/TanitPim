package soft.dot.tn.tanit.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;
import soft.dot.tn.tanit.Entitites.TemperatureData;

/**
 * Created by sofien on 26/02/2018.
 */

public interface ITemperatureDAO {
    //temperatureData/add/{id}
    @POST
    Call<String> AddTemperature(@Url String url, @Body TemperatureData data);
    ///temperatureData/getAt/{id}
    @POST
    Call<TemperatureData> GetTemperatureAt(@Url String url, @Body TemperatureData data);
    ///temperatureData/getBetween/{id}
    @POST
    Call<List<TemperatureData>> GetTemperatureBetween(@Url String url, String startDate, String endDate);

}
