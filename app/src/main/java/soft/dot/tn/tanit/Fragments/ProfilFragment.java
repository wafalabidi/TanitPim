package soft.dot.tn.tanit.Fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.danlew.android.joda.DateUtils;
import net.danlew.android.joda.JodaTimeAndroid;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soft.dot.tn.tanit.Activities.FirstCycleActivity;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;
import soft.dot.tn.tanit.R;
import soft.dot.tn.tanit.Services.CycleDAO;

/**
 * Created by sofien on 04/02/2018.
 */

public class ProfilFragment extends Fragment implements View.OnClickListener, Callback<ResponseBody> {

    @BindView(R.id.imageButtonCycleEnd)
    ImageButton imageButtonCycleEnd;
    @BindView(R.id.imageButtonCycleStart)
    ImageButton imageButtonCycleStart;
    @BindView(R.id.cycleStartLayout)
    LinearLayout cycleStartLayout;
    @BindView(R.id.cycleEndLayout)
    LinearLayout cycleEndLayout;
    @BindView(R.id.cycleParams)
    Button cycleParams;
    @BindView(R.id.cycleStatus)
    TextView cycleStatus;
    CycleDAO cycleDAO;
    UserSharedPref userSharedPref;
    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profil_fragment, container, false);
        ButterKnife.bind(this, view);
        instatiateStorage();
        setClickListenr();
        setUpUI();
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonCycleEnd:
                userSharedPref.insertBollean(UserSharedPref.CYCL_IS_IN_CYCLE, false);
                cycleDAO.EndCycle(userSharedPref.getInt(UserSharedPref.USER_ID), this);
                long startDate = userSharedPref.getLong(UserSharedPref.CYCL_START_DATE);
                if (startDate != -1) {
                    int duration = calculateCycleDuration();
                    Log.e("Cycle duration : ", "Cycle Duration Days: " + duration);
                    Snackbar.make(view, "We hope it was cool , madame  ", Snackbar.LENGTH_SHORT).show();
                    setUpUI();
                }
                break;
            case R.id.imageButtonCycleStart:
                userSharedPref.insertBollean(UserSharedPref.CYCL_IS_IN_CYCLE, true);
                cycleDAO.StartCycle(userSharedPref.getInt(UserSharedPref.USER_ID), this);
                userSharedPref.inserLong(UserSharedPref.CYCL_START_DATE, System.currentTimeMillis());
                Snackbar.make(view, "Good luck , madame  ", Snackbar.LENGTH_SHORT).show();
                setUpUI();
                break;
            case R.id.cycleParams:
                Intent intent = new Intent(getActivity(), FirstCycleActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            default:
                break;
        }
    }

    // Response for end and start cycles service
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }

    // Instantiate DAO and shared pref
    private void instatiateStorage() {
        cycleDAO = new CycleDAO();
        userSharedPref = new UserSharedPref(getActivity().getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
    }

    //Set up the UI for everyUser
    private void setUpUI() {
        if (userSharedPref.getBoolean(UserSharedPref.CYCL_IS_IN_CYCLE)) {
            cycleEndLayout.setVisibility(View.VISIBLE);
            cycleStartLayout.setVisibility(View.GONE);
            int cycleDuration = calculateCycleDuration();
            if (cycleDuration > 0) {
                String cycleMessage = cycleDuration + " days since benediction started ";
                cycleStatus.setText(cycleMessage);
            } else if (cycleDuration == 0) {
                cycleStatus.setText("Benedction started today");
            }
        } else {

            cycleStartLayout.setVisibility(View.VISIBLE);
            cycleEndLayout.setVisibility(View.GONE);

        }
    }

    private void setClickListenr() {
        cycleParams.setOnClickListener(this);
        imageButtonCycleEnd.setOnClickListener(this);
        imageButtonCycleStart.setOnClickListener(this);
    }

    private int calculateCycleDuration() {
        java.util.Date sDate = new Date(userSharedPref.getLong(UserSharedPref.CYCL_START_DATE));
        java.util.Date eDate = new Date(System.currentTimeMillis());
        GregorianCalendar startDate = new GregorianCalendar(sDate.getYear(), sDate.getMonth(), sDate.getDay());
        GregorianCalendar endDate = new GregorianCalendar(eDate.getYear(), eDate.getMonth(), eDate.getDay());
        return endDate.get(Calendar.DAY_OF_MONTH) - startDate.get(Calendar.DAY_OF_MONTH);
    }
}
