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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.Filter;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.commons.model.commons.ClassroomType;
import com.fargutuvictoria.commons.model.commons.Day;
import com.fargutuvictoria.commons.model.commons.Hour;
import com.fargutuvictoria.commons.model.commons.Specialization;
import com.fargutuvictoria.commons.model.commons.Subgroup;
import com.fargutuvictoria.commons.model.commons.WeekType;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.commons.ToFilterFrom;
import com.fargutuvictoria.unibook.ui.reservation.ReservationActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReservationFilterActivity extends AppCompatActivity implements ReservationFilterContract.View {
    private ReservationFilterPresenter reservationFilterPresenter;

    private RelativeLayout classroomTypeLayout;

    private EditText dateEditText;

    private Spinner classroomTypeSpinner;
    private Spinner daySpinner;
    private Spinner weekTypeSpinner;
    private Spinner hourSpinner;
    private Spinner yearSpinner;
    private Spinner specializationSpinner;
    private Spinner studentsGroupSpinner;
    private Spinner studentsSubgroupSpinner;
    private Button resetButton;
    private Button applyButton;

    private CheckBox classroomTypeCheckBox;
    private CheckBox dayCheckBox;
    private CheckBox weekTypeCheckBox;
    private CheckBox dateCheckBox;
    private CheckBox hourCheckBox;
    private CheckBox yearCheckBox;
    private CheckBox specializationCheckBox;
    private CheckBox studentsGroupCheckBox;
    private CheckBox studentsSubgroupCheckBox;

    private Calendar calendar;

    private List<StudentsGroup> studentsGroups;
    private Classroom classroom;
    private String fromFilter;
    private Filter filter;
    private Filter extraFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_filter);

        Toolbar toolbar = findViewById(R.id.filters_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        classroomTypeLayout = findViewById(R.id.classroom_type);

        Intent intent = getIntent();
        fromFilter = (String) intent.getSerializableExtra("toFilterFrom");
        if (fromFilter != null && fromFilter.equals(ToFilterFrom.FROM_CLASSROOM)) {
            classroomTypeLayout.setVisibility(View.GONE);
        }
        classroom = (Classroom) intent.getSerializableExtra("classroom");
        extraFilter = (Filter) intent.getSerializableExtra("filter");

        dateEditText = findViewById(R.id.date_picker);
        classroomTypeSpinner = findViewById(R.id.classroom_type_spinner);
        weekTypeSpinner = findViewById(R.id.week_type_spinner);
        daySpinner = findViewById(R.id.day_spinner);
        hourSpinner = findViewById(R.id.hour_spinner);
        yearSpinner = findViewById(R.id.year_spinner);
        specializationSpinner = findViewById(R.id.specialization_spinner);
        studentsGroupSpinner = findViewById(R.id.students_group_spinner);
        studentsSubgroupSpinner = findViewById(R.id.students_subgroup_spinner);
        resetButton = findViewById(R.id.filters_reset_button);
        applyButton = findViewById(R.id.filters_apply_button);

        initializeCheckBoxes();

        if (extraFilter != null) {

            if (extraFilter.getClassroomType() != null) {
                classroomTypeCheckBox.setChecked(true);
            }
            if (extraFilter.getWeekType() != null) {
                weekTypeCheckBox.setChecked(true);
            }
            if (extraFilter.getDay() != null) {
                dayCheckBox.setChecked(true);
            }
            if (extraFilter.getDate() != null) {
                dateCheckBox.setChecked(true);
            }
            if (extraFilter.getHour() != null) {
                hourCheckBox.setChecked(true);
            }
            if (extraFilter.getYear() != null) {
                yearCheckBox.setChecked(true);
            }
            if (extraFilter.getSpecialization() != null) {
                specializationCheckBox.setChecked(true);
            }
            if (extraFilter.getStudentsGroup() != null) {
                studentsGroupCheckBox.setChecked(true);
            }
            if (extraFilter.getSubgroup() != null) {
                studentsSubgroupCheckBox.setChecked(true);
            }
        }

        reservationFilterPresenter = new ReservationFilterPresenter(this);

        showClassroomTypes();
        showDays();
        showWeekTypes();
        showHours();
        showYear();
        showSubgroups();

        calendar = Calendar.getInstance();

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
                new DatePickerDialog(ReservationFilterActivity.this, R.style.DialogTheme, date, calendar
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

        dayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        weekTypeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        dateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                dayCheckBox.setChecked(false);
                weekTypeCheckBox.setChecked(false);
                dateCheckBox.setChecked(false);
                hourCheckBox.setChecked(false);
                yearCheckBox.setChecked(false);
                specializationCheckBox.setChecked(false);
                studentsGroupCheckBox.setChecked(false);
                studentsSubgroupCheckBox.setChecked(false);
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            ClassroomType classroomType = null;
            Day day = null;
            WeekType weekType = null;
            Long date = null;
            String hour = null;
            Integer year = null;
            Specialization specialization = null;
            StudentsGroup studentsGroup = null;
            Subgroup studentsSubgroup = null;

            @Override
            public void onClick(View view) {
                int count = 0;
                filter = new Filter();
                if (validateFilters()) {
                    if (classroomTypeCheckBox.isChecked()) {
                        classroomType = (ClassroomType) classroomTypeSpinner.getSelectedItem();
                        count++;
                    }
                    if (dayCheckBox.isChecked()) {
                        day = (Day) daySpinner.getSelectedItem();
                        count++;
                    }
                    if (weekTypeCheckBox.isChecked()) {
                        weekType = (WeekType) weekTypeSpinner.getSelectedItem();
                        count++;
                    }
                    if (dateCheckBox.isChecked()) {
                        date = calendar.getTime().getTime();
                        count++;
                    }
                    if (hourCheckBox.isChecked()) {
                        hour = hourSpinner.getSelectedItem().toString();
                        count++;
                    }
                    if (yearCheckBox.isChecked()) {
                        year = (Integer) yearSpinner.getSelectedItem();
                        count++;
                    }
                    if (specializationCheckBox.isChecked()) {
                        specialization = (Specialization) specializationSpinner.getSelectedItem();
                        count++;
                    }
                    if (studentsGroupCheckBox.isChecked()) {
                        studentsGroup = (StudentsGroup) studentsGroupSpinner.getSelectedItem();
                        count++;
                    }
                    if (studentsSubgroupCheckBox.isChecked()) {
                        studentsSubgroup = (Subgroup) studentsSubgroupSpinner.getSelectedItem();
                        count++;
                    }
                    filter.setClassroom(classroom);
                    filter.setClassroomType(classroomType);
                    filter.setWeekType(weekType);
                    filter.setDay(day);
                    filter.setDate(date);
                    filter.setHour(hour);
                    if (year != null) {
                        filter.setYear(year.toString());
                    } else {
                        filter.setYear(null);
                    }
                    filter.setSpecialization(specialization);
                    filter.setStudentsGroup(studentsGroup);
                    filter.setSubgroup(studentsSubgroup);
                    if (count == 0) {
                        // make request without any filter(go back to how it was)
                        ReservationFilterActivity.this.finish();
                        Intent intent = new Intent(ReservationFilterActivity.this, ReservationActivity.class);
                        intent.putExtra("classroom", classroom);
                        intent.putExtra("toFilterFrom", fromFilter);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ReservationFilterActivity.this, ReservationActivity.class);
                        intent.putExtra("classroom", classroom);
                        intent.putExtra("toFilterFrom", fromFilter);
                        intent.putExtra("filter", filter);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, ReservationActivity.class);
                intent.putExtra("toFilterFrom", fromFilter);
                intent.putExtra("classroom", classroom);
                intent.putExtra("filter", filter);
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
    public void showDays() {
        ArrayAdapter<Day> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Day.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);
        daySpinner.setSelection(0, true);

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        showSpecializations();

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO change other spinners items
                showSpecializations();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void showSpecializations() {
        Specialization[] specializations = new Specialization[4];
        if (yearSpinner.getSelectedItem().equals(3)) {
            specializations[0] = Specialization.MI;
            specializations[1] = Specialization.I;
            specializations[2] = Specialization.IA;
            specializations[3] = Specialization.IAG;

        } else {
            specializations = Specialization.values();
        }
        ArrayAdapter<Specialization> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, specializations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specializationSpinner.setAdapter(adapter);
        specializationSpinner.setSelection(0, true);
        reservationFilterPresenter.loadStudentsGroups();

        specializationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reservationFilterPresenter.loadStudentsGroups();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showStudentsGroups(List<StudentsGroup> studentsGroups) {
        List<StudentsGroup> studentsGroupsbyYearAndSpecialization = new ArrayList<>();
        for (StudentsGroup studentsGroup : studentsGroups) {
            if (studentsGroup.getYear().equals(yearSpinner.getSelectedItem().toString()) && studentsGroup.getSpecialization().equals(specializationSpinner.getSelectedItem())) {
                studentsGroupsbyYearAndSpecialization.add(studentsGroup);
            }
        }
        ArrayAdapter<StudentsGroup> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, studentsGroupsbyYearAndSpecialization);
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
        studentsSubgroupSpinner.setSelection(1, true);

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
        dayCheckBox = findViewById(R.id.day_checkbox);
        weekTypeCheckBox = findViewById(R.id.week_type_checkbox);
        dateCheckBox = findViewById(R.id.date_checkbox);
        hourCheckBox = findViewById(R.id.hour_checkbox);
        yearCheckBox = findViewById(R.id.year_checkbox);
        specializationCheckBox = findViewById(R.id.specialization_checkbox);
        studentsGroupCheckBox = findViewById(R.id.students_group_checkbox);
        studentsSubgroupCheckBox = findViewById(R.id.students_subgroup_checkbox);
    }

    private boolean validateFilters() {
        if (dateCheckBox.isChecked() && !dateEditText.getText().toString().matches(".*\\d+.*")) {
            Toast.makeText(UnibookApplication.getInstance(), "Please select the date or uncheck this filter!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (dayCheckBox.isChecked() && !dateCheckBox.isChecked()) {
            Toast.makeText(UnibookApplication.getInstance(), "Please select the date!", Toast.LENGTH_LONG).show();
            return false;
        }
        Day currentDay = initializeCurrentDay();
        if (dayCheckBox.isChecked() && dateCheckBox.isChecked() && dateEditText.getText().toString().matches(".*\\d+.*")) {
            if (!daySpinner.getSelectedItem().equals(currentDay)) {
                Toast.makeText(UnibookApplication.getInstance(), "Please select a valid date, it should match the selected day!", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        tempCalendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        tempCalendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
        tempCalendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));

        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        if (dateCheckBox.isChecked() && calendar.getTime().equals(tempCalendar.getTime()) && hourCheckBox.isChecked() && currentHour >= Integer.valueOf(hourSpinner.getSelectedItem().toString().substring(0, 2))) {
            Toast.makeText(UnibookApplication.getInstance(), "Please introduce a valid time!", Toast.LENGTH_LONG).show();
            return false;
        }
        tempCalendar.setTime(calendar.getTime());
        tempCalendar.set(Calendar.HOUR, Integer.valueOf(hourSpinner.getSelectedItem().toString().substring(0, 2)));
        if (dateCheckBox.isChecked() && tempCalendar.getTime().before(new Date())) {
            Toast.makeText(UnibookApplication.getInstance(), "Please introduce a valid date, today or after:))!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (studentsSubgroupCheckBox.isChecked() && !studentsGroupCheckBox.isChecked()) {
            Toast.makeText(UnibookApplication.getInstance(), "Please select students group and check the checkbox!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Day initializeCurrentDay() {
        Day currentDay = null;
        int currentIntDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (currentIntDay == 1) {
            currentDay = Day.SUNDAY;
        } else if (currentIntDay == 2) {
            currentDay = Day.MONDAY;
        } else if (currentIntDay == 3) {
            currentDay = Day.TUESDAY;
        } else if (currentIntDay == 4) {
            currentDay = Day.WEDNESDAY;
        } else if (currentIntDay == 5) {
            currentDay = Day.THURSDAY;
        } else if (currentIntDay == 6) {
            currentDay = Day.FRIDAY;
        } else if (currentIntDay == 7) {
            currentDay = Day.SATURDAY;
        }

        return currentDay;

    }
}
