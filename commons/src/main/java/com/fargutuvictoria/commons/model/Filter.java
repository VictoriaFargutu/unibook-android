package com.fargutuvictoria.commons.model;

import com.fargutuvictoria.commons.model.commons.ClassroomType;
import com.fargutuvictoria.commons.model.commons.Day;
import com.fargutuvictoria.commons.model.commons.Specialization;
import com.fargutuvictoria.commons.model.commons.Subgroup;
import com.fargutuvictoria.commons.model.commons.WeekType;

import java.util.Date;

public class Filter {
    private ClassroomType classroomType;
    private WeekType weekType;
    private Day day;
    private Date date;
    private String hour;
    private String year;
    private Specialization specialization;
    private StudentsGroup studentsGroup;
    private Subgroup subgroup;

    public ClassroomType getClassroomType() {
        return classroomType;
    }

    public void setClassroomType(ClassroomType classroomType) {
        this.classroomType = classroomType;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
