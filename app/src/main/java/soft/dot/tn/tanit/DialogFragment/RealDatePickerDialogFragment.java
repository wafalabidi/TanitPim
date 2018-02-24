package soft.dot.tn.tanit.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

import soft.dot.tn.tanit.Activities.FirstCycleActivity;
import soft.dot.tn.tanit.Activities.IntroActivity;
import soft.dot.tn.tanit.LocalStorage.UserSharedPref;

/**
 * Created by sofien on 07/02/2018.
 */

public class RealDatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String DD;
        String MM = "" + month;
        if (month < 10) {
            MM = "0" + month;
        }
        if (day < 10) {
            DD = "0" + day;
        } else {
            DD = "" + day;
        }
        String date = DD + "-" + MM + "-" + year + " 00:00:00";
        Log.e("Date   :   " , date );

        if (getActivity() instanceof IntroActivity) {
            ((IntroActivity) getActivity()).date = " ";
            ((IntroActivity) getActivity()).date += date;
            TimePickerDialogFragment datePickerDialogFragment = new TimePickerDialogFragment();
            ((IntroActivity) getActivity()).ShowDialogFragment(datePickerDialogFragment, "TimePicker");
        } else if (getActivity() instanceof FirstCycleActivity) {
            UserSharedPref userSharedPref = new UserSharedPref(getActivity().getSharedPreferences(UserSharedPref.USER_FILE, Context.MODE_PRIVATE));
            userSharedPref.insertString(UserSharedPref.CYCL_START_DATE, date);
            ((FirstCycleActivity) getActivity()).WriteDate(date);
            dismiss();
        }

    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

}
