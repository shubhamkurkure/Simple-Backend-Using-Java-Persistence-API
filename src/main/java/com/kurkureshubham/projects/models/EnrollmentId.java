package com.kurkureshubham.projects.models;

import java.io.Serializable;


public class EnrollmentId implements Serializable {

    private Integer studentId;
    private Integer sectionId;

    public boolean equals(Object other) {

        if (!(other instanceof EnrollmentId))
            return false;
        return this.sectionId.equals(((EnrollmentId) other).sectionId)
                && this.studentId.equals(((EnrollmentId) other).studentId);
    }

    public int hash() {
        return studentId + sectionId;
    }
}
