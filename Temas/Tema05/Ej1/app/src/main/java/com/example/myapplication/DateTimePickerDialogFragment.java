package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.MainActivity;

import java.util.Calendar;

public class DateTimePickerDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new DatePickerDialog(getActivity(), (view, year1, month1, dayOfMonth) -> {
            new TimePickerDialog(getActivity(), (view1, hourOfDay, minute1) -> {
                // Aquí puedes enviar la fecha y hora seleccionadas a la actividad
                ((MainActivity) getActivity()).onDateTimeSelected(year1, month1, dayOfMonth, hourOfDay, minute1);
            }, hour, minute, true).show();
        }, year, month, day);
    }
}