package soft.dot.tn.tanit.Fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import soft.dot.tn.tanit.Activities.ProvidersDataChecker;
import soft.dot.tn.tanit.R;

/**
 * Created by Wafee on 04/02/2018.
 */

public class SignUpFragment extends Fragment implements FacebookCallback<LoginResult> {

    @BindView(R.id.login_facebook)
    LoginButton loginButtonFacebook;
    CallbackManager callbackManager;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.signup_fragment, container, false);

        ButterKnife.bind(this, view);
        callbackManager = CallbackManager.Factory.create();

        loginButtonFacebook.setFragment(this);
        loginButtonFacebook.setReadPermissions("user_friends");
        loginButtonFacebook.setReadPermissions("public_profile");
        loginButtonFacebook.setReadPermissions("email");
        loginButtonFacebook.setReadPermissions("birthday");

        loginButtonFacebook.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        loginButtonFacebook.registerCallback(callbackManager, this);

        return view;
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
}
