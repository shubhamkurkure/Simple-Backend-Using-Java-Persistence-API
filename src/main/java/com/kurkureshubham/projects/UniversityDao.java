package com.kurkureshubham.projects;

import com.kurkureshubham.projects.models.*;
import com.kurkureshubham.projects.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UniversityDao {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public void truncateDatabase() {
        enrollmentRepository.deleteAll();
        sectionRepository.deleteAll();
        courseRepository.deleteAll();
        facultyRepository.deleteAll();
        studentRepository.deleteAll();
        personRepository.deleteAll();
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public Section addSectionToCourse(Section section, Course course) {
        section.setCourse(course);
        return sectionRepository.save(section);
    }

    public Course setAuthorForCourse(Faculty faculty, Course course) {
        course.setFaculty(faculty);
        return courseRepository.save(course);
    }

    public Boolean enrollStudentInSection(Student student, Section section) {
        if(section.getSeats()<=0) {
            return false;
        }


        Enrollment enrollment = new Enrollment();
        section.setSeats(section.getSeats()-1);
        enrollment.setStudentId(student.getId());
        enrollment.setSectionId(section.getId());

        System.out.println("STUDENT ID: "+enrollment.getStudentId());
        System.out.println("STUDENT1 ID: "+enrollment.getSectionId());

        enrollmentRepository.save(enrollment);
        return true;
    }

    public List<Person> findAllUsers() {
        return (List<Person>) personRepository.findAll();
    }

    public List<Faculty> findAllFaculty() {
        return (List<Faculty>) facultyRepository.findAll();
    }

    public List<Student> findAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public List<Course> findAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public List<Section> findAllSections() {
        return (List<Section>) sectionRepository.findAll();
    }

    public List<Course> findCourseForAuthor(Faculty faculty) {
        return faculty.getCoursesTaught();
    }

    public List<Section> findSectionForCourse(Course course) {
        return course.getSections();
    }

    public List<Student> findStudentsInSection(Section section) {
        List<Student> students = new ArrayList<>();

        for (Enrollment eachEnrollment : section.getEnrollments()) {
            students.add(eachEnrollment.getStudent());
        }
        return students;
    }

/*    public List<Section> findSectionsForStudent(Student student) {
        List<Section> sections = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByStudent(student.getId());
        for (Enrollment enrollment : enrollments) {
            sections.add(enrollment.getSection());
        }
        return sections;
    }*/

    public List<Section> findSectionsForStudent(Student student) {
        return enrollmentRepository.findSectionsByStudent(student.getId());
    }
}
