package soft.dot.tn.tanit.Fragments;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.dot.tn.tanit.Activities.FertilityActivity;
import soft.dot.tn.tanit.Activities.SomeilActivity;
import soft.dot.tn.tanit.R;

/**
 * Created by sofien on 29/01/2018.
 */

public class AnalyseFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.LlConnectBracelet)
    LinearLayout LlConnectBracelet;
    @BindView(R.id.LlShowRapport)
    LinearLayout LlShowRapport;
    @BindView(R.id.someil)
    LinearLayout LlSomeil;
    @BindView(R.id.cvSomeil)
    CardView cvSomeil;
    @BindView(R.id.pregnancyTiming)
    LinearLayout pregnancyTiming;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.analyse_fragment, container, false);
        ButterKnife.bind(this, view);
        IntiWidgets();
        return view;
    }

    private void IntiWidgets() {
        LlConnectBracelet.setOnClickListener(this);
        LlConnectBracelet.setOnClickListener(this);
        LlSomeil.setOnClickListener(this);
        pregnancyTiming.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.LlShowRapport) {
            view.setBackground(getActivity().getDrawable(R.drawable.transparent_calque));

        } else if (view.getId() == R.id.LlConnectBracelet) {
            ShowConnectionDialog();
            view.setBackground(getActivity().getDrawable(R.drawable.transparent_calque));
        } else if (view.getId() == R.id.someil) {
            Intent intent = new Intent(getActivity(), SomeilActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else if (view.getId() == R.id.pregnancyTiming) {
            Intent intent = new Intent(getActivity(), FertilityActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        }
        else if (view.getId() == R.id.poid){
        }

    }

    private void ShowConnectionDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.connecting_bluetooth);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }
}
