package com.kurkureshubham.projects.repositories;

import com.kurkureshubham.projects.models.Course;
import com.kurkureshubham.projects.models.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Query("SELECT course FROM Course course WHERE course.label=:label")
    public Course findCourseByLabel(@Param("label") String label);

//    @Query("SELECT course FROM Course course WHERE course.faculty=:faculty")
//    public List<Course> findCoursesForFaculty(@Param("faculty") Faculty faculty);

}
