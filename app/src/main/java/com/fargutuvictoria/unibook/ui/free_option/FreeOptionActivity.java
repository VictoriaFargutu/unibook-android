package com.fargutuvictoria.unibook.ui.free_option;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.Course;
import com.fargutuvictoria.commons.model.Filter;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.commons.model.commons.Specialization;
import com.fargutuvictoria.commons.model.commons.Subgroup;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.reservation.ReservationActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private Spinner yearsSpinner;
    private Spinner specializationsSpinner;
    private Spinner groupsSpinner;
    private Spinner subgroupsSpinner;
    private String fromFilter;
    private Button makeReservationButton;
    private Button sendEmailButton;

    private FreeOption freeOption;
    private Filter filter;

    public static String YOU_CAN_CHOOSE = "You_Can_Choose";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_option);

        Toolbar toolbar = findViewById(R.id.make_reservation_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        freeOption = (FreeOption) intent.getSerializableExtra("freeOption");
        filter = (Filter) intent.getSerializableExtra("filter");
        fromFilter = (String) intent.getSerializableExtra("toFilterFrom");

        dateText = findViewById(R.id.date_text);
        weekTypeText = findViewById(R.id.week_type_text);
        dayText = findViewById(R.id.day_text);
        hourText = findViewById(R.id.hour_text);
        classroomText = findViewById(R.id.classroom_text);
        coursesSpinner = findViewById(R.id.course_spinner);
        yearsSpinner = findViewById(R.id.year_spinner);
        specializationsSpinner = findViewById(R.id.specialization_spinner);
        groupsSpinner = findViewById(R.id.students_group_spinner);
        subgroupsSpinner = findViewById(R.id.subgroup_spinner);
        makeReservationButton = findViewById(R.id.make_reservation_button);
        sendEmailButton = findViewById(R.id.email_button);

        sendEmailButton.setVisibility(View.GONE);

        RelativeLayout.LayoutParams ll = (RelativeLayout.LayoutParams) makeReservationButton.getLayoutParams();
        ll.addRule(RelativeLayout.CENTER_HORIZONTAL);
        makeReservationButton.setLayoutParams(ll);

        initializeFreeOptionActivityFields();

        presenter = new FreeOptionPresenter(this);
        presenter.loadCourses();
        presenter.loadStudentsGroups();
        showYear();
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
                Integer year = (Integer) yearsSpinner.getSelectedItem();
                if (year != null && year != 0) {
                    reservation.setYear((yearsSpinner.getSelectedItem()).toString());
                }
                Specialization specialization = (Specialization) specializationsSpinner.getSelectedItem();
                if (specialization != null && !specialization.name().equals(YOU_CAN_CHOOSE)) {
                    reservation.setSpecialization((Specialization) specializationsSpinner.getSelectedItem());
                }


                StudentsGroup studentsGroup = (StudentsGroup) groupsSpinner.getSelectedItem();
                if (studentsGroup != null && !studentsGroup.getName().equals(YOU_CAN_CHOOSE)) {
                    reservation.setStudentsGroup((StudentsGroup) groupsSpinner.getSelectedItem());

                    //show e-mail button
                    RelativeLayout.LayoutParams ll = (RelativeLayout.LayoutParams) makeReservationButton.getLayoutParams();
                    ll.addRule(RelativeLayout.ALIGN_PARENT_START);
                    makeReservationButton.setLayoutParams(ll);
                    sendEmailButton.setVisibility(View.VISIBLE);
                }
                if (subgroupsSpinner.getSelectedItem() != null && subgroupsSpinner.getSelectedItem() != Subgroup.You_Can_Choose) {
                    reservation.setSubgroup((Subgroup) subgroupsSpinner.getSelectedItem());
                }
                presenter.makeReservation(reservation);
                makeReservationButton.setEnabled(false);
                makeReservationButton.setBackgroundColor(getResources().getColor(R.color.darkGray));
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String groupEmail = "g" + freeOption.getStudentsGroup().getName().toLowerCase() + "@student.unitbv.ro";
                emailIntent.setData(Uri.parse("mailto:" + groupEmail));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    System.err.println(e.getMessage());
                }
            }
        });

        // Create an explicit intent for an Activity in your app
        Intent notificationIntent = new Intent(this, ReservationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "12345")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(11111111, mBuilder.build());
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
    public void showYear() {
        List<Integer> years = new ArrayList<>();
        years.add(0);
        years.add(1);
        years.add(2);
        years.add(3);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearsSpinner.setAdapter(adapter);
        if (freeOption.getYear() != null) {
            yearsSpinner.setSelection(years.indexOf(Integer.parseInt(freeOption.getYear())));
            RelativeLayout.LayoutParams ll = (RelativeLayout.LayoutParams) makeReservationButton.getLayoutParams();
            ll.addRule(RelativeLayout.ALIGN_PARENT_START);
            makeReservationButton.setLayoutParams(ll);
            sendEmailButton.setVisibility(View.VISIBLE);
        } else {
            yearsSpinner.setSelection(0, true);
        }
        showSpecializations();

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        Specialization[] specializations = new Specialization[5];
        if (yearsSpinner.getSelectedItem().equals(3)) {
            specializations[0] = Specialization.You_Can_Choose;
            specializations[1] = Specialization.MI;
            specializations[2] = Specialization.I;
            specializations[3] = Specialization.IA;
            specializations[4] = Specialization.IAG;

        } else {
            specializations = Specialization.values();
        }
        ArrayAdapter<Specialization> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, specializations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specializationsSpinner.setAdapter(adapter);

        if (freeOption.getSpecialization() != null) {
            for (int i = 0; i < specializations.length; i++) {
                if (specializations[i].name().equals(freeOption.getSpecialization().name())) {
                    specializationsSpinner.setSelection(i);
                    RelativeLayout.LayoutParams ll = (RelativeLayout.LayoutParams) makeReservationButton.getLayoutParams();
                    ll.addRule(RelativeLayout.ALIGN_PARENT_START);
                    makeReservationButton.setLayoutParams(ll);
                    sendEmailButton.setVisibility(View.VISIBLE);
                    break;
                }
            }
        } else {
            specializationsSpinner.setSelection(0);
        }
        presenter.loadStudentsGroups();

        specializationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.loadStudentsGroups();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showStudentsGroups(List<StudentsGroup> studentsGroups) {
        ArrayAdapter<StudentsGroup> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        StudentsGroup defaultNoSelection = new StudentsGroup();
        defaultNoSelection.setName(YOU_CAN_CHOOSE);
        if (freeOption.getStudentsGroup() == null) {
            List<StudentsGroup> studentsGroupsbyYearAndSpecialization = new ArrayList<>();
            studentsGroupsbyYearAndSpecialization.add(0, defaultNoSelection);
            for (StudentsGroup studentsGroup : studentsGroups) {
                if (studentsGroup.getYear().equals(yearsSpinner.getSelectedItem().toString()) && studentsGroup.getSpecialization().equals(specializationsSpinner.getSelectedItem())) {
                    studentsGroupsbyYearAndSpecialization.add(studentsGroup);
                }
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, studentsGroupsbyYearAndSpecialization);
        } else {
            studentsGroups.add(0, defaultNoSelection);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, studentsGroups);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupsSpinner.setAdapter(adapter);
        if (freeOption.getStudentsGroup() != null) {
            groupsSpinner.setSelection(studentsGroups.indexOf(freeOption.getStudentsGroup()));
            RelativeLayout.LayoutParams ll = (RelativeLayout.LayoutParams) makeReservationButton.getLayoutParams();
            ll.addRule(RelativeLayout.ALIGN_PARENT_START);
            makeReservationButton.setLayoutParams(ll);
            sendEmailButton.setVisibility(View.VISIBLE);
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
                intent.putExtra("toFilterFrom", fromFilter);
                intent.putExtra("classroom", freeOption.getClassroom());
                intent.putExtra("filter", filter);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
