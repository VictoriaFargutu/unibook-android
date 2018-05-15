package com.fargutuvictoria.unibook.ui.filter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.fargutuvictoria.commons.model.commons.ClassroomType;
import com.fargutuvictoria.commons.model.commons.WeekType;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.reservation.ReservationActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReservationFilterActivity extends AppCompatActivity implements ReservationFilterContract.View {
    private Spinner classroomTypeSpinner;
    private Spinner weekTypeSpinner;
    private Button resetButton;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_filter);
        Toolbar toolbar = findViewById(R.id.filters_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final EditText dateEditText = findViewById(R.id.date_picker);
        classroomTypeSpinner = findViewById(R.id.classroom_type_spinner);
        weekTypeSpinner = findViewById(R.id.week_type_spinner);
        resetButton = findViewById(R.id.filters_reset_button);
        applyButton = findViewById(R.id.filters_apply_button);

        showClassroomTypes();
        showWeekTypes();

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateEditText.setText(sdf.format(calendar.getTime()));
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ReservationFilterActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, ReservationActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showClassroomTypes() {
        ArrayAdapter<ClassroomType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ClassroomType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classroomTypeSpinner.setAdapter(adapter);
        classroomTypeSpinner.setSelection(0, true);

        classroomTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showWeekTypes() {
        ArrayAdapter<WeekType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, WeekType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekTypeSpinner.setAdapter(adapter);
        weekTypeSpinner.setSelection(0, true);

        weekTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
