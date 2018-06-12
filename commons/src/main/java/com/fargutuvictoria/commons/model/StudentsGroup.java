package com.fargutuvictoria.commons.model;


import com.fargutuvictoria.commons.model.commons.Specialization;

import java.io.Serializable;
import java.util.Objects;

public class StudentsGroup implements Serializable{
    private Long id;
    private String name;
    private String year;
    private Specialization specialization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsGroup that = (StudentsGroup) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(year, that.year) &&
                specialization == that.specialization;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, year, specialization);
    }
}
