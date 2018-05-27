package com.fargutuvictoria.commons.model;

import com.fargutuvictoria.commons.model.commons.Day;
import com.fargutuvictoria.commons.model.commons.Subgroup;
import com.fargutuvictoria.commons.model.commons.WeekType;

/**
 * Created by fargutuvictoria on 05/05/2018.
 */

public class Reservation {
    private Long id;
    private StudentsGroup studentsGroup;
    private Subgroup subgroup;
    private Classroom classroom;
    private Course course;
    private WeekType weekType;
    private Day day;
    private Long date;
    private String hour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public Subgroup getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Subgroup subgroup) {
        this.subgroup = subgroup;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
