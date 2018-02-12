package soft.dot.tn.tanit.Activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import soft.dot.tn.tanit.DialogFragment.RealDatePickerDialogFragment;
import soft.dot.tn.tanit.Entitites.Cycle;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;
import soft.dot.tn.tanit.R;
import soft.dot.tn.tanit.Services.CycleDAO;

/**
 * Created by sofien on 08/02/2018.
 */

public class FirstCycleActivity extends AppCompatActivity implements View.OnClickListener, Callback<Response> {
    @BindView(R.id.WelomeTextView)
    TextView WelomeTextView;
    @BindView(R.id.cycleDuration)
    EditText cycleDuration;
    @BindView(R.id.cycleStartingDate)
    Button cycleStartingDate;
    @BindView(R.id.periodeDuration)
    EditText periodeDuration;
    @BindView(R.id.Submit)
    Button Submit;
    @BindView(R.id.firstLoginToolBar)
    Toolbar toolbar;
    RealDatePickerDialogFragment realDatePickerDialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_login);
        ButterKnife.bind(this);
        getWindow().setEnterTransition(new Fade());
        getWindow().setAllowEnterTransitionOverlap(true);
        toolbar.setTitle("Tanit");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        SetUpUI();

    }

    private void SetUpUI() {
        UserSharedPref userSharedPref = new UserSharedPref(getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
        String hello = "Welcom, " + userSharedPref.getString(UserSharedPref.USER_LAST_NAME) + " " + userSharedPref.getString(UserSharedPref.USER_FIRST_NAME);
        WelomeTextView.setText(hello);

        cycleStartingDate.setOnClickListener(this);
        Submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cycleStartingDate:
                realDatePickerDialogFragment = new RealDatePickerDialogFragment();
                realDatePickerDialogFragment.show(getSupportFragmentManager().beginTransaction(), "Starting date");
                break;
            case R.id.Submit:
                validateData();
                break;
        }
    }

    private void validateData() {
        if ((!TextUtils.isEmpty(periodeDuration.getText().toString())) && (!TextUtils.isEmpty(cycleDuration.getText().toString()))) {
            int priodeLenght = Integer.valueOf(periodeDuration.getText().toString());
            int length = Integer.valueOf(cycleDuration.getText().toString());

            UserSharedPref userSharedPref = new UserSharedPref(getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
            userSharedPref.insertInt(UserSharedPref.CYCL_LENGTH, length);
            userSharedPref.insertInt(UserSharedPref.CYCL_START_DATE, priodeLenght);
            Cycle cycle = new Cycle();
            cycle.setStartDate(userSharedPref.getString(UserSharedPref.CYCL_START_DATE));
            cycle.setLenght(length);
            cycle.setFolicularLength(priodeLenght);
            CycleDAO cycleDAO = new CycleDAO();
            cycleDAO.InsertFirstCycle(userSharedPref.getLong(UserSharedPref.USER_ID), cycle, this);
        } else {
            periodeDuration.setBackground(getResources().getDrawable(R.drawable.missing_data_edittext));
            cycleDuration.setBackground(getResources().getDrawable(R.drawable.missing_data_edittext));

        }

    }

    public void WriteDate(String date) {
        cycleStartingDate.setText(date);
    }


    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        Log.e("Add First Cycle :", t.getMessage());
    }
}
