package soft.dot.tn.tanit.Fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soft.dot.tn.tanit.Activities.DashBoardActivity;
import soft.dot.tn.tanit.Activities.FirstCycleActivity;
import soft.dot.tn.tanit.Activities.IntroActivity;
import soft.dot.tn.tanit.Entitites.User;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;
import soft.dot.tn.tanit.R;
import soft.dot.tn.tanit.Services.UserDAO;

/**
 * Created by Wafee on 04/02/2018.
 */

public class LoginFragment extends Fragment implements View.OnClickListener, Callback<User> {
    @BindView(R.id.login_button)
    LinearLayout loginButton;
    View view;
    @BindView(R.id.email_edittext)
    AppCompatEditText email_edittext;
    @BindView(R.id.password_edittext)
    AppCompatEditText password_edittext;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        loginButton.setOnClickListener(this);
        checkCurrentUser();
        return view;
    }


    void checkCurrentUser() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == (R.id.login_button)) {
            User user = new User();
            user.setEmail(email_edittext.getText().toString());
            user.setPassword(password_edittext.getText().toString());
            UserDAO userDAO = new UserDAO();
            userDAO.logIn(user, this);
        }
    }


    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.body() != null) {
            UserSharedPref userSharedPref = new UserSharedPref(getActivity().getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
            userSharedPref.logIn(response.body());
            Intent intent = new Intent(getActivity(), DashBoardActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            Snackbar.make(view, "Wrong e-mail or Password ", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Log.e("Error", t.getMessage());
        Snackbar.make(view, "Failed to acces remote server.", Snackbar.LENGTH_SHORT).show();


    }
}
