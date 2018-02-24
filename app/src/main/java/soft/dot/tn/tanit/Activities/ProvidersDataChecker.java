package soft.dot.tn.tanit.Activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soft.dot.tn.tanit.Entitites.User;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;
import soft.dot.tn.tanit.R;
import soft.dot.tn.tanit.Services.UserDAO;

/**
 * Created by sofien on 05/02/2018.
 */

public class ProvidersDataChecker extends AppCompatActivity implements View.OnClickListener, Callback<ResponseBody> {

    public static final int FACEBOOK_PROVIDER = 1;
    public static final int GOOGLE_PROVIDER = 2;
    public static final String ARGS0 = "provider";
    public static final String ARGS1 = "user";

    @BindView(R.id.providersBitmap)
    ImageView providersBitmap;
    @BindView(R.id.firstname_edittext)
    AppCompatEditText firstname_edittext;
    @BindView(R.id.username_edittext)
    AppCompatEditText username_edittext;
    @BindView(R.id.email_edittext)
    AppCompatEditText email_edittext;
    @BindView(R.id.birthday_edittext)
    AppCompatTextView birthday_edittext;

    @BindView(R.id.noChangeButton)
    Button noChangeButton;
    @BindView(R.id.password2_edittext)
    AppCompatEditText password2;
    @BindView(R.id.password_edittext)
    AppCompatEditText password;
    private int currentProvider = 0;
    private JSONObject graphJson;
    private User currentUser;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_data_from_providers);
        currentProvider = getIntent().getExtras().getInt(ARGS0);
        try {
            graphJson = new JSONObject(getIntent().getExtras().getString(ARGS1));
        } catch (JSONException e) {
            Log.e("Json Erreur", e.getMessage());
        }
        ButterKnife.bind(this);
        setProviderImage();
        SetUpUI();
        noChangeButton.setOnClickListener(this);
    }


    private void setProviderImage() {
        if (currentProvider != 0) {
            if (currentProvider == FACEBOOK_PROVIDER) {
                providersBitmap.setImageResource(R.drawable.facebook);
            } else {
                //TODO add GoogleImage
            }
        }
    }

    public void handleJson() {

        if (!TextUtils.isEmpty(graphJson.toString())) {
            try {
                currentUser = new User();
                currentUser.setFirstName(graphJson.getString("first_name"));
                currentUser.setLastName(graphJson.getString("last_name"));
                // currentUser.setId(graphJson.getInt("id"));
                currentUser.setAge(graphJson.getString("birthday"));
                currentUser.setImager_url(graphJson.getJSONObject("picture").getString("url"));
                currentUser.setEmail(graphJson.getString("email"));
                // currentUser.setImager_url(graphJson.getString("profile_pic"));
                //TODO recheck facebook image
                UserDAO userDAO = new UserDAO();
                userDAO.SignUpUser(currentUser, this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.noChangeButton) {
            if (password.getText().toString().equals(password2.getText().toString())) {
                if(TextUtils.isEmpty(currentUser.getEmail())){
                    currentUser.setEmail(email_edittext.getText().toString());
                }
                currentUser.setPassword(password.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE);
                UserSharedPref userSharedPref = new UserSharedPref(sharedPreferences);
                userSharedPref.logIn(currentUser);
                Intent intent = new Intent(this, FirstCycleActivity.class);
                Log.e("UserSubscribed ", currentUser.toString());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                Toast.makeText(this, "Please make sure you entred the same password twice", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void SetUpUI() {
        handleJson();
        email_edittext.setText(currentUser.getEmail());
        firstname_edittext.setText(currentUser.getFirstName());
        username_edittext.setText(currentUser.getLastName());
        birthday_edittext.setText(currentUser.getAge());

    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.e("Signup Facebook " , "all good ") ;
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.e("Signup Facebook " , t.getMessage()) ;

    }
}
