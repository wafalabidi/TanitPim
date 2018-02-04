package soft.dot.tn.tanit.Entitites;

import java.sql.Date;

/**
 * Created by sofien on 03/02/2018.
 */

public class TemperatureData {
    int id ;
    Date date ;
    float value ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public TemperatureData(Date date, float value) {
        this.date = date;
        this.value = value;
    }
}
