package com.kurkureshubham.projects.repositories;

import com.kurkureshubham.projects.models.Enrollment;
import com.kurkureshubham.projects.models.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {

//    @Query("SELECT enrollment FROM Enrollment enrollment WHERE enrollment.studentId = :studentId")
//    public List<Enrollment> findEnrollmentsByStudent(@Param("studentId") Integer studentId);

    @Query("SELECT section FROM Section section, Enrollment enrollment WHERE enrollment.studentId=:studentId " +
            "AND enrollment.sectionId = section.id")
    public List<Section> findSectionsByStudent(@Param("studentId") Integer studentId);

}
