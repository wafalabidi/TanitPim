package soft.dot.tn.tanit.Entitites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by sofien on 05/02/2018.
 */

public class User {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("firstName")
    private String firstName;
    @Expose
    @SerializedName("lastName")
    private String lastName;

    @Expose
    @SerializedName("inscriptionDate")
    private String inscriptionDate;

    @Expose
    @SerializedName("birthDate")
    private String birthDate;
    @Expose
    @SerializedName("email")
    String email;
    @Expose
    @SerializedName("age")
    String age;
    @Expose
    @SerializedName("temperatureData")
    List<TemperatureData> temperatureData;
    @Expose
    @SerializedName("weightData")
    List<WeightData> weightData ;
    @Expose
    @SerializedName("cycleData")
    List<Cycle> cycleData  ;

    public List<WeightData> getWeightData() {
        return weightData;
    }

    public void setWeightData(List<WeightData> weightData) {
        this.weightData = weightData;
    }

    public List<Cycle> getCycleData() {
        return cycleData;
    }

    public void setCycleData(List<Cycle> cycleData) {
        this.cycleData = cycleData;
    }

    public List<TemperatureData> getTemperatureData() {
        return temperatureData;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setTemperatureData(List<TemperatureData> temperatureData) {
        this.temperatureData = temperatureData;
    }

    private String imager_url;

    public String getImager_url() {
        return imager_url;
    }

    public void setImager_url(String imager_url) {
        this.imager_url = imager_url;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(String inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", inscriptionDate=" + inscriptionDate +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
