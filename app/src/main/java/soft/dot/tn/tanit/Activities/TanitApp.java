package soft.dot.tn.tanit.Activities;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by sofien on 08/03/2018.
 */

public class TanitApp extends Application {
    private static final TanitApp ourInstance = new TanitApp();

    static TanitApp getInstance() {

        return ourInstance;
    }

    public  TanitApp() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);

    }
}
