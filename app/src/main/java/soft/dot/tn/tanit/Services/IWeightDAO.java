package soft.dot.tn.tanit.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;
import soft.dot.tn.tanit.Entitites.WeightData;

/**
 * Created by sofien on 10/03/2018.
 */

public interface IWeightDAO {
    @POST
    Call<String> AddWeight(@Url String url, @Body WeightData data);
    @POST
    Call<WeightData> GetWeightAt(@Url String url, @Body WeightData data);
    @POST
    Call<List<WeightData>> GetWeightBetween(@Url String url, String startDate, String endDate);
}
