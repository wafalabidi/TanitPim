package soft.dot.tn.tanit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.dot.tn.tanit.Entitites.TemperatureData;
import soft.dot.tn.tanit.R;

public class FertilityActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.lineChart)
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertility);
        ButterKnife.bind(this);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SetUpChart();
    }

    private void SetUpChart() {
        ArrayList<TemperatureData> temps = new ArrayList<>();
        temps.add(new TemperatureData(new Date(2017, 2, 5), 37.5f));
        temps.add(new TemperatureData(new Date(2017, 2, 6), 36.5f));
        temps.add(new TemperatureData(new Date(2017, 2, 7), 36.4f));
        temps.add(new TemperatureData(new Date(2017, 2, 8), 36.2f));
        temps.add(new TemperatureData(new Date(2017, 2, 9), 36.8f));
        temps.add(new TemperatureData(new Date(2017, 2, 10), 36.1f));
        temps.add(new TemperatureData(new Date(2017, 2, 11), 36.9f));
        temps.add(new TemperatureData(new Date(2017, 2, 12), 36.2f));
        temps.add(new TemperatureData(new Date(2017, 2, 13), 36.0f));
        temps.add(new TemperatureData(new Date(2017, 2, 14), 36.6f));

        List<Entry> entries = new ArrayList<>();
        for (TemperatureData temp : temps) {
            entries.add(new Entry((float) temp.getDate().getDay(), temp.getValue()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Temperature");
        dataSet.setValueTextColor(R.color.colorAccent);
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
        lineChart.notifyDataSetChanged();
    }

}
