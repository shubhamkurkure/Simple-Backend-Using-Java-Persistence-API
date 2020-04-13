package com.kurkureshubham.projects.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student extends Person {
    private int gradYear;
    private long scholarship;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    public Student() {
    }

    public Student(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public Student(String username, String password, String firstName, String lastName, int gradYear, long scholarship) {
        super(username, password, firstName, lastName);
        this.gradYear = gradYear;
        this.scholarship = scholarship;
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public long getScholarship() {
        return scholarship;
    }

    public void setScholarship(long scholarship) {
        this.scholarship = scholarship;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }


}
