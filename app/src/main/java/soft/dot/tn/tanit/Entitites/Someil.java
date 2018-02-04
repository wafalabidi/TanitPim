package soft.dot.tn.tanit.Entitites;

/**
 * Created by sofien on 29/01/2018.
 */

public class Someil {
    String date;
    int heure;
    int seconde;

    public Someil() {

    }

    public Someil(String date, int heure, int seconde) {
        this.date = date;
        this.heure = heure;
        this.seconde = seconde;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getSeconde() {
        return seconde;
    }

    public void setSeconde(int seconde) {
        this.seconde = seconde;
    }
}
