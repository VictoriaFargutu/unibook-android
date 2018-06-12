package com.fargutuvictoria.commons.model;


import com.fargutuvictoria.commons.model.commons.Day;
import com.fargutuvictoria.commons.model.commons.Specialization;
import com.fargutuvictoria.commons.model.commons.Subgroup;
import com.fargutuvictoria.commons.model.commons.WeekType;

import java.io.Serializable;
import java.util.Date;

public class FreeOption implements Serializable {
    private Classroom classroom;
    private WeekType weekType;
    private Date date;
    private Day day;
    private String hour;
    private String year;
    private Specialization specialization;
    private StudentsGroup studentsGroup;
    private Subgroup subgroup;

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
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
}
