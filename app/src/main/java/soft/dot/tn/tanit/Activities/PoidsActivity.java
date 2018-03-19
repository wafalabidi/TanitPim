package soft.dot.tn.tanit.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.dot.tn.tanit.Entitites.WeightData;
import soft.dot.tn.tanit.R;

/**
 * Created by Wafee on 04/02/2018.
 */

public class PoidsActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    private EditText height;
    private EditText weight;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poid_activity);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
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

    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }



    private void SetUpChart() {
        ArrayList<WeightData> poids = new ArrayList<>();
       poids.add(new WeightData(new Date(2017, 2, 5).toString(), 60));
       poids.add(new WeightData(new Date(2017, 2, 6).toString(), 60.5f));
       poids.add(new WeightData(new Date(2017, 2, 7).toString(), 53.4f));
       poids.add(new WeightData(new Date(2017, 2, 8).toString(), 68.2f));
       poids.add(new WeightData(new Date(2017, 2, 9).toString(), 70.8f));
       poids.add(new WeightData(new Date(2017, 2, 10).toString(), 68.1f));
       poids.add(new WeightData(new Date(2017, 2, 11).toString(), 72.9f));
       poids.add(new WeightData(new Date(2017, 2, 12).toString(), 72.2f));
       poids.add(new WeightData(new Date(2017, 2, 13).toString(), 100.0f));
       poids.add(new WeightData(new Date(2017, 2, 14).toString(), 70.6f));

        List<Entry> entries = new ArrayList<>();
        for (WeightData p : poids) {
           // entries.add(new Entry((float) p.getDate().getDay(), p.getValue()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "WeightData");
        dataSet.setValueTextColor(R.color.colorAccent);
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
        lineChart.notifyDataSetChanged();
    }
}
