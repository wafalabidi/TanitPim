package soft.dot.tn.tanit.Activities;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import soft.dot.tn.tanit.Fragments.AnalyseFragment;
import soft.dot.tn.tanit.R;

/**
 * Created by sofien on 28/01/2018.
 */

public abstract class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;


    protected Toolbar SetUpToolbar() {
        if (this.toolbar == null) {
            toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }
        return toolbar;
    }

    protected BottomNavigationView SetUpBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        return bottomNavigationView;
    }


}
