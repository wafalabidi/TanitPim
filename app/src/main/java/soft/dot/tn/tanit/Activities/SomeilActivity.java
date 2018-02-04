package soft.dot.tn.tanit.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.dot.tn.tanit.Adapters.SomeilAdapter;
import soft.dot.tn.tanit.Entitites.Someil;
import soft.dot.tn.tanit.R;


public class SomeilActivity extends AppCompatActivity {
    @BindView(R.id.graphSomeil)
    BarChart barChart;
    @BindView(R.id.rvRegistreSomeil)
    RecyclerView rvRegistreSomeil;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.rootView)
    LinearLayout rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.someil_activity);
        ButterKnife.bind(this);
        setUpView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new ChangeTransform());


    }

    private void setUpView() {
        ArrayList<Someil> arrayList = new ArrayList<>();
        arrayList.add(new Someil("14 jan 2018", 5, 20));
        arrayList.add(new Someil("15 jan 2018", 6, 20));
        arrayList.add(new Someil("16 jan 2018", 5, 20));
        arrayList.add(new Someil("17 jan 2018", 7, 20));
        arrayList.add(new Someil("18 jan 2018", 5, 20));
        arrayList.add(new Someil("19 jan 2018", 7, 20));
        arrayList.add(new Someil("20 jan 2018", 6, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        arrayList.add(new Someil("21 jan 2018", 5, 20));
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            entries.add(new BarEntry(i, arrayList.get(i).getHeure()));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "Sleep");
        barDataSet.setColor(Color.MAGENTA);
        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barChart.invalidate();

        SomeilAdapter adapter = new SomeilAdapter(this, arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        barChart.setGridBackgroundColor(Color.TRANSPARENT);
        barChart.animate();
        barChart.setDrawBarShadow(true);
        barChart.setBorderColor(R.color.colorPrimary);
        barChart.animateXY(2000, 2000);
        rvRegistreSomeil.setLayoutManager(linearLayoutManager);
        rvRegistreSomeil.setAdapter(adapter);
    }
}


