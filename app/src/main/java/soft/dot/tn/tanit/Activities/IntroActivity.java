package soft.dot.tn.tanit.Activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import soft.dot.tn.tanit.Adapters.ViewPagerAdapter;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;
import soft.dot.tn.tanit.R;

/**
 * Created by Wafee on 03/02/2018.
 */

public class IntroActivity extends AppCompatActivity {

    private static final int LOGIN_FRAGMENT = 1;
    private static final int SIGNUP_FRAGMENT = 2;
    private static final int RESET_PASSWORD_FRAGMENT = 3;
    private static final int INTRO_FRAGMENT = 0;

    ImageView background;
    AnimationDrawable animationDrawable;
    Animation zoomin, zoomout;
    LinearLayout ll_login, ll_signup;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    public String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        changeFragment(INTRO_FRAGMENT);
        background = findViewById(R.id.background);

        //Background animation
        animationDrawable = (AnimationDrawable) background.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        //Background zoom
        zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        background.setAnimation(zoomin);
        zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        zoomin.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {


            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                background.startAnimation(zoomout);


            }
        });
        zoomout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                background.startAnimation(zoomin);

            }
        });

        UserSharedPref userSharedPref = new UserSharedPref(getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
        if (userSharedPref.isUserLogged()) {
            Intent intent = new Intent(this, DashBoardActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Background animation
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Background animation
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }


    private class Zoom implements Runnable {
        @Override
        public void run() {
            while (true) {
                background.startAnimation(zoomin);
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                background.startAnimation(zoomout);
            }
        }
    }

    private void changeFragment(int fragmentType) {

        switch (fragmentType) {
            case INTRO_FRAGMENT:
                viewPager.setCurrentItem(INTRO_FRAGMENT);
                break;
            case LOGIN_FRAGMENT:
                viewPager.setCurrentItem(LOGIN_FRAGMENT);
                break;
            case SIGNUP_FRAGMENT:
                viewPager.setCurrentItem(SIGNUP_FRAGMENT);
                break;
            case RESET_PASSWORD_FRAGMENT:
                viewPager.setCurrentItem(RESET_PASSWORD_FRAGMENT);
                break;

            default:
                viewPager.setCurrentItem(INTRO_FRAGMENT);
                break;
        }


    }

    public void signUpClick(View view) {
        changeFragment(SIGNUP_FRAGMENT);
    }

    public void signInClick(View view) {
        changeFragment(LOGIN_FRAGMENT);
    }

    public void resetPasswordClick(View view) {
        changeFragment(RESET_PASSWORD_FRAGMENT);
    }

    public void backClick(View view) {
        changeFragment(INTRO_FRAGMENT);
    }

    @Override
    public void onBackPressed() {

        if (viewPager.getCurrentItem() == INTRO_FRAGMENT)
            super.onBackPressed();
        else {
            changeFragment(INTRO_FRAGMENT);
        }
    }

    // Show dialog Fragment
    public void ShowDialogFragment(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(getSupportFragmentManager(), tag);
    }

}
