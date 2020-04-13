package com.kurkureshubham.projects.models;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@IdClass(EnrollmentId.class)

public class Enrollment {

    @Id
    @Column(name = "student_id")
    private Integer studentId;
    @Id
    @Column(name = "section_id")
    private Integer sectionId;
    private int grade;
    private String feedback;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id", referencedColumnName = "Id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "section_id", referencedColumnName = "Id", insertable = false, updatable = false)
    private Section section;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}