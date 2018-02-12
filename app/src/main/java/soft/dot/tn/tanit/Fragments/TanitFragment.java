package soft.dot.tn.tanit.Fragments;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.dot.tn.tanit.Activities.DashBoardActivity;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;
import soft.dot.tn.tanit.R;

/**
 * Created by sofien on 03/02/2018.
 */

public class TanitFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.WelomeTextView)
    TextView WelomeTextView;
    @BindView(R.id.TodayDate)
    TextView TodayDate;
    @BindView(R.id.AnalyseButton)
    ImageButton AnalyseButton;
    @BindView(R.id.AnalyseText)
    TextView AnalyseText;
    @BindView(R.id.profilImageButton)
    ImageButton profilImageButton;
    @BindView(R.id.profilText)
    TextView profilText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tanit, container, false);
        ButterKnife.bind(this, view);
        SetUpUI();
        return view;
    }

    private void SetUpUI() {
        UserSharedPref userSharedPref = new UserSharedPref(getActivity().getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
        String welcomeText = "Welcome Back , " + userSharedPref.getString(UserSharedPref.USER_FIRST_NAME) + " " + userSharedPref.getString(UserSharedPref.USER_LAST_NAME);
        WelomeTextView.setText(welcomeText);
        Date date = new Date(System.currentTimeMillis());

        DateFormat outputFormat = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);

        TodayDate.setText(outputFormat.format(date));
        AnalyseButton.setOnClickListener(this);
        AnalyseText.setOnClickListener(this);
        profilImageButton.setOnClickListener(this);
        profilText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.AnalyseButton:
                ((DashBoardActivity) getActivity()).getViewPager().setCurrentItem(1);
                break;
            case R.id.AnalyseText:
                ((DashBoardActivity) getActivity()).getViewPager().setCurrentItem(1);
                break;
            case R.id.profilImageButton:
                ((DashBoardActivity) getActivity()).getViewPager().setCurrentItem(2);
                break;
            case R.id.profilText:
                ((DashBoardActivity) getActivity()).getViewPager().setCurrentItem(2);
                break;

        }
    }
}
