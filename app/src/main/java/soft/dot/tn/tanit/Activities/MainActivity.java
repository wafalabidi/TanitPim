package soft.dot.tn.tanit.Activities;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
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
    Toolbar toolbarLayout;
    BottomNavigationView bottomNavigationView;


    protected Toolbar SetUpToolbar() {
        if (this.toolbarLayout == null) {
            toolbarLayout = findViewById(R.id.toolbar);
           if (toolbarLayout != null) {
                setSupportActionBar(toolbarLayout);
            }
        }
        return toolbarLayout;
    }

    protected BottomNavigationView SetUpBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        return bottomNavigationView;
    }


}
