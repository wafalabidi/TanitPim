package soft.dot.tn.tanit.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.dot.tn.tanit.Fragments.AnalyseFragment;
import soft.dot.tn.tanit.Fragments.ProfilFragment;
import soft.dot.tn.tanit.Fragments.TanitFragment;
import soft.dot.tn.tanit.R;

/**
 * Created by sofien on 28/01/2018.
 */

public class DashBoardActivity extends MainActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    AnalyseFragment analyseFragment;
    TanitFragment tanitFragment;
    ProfilFragment profilFragment  ;
    @BindView(R.id.container)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        toolbar = super.SetUpToolbar();
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Tanit");


        super.SetUpBottomNavigation();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        SetUpViewPager();


        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS) ;
        getWindow().setExitTransition(new Explode());
        getWindow().setEnterTransition(new Explode());
        getWindow().setAllowEnterTransitionOverlap(true);

    }

    private void SetUpViewPager() {
        analyseFragment = new AnalyseFragment();
        tanitFragment = new TanitFragment();
        profilFragment = new ProfilFragment() ;
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(tanitFragment, "Tanit");
        adapter.addFragment(analyseFragment, "Analyse");
        adapter.addFragment(profilFragment, "Profile");
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ScaleInOutTransformer());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Analyse) {
            viewPager.setCurrentItem(1);
        } else if (item.getItemId() == R.id.Tanit) {

            viewPager.setCurrentItem(0);
        } else if (item.getItemId() == R.id.Moi) {
            viewPager.setCurrentItem(2);
        }
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
