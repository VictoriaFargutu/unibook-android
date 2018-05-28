package com.fargutuvictoria.unibook.ui.free_option;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.Course;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.commons.model.Reservation;
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
    private Button makeReservationButton;

    private FreeOption freeOption;

    public static String YOU_CAN_CHOOSE = "You_Can_Choose";


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
        makeReservationButton = findViewById(R.id.make_reservation_button);

        initializeFreeOptionActivityFields();

        presenter = new FreeOptionPresenter(this);
        presenter.loadCourses();
        presenter.loadStudentsGroups();
        showSubgroups();

        makeReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservation reservation = new Reservation();
                reservation.setClassroom(freeOption.getClassroom());
                reservation.setDate(freeOption.getDate().getTime());
                reservation.setWeekType(freeOption.getWeekType());
                reservation.setDay(freeOption.getDay());
                reservation.setHour(freeOption.getHour());
                Course course = (Course) coursesSpinner.getSelectedItem();
                if (course != null && !course.getName().equals(YOU_CAN_CHOOSE)) {
                    reservation.setCourse((Course) coursesSpinner.getSelectedItem());
                }
                StudentsGroup studentsGroup = (StudentsGroup) groupsSpinner.getSelectedItem();
                if (studentsGroup != null && !studentsGroup.getName().equals(YOU_CAN_CHOOSE)) {
                    reservation.setStudentsGroup((StudentsGroup) groupsSpinner.getSelectedItem());
                }
                if (subgroupsSpinner.getSelectedItem() != null && subgroupsSpinner.getSelectedItem() != Subgroup.You_Can_Choose) {
                    reservation.setSubgroup((Subgroup) subgroupsSpinner.getSelectedItem());
                }
                presenter.makeReservation(reservation);
                makeReservationButton.setEnabled(false);
                makeReservationButton.setBackgroundColor(getResources().getColor(R.color.darkGray));
            }
        });
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
        Course defaultNoSelection = new Course();
        defaultNoSelection.setName(YOU_CAN_CHOOSE);
        courses.add(0, defaultNoSelection);
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(adapter);
        coursesSpinner.setSelection(0);


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
        StudentsGroup defaultNoSelection = new StudentsGroup();
        defaultNoSelection.setName(YOU_CAN_CHOOSE);
        studentsGroups.add(0, defaultNoSelection);
        ArrayAdapter<StudentsGroup> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, studentsGroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupsSpinner.setAdapter(adapter);
        if (freeOption.getStudentsGroup() != null) {
            groupsSpinner.setSelection(studentsGroups.indexOf(freeOption.getStudentsGroup()));
        } else {
            groupsSpinner.setSelection(0);
        }
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
        if (freeOption.getSubgroup() != null) {
            if (freeOption.getSubgroup().equals(Subgroup.A)) {
                subgroupsSpinner.setSelection(1);
            } else if (freeOption.getSubgroup().equals(Subgroup.B)) {
                subgroupsSpinner.setSelection(2);
            }
        } else {
            subgroupsSpinner.setSelection(0);
        }
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
                intent.putExtra("classroom", freeOption.getClassroom());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
