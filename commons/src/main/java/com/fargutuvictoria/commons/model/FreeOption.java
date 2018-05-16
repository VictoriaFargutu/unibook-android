package com.fargutuvictoria.commons.model;


import com.fargutuvictoria.commons.model.commons.ClassroomType;
import com.fargutuvictoria.commons.model.commons.Day;
import com.fargutuvictoria.commons.model.commons.Hour;
import com.fargutuvictoria.commons.model.commons.Subgroup;
import com.fargutuvictoria.commons.model.commons.WeekType;

import java.util.Date;

public class FreeOption {
    private ClassroomType classroomType;
    private Classroom classroom;
    private WeekType weekType;
    private Date date;
    private Day day;
    private Hour hour;
    private StudentsGroup studentsGroup;
    private Subgroup subgroup;

    public ClassroomType getClassroomType() {
        return classroomType;
    }

    public void setClassroomType(ClassroomType classroomType) {
        this.classroomType = classroomType;
    }

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

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
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
