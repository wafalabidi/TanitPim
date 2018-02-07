package soft.dot.tn.tanit.Fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soft.dot.tn.tanit.Activities.IntroActivity;
import soft.dot.tn.tanit.Activities.ProvidersDataChecker;
import soft.dot.tn.tanit.DialogFragment.RealDatePickerDialogFragment;
import soft.dot.tn.tanit.Entitites.User;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;
import soft.dot.tn.tanit.R;
import soft.dot.tn.tanit.Services.UserDAO;

/**
 * Created by Wafee on 04/02/2018.
 */

public class SignUpFragment extends Fragment implements FacebookCallback<LoginResult>, View.OnClickListener, Callback<okhttp3.Response> {

    @BindView(R.id.login_facebook)
    LoginButton loginButtonFacebook;
    CallbackManager callbackManager;
    @BindView(R.id.clickable_birthday_layout)
    LinearLayout clickable_birthday_layout;
    @BindView(R.id.firstname_edittext)
    AppCompatEditText firstname_edittext;
    @BindView(R.id.username_edittext)
    AppCompatEditText username_edittext;
    @BindView(R.id.email_edittext)
    AppCompatEditText email_edittext;
    @BindView(R.id.birthday_edittext)
    AppCompatTextView birthday_edittext;
    @BindView(R.id.password_edittext)
    AppCompatEditText password;
    @BindView(R.id.signup_button)
    LinearLayout signup_button;
    User currentUser;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.signup_fragment, container, false);

        ButterKnife.bind(this, view);

        SetUpFacebook(); // Setup Facebook login
        clickable_birthday_layout.setOnClickListener(this);
        return view;
    }

    private void SetUpFacebook() {
        callbackManager = CallbackManager.Factory.create();

        loginButtonFacebook.setFragment(this);
        loginButtonFacebook.setReadPermissions("user_friends");
        loginButtonFacebook.setReadPermissions("public_profile");
        loginButtonFacebook.setReadPermissions("email");
        loginButtonFacebook.setReadPermissions("birthday");

        loginButtonFacebook.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        loginButtonFacebook.registerCallback(callbackManager, this);


    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.e("Facebook ", loginResult.getAccessToken().getUserId());
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.e("User from facebook ", object.toString());
                showDialog(object);
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,email,picture,birthday");

        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void showDialog(JSONObject jsonObject) {

        Bundle bundle = new Bundle();
        bundle.putInt(ProvidersDataChecker.ARGS0, ProvidersDataChecker.FACEBOOK_PROVIDER);
        bundle.putString(ProvidersDataChecker.ARGS1, jsonObject.toString());
        Intent intent = new Intent(getActivity(), ProvidersDataChecker.class);
        intent.putExtras(bundle);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {
        Log.e("Error ", error.getMessage());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.clickable_birthday_layout) {
            RealDatePickerDialogFragment realDatePickerDialogFragment = new RealDatePickerDialogFragment();
            ((IntroActivity) getActivity()).ShowDialogFragment(realDatePickerDialogFragment, "DatePicker");

        } else if (view.getId() == R.id.signup_button) {
            if (allDataFilled()) {
                currentUser = new User();
                currentUser.setFirstName(firstname_edittext.getText().toString());
                currentUser.setLastName(username_edittext.getText().toString());
                currentUser.setPassword(password.getText().toString());
                currentUser.setAge(((IntroActivity) getActivity()).date);
                currentUser.setEmail(email_edittext.getText().toString());
                currentUser.setId(System.currentTimeMillis());
                UserDAO userDAO = new UserDAO();
                userDAO.SignUpUser(currentUser, this);
            }
        }
    }

    private boolean allDataFilled() {
        if (TextUtils.isEmpty(firstname_edittext.getText())) {
            firstname_edittext.setBackgroundDrawable(getActivity().getDrawable(R.drawable.missing_data_edittext));
            return false;
        } else if (TextUtils.isEmpty(username_edittext.getText())) {
            username_edittext.setBackgroundDrawable(getActivity().getDrawable(R.drawable.missing_data_edittext));
            return false;
        } else if (TextUtils.isEmpty(password.getText())) {
            password.setBackgroundDrawable(getActivity().getDrawable(R.drawable.missing_data_edittext));
            return false;
        } else if (TextUtils.isEmpty(
                ((IntroActivity) getActivity()).date)) {
            Toast.makeText(getActivity(), "Please enter Birtdhay", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
        //Call Back from Successfull SignUP
    @Override
    public void onResponse(Call<okhttp3.Response> call, Response<okhttp3.Response> response) {
        Log.e("Response", response.message());
        UserSharedPref userSharedPref = new
                UserSharedPref(getActivity().getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
        userSharedPref.logIn(currentUser);
        //TODO  Add first login options before moving on

    }
    //CallBakc from Failed SignUp
    @Override
    public void onFailure(Call<okhttp3.Response> call, Throwable t) {
        Log.e("Response", t.getMessage());

    }
}
