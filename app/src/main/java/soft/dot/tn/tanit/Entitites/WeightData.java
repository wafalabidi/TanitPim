package soft.dot.tn.tanit.Entitites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

/**
 * Created by Wafee on 05/02/2018.
 */

public class WeightData {
    @Expose
    @SerializedName("id")
    int id;
    @Expose
    @SerializedName("value")
    float value;
    @Expose
    @SerializedName("entryDate")
    String entryDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return entryDate;
    }

    public void setDate(String date) {
        this.entryDate = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public WeightData(String date, float value) {

        this.entryDate = date;
        this.value = value;
    }
}
