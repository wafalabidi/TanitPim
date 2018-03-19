package soft.dot.tn.tanit.Activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soft.dot.tn.tanit.Adapters.ViewPagerAdapter;
import soft.dot.tn.tanit.Entitites.User;
import soft.dot.tn.tanit.Fragments.SignUpFragment;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;
import soft.dot.tn.tanit.R;
import soft.dot.tn.tanit.Services.UserDAO;

/**
 * Created by Wafee on 03/02/2018.
 */

public class IntroActivity extends AppCompatActivity implements DialogInterface , Callback<User> {

    public static final int LOGIN_FRAGMENT = 1;
    public static final int SIGNUP_FRAGMENT = 2;
    public static final int RESET_PASSWORD_FRAGMENT = 3;
    public static final int INTRO_FRAGMENT = 0;

    ImageView background;
    AnimationDrawable animationDrawable;
    Animation zoomin, zoomout;
    LinearLayout ll_login, ll_signup;
    public ViewPager viewPager;
    public ViewPagerAdapter viewPagerAdapter;
    public String date;
    public User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        UserSharedPref.logOut(getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
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
        //TODO dynamise from backend
        boolean b = false;
        if (b) {
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

    @Override
    public void cancel() {

    }

    @Override
    public void dismiss() {
        Log.e("tag", "tagg");
        //((SignUpFragment)viewPagerAdapter.getItem(SIGNUP_FRAGMENT)).setDate();
    }

    public void setUpLogin(User currentUser) {
        this.currentUser = currentUser;
        Log.e("User in Activity" , this.currentUser.toString());

        UserDAO userDAO = new UserDAO();
        userDAO.logIn(currentUser, this);
        changeFragment(LOGIN_FRAGMENT);
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

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.body() != null) {
            UserSharedPref userSharedPref = new UserSharedPref(this.getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
            userSharedPref.logIn(response.body());
            Intent intent = new Intent(this, FirstCycleActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            Snackbar.make(background, "Wrong e-mail or Password ", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Log.e("Error", t.getMessage());
        Snackbar.make(background, "Failed to acces remote server.", Snackbar.LENGTH_SHORT).show();


    }

}
