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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.commons.model.commons.ClassroomType;
import com.fargutuvictoria.commons.model.commons.Hour;
import com.fargutuvictoria.commons.model.commons.Specialization;
import com.fargutuvictoria.commons.model.commons.Subgroup;
import com.fargutuvictoria.commons.model.commons.WeekType;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.reservation.ReservationActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ReservationFilterActivity extends AppCompatActivity implements ReservationFilterContract.View {
    private Spinner classroomTypeSpinner;
    private Spinner weekTypeSpinner;
    private Spinner hourSpinner;
    private Spinner yearSpinner;
    private Spinner specializationSpinner;
    private Spinner studentsGroupSpinner;
    private Spinner studentsSubgroupSpinner;
    private Button resetButton;
    private Button applyButton;

    private CheckBox classroomTypeCheckBox;
    private CheckBox weekTypeCheckBox;
    private CheckBox hourCheckBox;
    private CheckBox yearCheckBox;
    private CheckBox specializationCheckBox;
    private CheckBox studentsGroupCheckBox;
    private CheckBox studentsSubgroupCheckBox;

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
        hourSpinner = findViewById(R.id.hour_spinner);
        yearSpinner = findViewById(R.id.year_spinner);
        specializationSpinner = findViewById(R.id.specialization_spinner);
        studentsGroupSpinner = findViewById(R.id.students_group_spinner);
        studentsSubgroupSpinner = findViewById(R.id.students_subgroup_spinner);
        resetButton = findViewById(R.id.filters_reset_button);
        applyButton = findViewById(R.id.filters_apply_button);

        initializeCheckBoxes();

        showClassroomTypes();
        showWeekTypes();
        showHours();
        showYear();
        showSpecializations();
        showStudentsGroups();
        showSubgroups();

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

        //checkboxes

        classroomTypeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        weekTypeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        hourCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        yearCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        specializationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        studentsGroupCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        studentsSubgroupCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        //buttons
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classroomTypeCheckBox.setChecked(false);
                weekTypeCheckBox.setChecked(false);
                hourCheckBox.setChecked(false);
                yearCheckBox.setChecked(false);
                specializationCheckBox.setChecked(false);
                studentsGroupCheckBox.setChecked(false);
                studentsSubgroupCheckBox.setChecked(false);
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            ClassroomType classroomType = null;
            WeekType weekType = null;
            Hour hour = null;
            Integer year = null;
            Specialization specialization = null;
            StudentsGroup studentsGroup = null;
            Subgroup studentsSubgroup = null;

            @Override
            public void onClick(View view) {
                if (classroomTypeCheckBox.isChecked()) {
                    classroomType = (ClassroomType) classroomTypeSpinner.getSelectedItem();
                }
                if (weekTypeCheckBox.isChecked()) {
                    weekType = (WeekType) weekTypeSpinner.getSelectedItem();
                }
                if (hourCheckBox.isChecked()) {
                    hour = (Hour) hourSpinner.getSelectedItem();
                }
                if (yearCheckBox.isChecked()) {
                    year = (Integer) yearSpinner.getSelectedItem();
                }
                if (specializationCheckBox.isChecked()) {
                    specialization = (Specialization) specializationSpinner.getSelectedItem();
                }
                if (studentsGroupCheckBox.isChecked()) {
                    studentsGroup = (StudentsGroup) studentsGroupSpinner.getSelectedItem();
                }
                if (studentsSubgroupCheckBox.isChecked()) {
                    studentsSubgroup = (Subgroup) studentsSubgroupSpinner.getSelectedItem();
                }
                //TODO make request
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

    @Override
    public void showHours() {
        List<String> hours = new ArrayList<>();
        hours.add(Hour.EIGHT_TO_TEN);
        hours.add(Hour.TEN_TO_TWELVE);
        hours.add(Hour.TWELVE_TO_FOURTEEN);
        hours.add(Hour.FOURTEEN_TO_SIXTEEN);
        hours.add(Hour.SIXTEEN_TO_EIGHTEEN);
        hours.add(Hour.EIGHTEEN_TO_TWENTY);
        hours.add(Hour.TWENTY_TO_TWENTYTWO);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(adapter);
        hourSpinner.setSelection(0, true);

        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showYear() {
        List<Integer> years = new ArrayList<>();
        years.add(1);
        years.add(2);
        years.add(3);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter);
        yearSpinner.setSelection(0, true);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO change other spinners items
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void showSpecializations() {
        ArrayAdapter<Specialization> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Specialization.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specializationSpinner.setAdapter(adapter);
        specializationSpinner.setSelection(0, true);

        specializationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO change other spinners items
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showStudentsGroups() {
        //TODO
        ArrayAdapter<WeekType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, WeekType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentsGroupSpinner.setAdapter(adapter);
        studentsGroupSpinner.setSelection(0, true);

        studentsGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showSubgroups() {
        ArrayAdapter<Subgroup> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Subgroup.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentsSubgroupSpinner.setAdapter(adapter);
        studentsSubgroupSpinner.setSelection(0, true);

        studentsSubgroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initializeCheckBoxes() {
        classroomTypeCheckBox = findViewById(R.id.classroom_type_checkbox);
        weekTypeCheckBox = findViewById(R.id.week_type_checkbox);
        hourCheckBox = findViewById(R.id.hour_checkbox);
        yearCheckBox = findViewById(R.id.year_checkbox);
        specializationCheckBox = findViewById(R.id.specialization_checkbox);
        studentsGroupCheckBox = findViewById(R.id.students_group_checkbox);
        studentsSubgroupCheckBox = findViewById(R.id.students_subgroup_checkbox);
    }
}
