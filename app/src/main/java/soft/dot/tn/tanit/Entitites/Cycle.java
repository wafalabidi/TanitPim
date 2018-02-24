package soft.dot.tn.tanit.Entitites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sofien on 08/02/2018.
 */

public class Cycle {
    @Expose
    @SerializedName("id")
    private long id;
    @Expose
    @SerializedName("startDate")
    private String startDate;
    @Expose
    @SerializedName("length")
    private int lenght;
    @Expose
    @SerializedName("fertilityStartDate")
    private String fertilityStartDate;
    @Expose
    @SerializedName("fertilityEndDate")
    private String fertilityEndDate;
    @Expose
    @SerializedName("lutealLength")
    private int lutualLength;
    @Expose
    @SerializedName("follicularLength")
    private int folicularLength;

    @Override
    public String toString() {
        return "Cycle{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", lenght=" + lenght +
                ", fertilityStartDate='" + fertilityStartDate + '\'' +
                ", fertilityEndDate='" + fertilityEndDate + '\'' +
                ", lutualLength=" + lutualLength +
                ", folicularLength=" + folicularLength +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String getFertilityStartDate() {
        return fertilityStartDate;
    }

    public void setFertilityStartDate(String fertilityStartDate) {
        this.fertilityStartDate = fertilityStartDate;
    }

    public String getFertilityEndDate() {
        return fertilityEndDate;
    }

    public void setFertilityEndDate(String fertilityEndDate) {
        this.fertilityEndDate = fertilityEndDate;
    }

    public int getLutualLength() {
        return lutualLength;
    }

    public void setLutualLength(int lutualLength) {
        this.lutualLength = lutualLength;
    }

    public int getFolicularLength() {
        return folicularLength;
    }

    public void setFolicularLength(int folicularLength) {
        this.folicularLength = folicularLength;
    }
}
