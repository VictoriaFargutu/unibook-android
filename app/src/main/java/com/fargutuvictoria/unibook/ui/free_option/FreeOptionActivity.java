package com.fargutuvictoria.unibook.ui.free_option;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.Course;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.commons.model.commons.Subgroup;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.reservation.ReservationActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class FreeOptionActivity extends AppCompatActivity implements FreeOptionContract.View {
    private FreeOptionContract.Presenter presenter;

    private TextView dateText;
    private TextView weekTypeText;
    private TextView dayText;
    private TextView hourText;
    private TextView classroomText;
    private Spinner coursesSpinner;
    private Spinner groupsSpinner;
    private Spinner subgroupsSpinner;

    private FreeOption freeOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_option);

        Toolbar toolbar = findViewById(R.id.make_reservation_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        freeOption = (FreeOption) intent.getSerializableExtra("freeOption");

        dateText = findViewById(R.id.date_text);
        weekTypeText = findViewById(R.id.week_type_text);
        dayText = findViewById(R.id.day_text);
        hourText = findViewById(R.id.hour_text);
        classroomText = findViewById(R.id.classroom_text);
        coursesSpinner = findViewById(R.id.course_spinner);
        groupsSpinner = findViewById(R.id.students_group_spinner);
        subgroupsSpinner = findViewById(R.id.subgroup_spinner);

        initializeFreeOptionActivityFields();

        presenter = new FreeOptionPresenter(this);
        presenter.loadStudentsGroups();
        showSubgroups();
    }

    private void initializeFreeOptionActivityFields() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateText.setText(sdf.format(freeOption.getDate()));
        weekTypeText.setText(freeOption.getWeekType().name());
        dayText.setText(freeOption.getDay().name());
        hourText.setText(freeOption.getHour());
        classroomText.setText(freeOption.getClassroom().getName());
    }

    @Override
    public void showCourses(List<Course> courses) {
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(adapter);

        coursesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showStudentsGroups(List<StudentsGroup> studentsGroups) {
        ArrayAdapter<StudentsGroup> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, studentsGroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupsSpinner.setAdapter(adapter);

        groupsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        subgroupsSpinner.setAdapter(adapter);

        subgroupsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
}
