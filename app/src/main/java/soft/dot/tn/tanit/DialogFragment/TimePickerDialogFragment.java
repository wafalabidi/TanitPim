package soft.dot.tn.tanit.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import soft.dot.tn.tanit.Activities.IntroActivity;

/**
 * Created by sofien on 07/02/2018.
 */

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        int random = ThreadLocalRandom.current().nextInt(1, 60);
        ((IntroActivity) getActivity()).date += " "+i + ":" + i1 + ":" + random;
        Log.e("Date", ((IntroActivity) getActivity()).date);
        dismiss();
    }
}
