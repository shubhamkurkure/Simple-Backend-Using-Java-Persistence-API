package com.kurkureshubham.projects.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Faculty extends Person {
    private String office;
    private Boolean tenured;

    @OneToMany(mappedBy = "faculty")
    private List<Course> coursesTaught;

    public Faculty() {
    }

    public Faculty(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public Faculty(String username, String password, String firstName, String lastName, String office, boolean tenured) {
        super(username, password, firstName, lastName);
        this.office = office;
        this.tenured = tenured;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Boolean getTenured() {
        return tenured;
    }

    public void setTenured(Boolean tenured) {
        this.tenured = tenured;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public void addCourse(Course course) {
        this.coursesTaught.add(course);
        course.setFaculty(this);
    }

}
