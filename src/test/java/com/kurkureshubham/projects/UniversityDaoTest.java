package com.kurkureshubham.projects;

import com.kurkureshubham.projects.models.*;
import com.kurkureshubham.projects.repositories.CourseRepository;
import com.kurkureshubham.projects.repositories.FacultyRepository;
import com.kurkureshubham.projects.repositories.SectionRepository;
import com.kurkureshubham.projects.repositories.StudentRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import  static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@SpringBootTest(classes = Cs5200Sp2020KurkureShubhamJpaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UniversityDaoTest {
    @Autowired
    UniversityDao universityDao;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SectionRepository sectionRepository;


    @Test
    public void A_testTruncateDatabase() {
        universityDao.truncateDatabase();
    }

    @Test
    public void B_testCreateFaculty() {
        Faculty alan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
        Faculty ada = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
        universityDao.createFaculty(alan);
        universityDao.createFaculty(ada);
    }

    @Test
    public void C_testCreateStudent() {
        Student alice = new Student("alice", "password", "Alice", "Wonderland", 2020, 12000);
        Student bob = new Student("bob", "password", "Bob", "Hope", 2021, 23000);
        Student charlie = new Student("charlie", "password", "Charlie", "Brown", 2019, 21000);
        Student dan = new Student("dan", "password", "Dan", "Craig", 2019, 0);
        Student edward = new Student("edward", "password", "Edward", "Scissorhands", 2022, 11000);
        Student frank = new Student("frank", "password", "Fran", "Herbert", 2018, 0);
        Student gregory = new Student("gregory", "password", "Gregory", "Peck", 2023, 10000);

        universityDao.createStudent(alice);
        universityDao.createStudent(bob);
        universityDao.createStudent(charlie);
        universityDao.createStudent(dan);
        universityDao.createStudent(edward);
        universityDao.createStudent(frank);
        universityDao.createStudent(gregory);
    }

    @Test
    public void D_testCreateCourse() {
        Faculty alan = facultyRepository.findPersonByUsername("alan");
        Faculty ada = facultyRepository.findPersonByUsername("ada");
        Course cs1234 = new Course("CS1234");
        Course cs2345 = new Course("CS2345");
        Course cs3456 = new Course("CS3456");
        Course cs4567 = new Course("CS4567");
        cs1234.setFaculty(alan);
        cs2345.setFaculty(alan);
        cs3456.setFaculty(ada);
        cs4567.setFaculty(ada);
        universityDao.createCourse(cs1234);
        universityDao.createCourse(cs2345);
        universityDao.createCourse(cs3456);
        universityDao.createCourse(cs4567);
    }

    @Test
    public void E_testCreateSection() {
        Course cs1234 = courseRepository.findCourseByLabel("CS1234");
        Course cs2345 = courseRepository.findCourseByLabel("CS2345");
        Course cs3456 = courseRepository.findCourseByLabel("CS3456");
        Section sec4321 = new Section("SEC4321", 50, cs1234);
        Section sec5432 = new Section("SEC5432", 50, cs1234);
        Section sec6543 = new Section("SEC6543", 50, cs2345);
        Section sec7654 = new Section("SEC7654", 50, cs3456);
        universityDao.createSection(sec4321);
        universityDao.createSection(sec5432);
        universityDao.createSection(sec6543);
        universityDao.createSection(sec7654);
    }

    @Test
    public void F_testEnrollStudentInSection() {
        Student alice = studentRepository.findPersonByUsername("alice");
        Student charlie = studentRepository.findPersonByUsername("charlie");
        Student bob = studentRepository.findPersonByUsername("bob");
        Section sec4321 = sectionRepository.findSectionByTitle("SEC4321");
        Section sec5432 = sectionRepository.findSectionByTitle("SEC5432");
        Section sec6543 = sectionRepository.findSectionByTitle("SEC6543");
        universityDao.enrollStudentInSection(alice, sec4321);
        universityDao.enrollStudentInSection(alice, sec5432);
        universityDao.enrollStudentInSection(bob, sec5432);
        universityDao.enrollStudentInSection(charlie, sec6543);
    }

// VALIDATION TESTS

    @Test
    public void G_testValidateUsers() {
        List<Person> persons = universityDao.findAllUsers();
        assertEquals(9, persons.size());
    }

    @Test
    public void H_testValidateFaculty() {
        List<Faculty> faculties = universityDao.findAllFaculty();
        assertEquals(2, faculties.size());
    }

    @Test
    public void I_testValidateStudents() {
        List<Student> students = universityDao.findAllStudents();
        assertEquals(7, students.size());
    }

    @Test
    public void J_testValidateCourses() {
        List<Course> courses = universityDao.findAllCourses();
        assertEquals(4, courses.size());
    }

    @Test
    public void K_testValidateSections() {
        List<Section> sections = universityDao.findAllSections();
        assertEquals(4, sections.size());
    }

    @Test
    @Transactional
    public void L_testValidateCourseAuthorship() {
        List<Integer> numberOfCourses = new ArrayList<>();
        List<Faculty> faculties = universityDao.findAllFaculty();
        for (Faculty faculty : faculties) {
            List<Course> courses = universityDao.findCourseForAuthor(faculty);
            numberOfCourses.add(courses.size());
        }
        assertEquals("[2, 2]", numberOfCourses.toString());
    }

    @Test
    @Transactional
    public void M_testValidateSectionPerCourse() {
        List<Integer> numberOfSections = new ArrayList<>();
        List<Course> courses = universityDao.findAllCourses();
        for (Course course : courses) {
            List<Section> sections = universityDao.findSectionForCourse(course);
            numberOfSections.add(sections.size());
        }
        assertEquals("[2, 1, 1, 0]", numberOfSections.toString());
    }

    @Test
    @Transactional
    public void N_testSectionEnrollments() {
        List<Integer> numberOfStudents = new ArrayList<>();
        List<Section> sections = universityDao.findAllSections();
        for (Section section : sections) {
            List<Student> students = universityDao.findStudentsInSection(section);
            numberOfStudents.add(students.size());
        }
        assertEquals("[1, 2, 1, 0]", numberOfStudents.toString());
    }

    @Test
    @Transactional
    public void O_testStudentEnrollment() {
        List<Integer> numberOfSections = new ArrayList<>();
        List<Student> students = universityDao.findAllStudents();
        for (Student student : students) {
            List<Section> sections = universityDao.findSectionsForStudent(student);
            numberOfSections.add(sections.size());
        }
        assertEquals("[2, 1, 1, 0, 0, 0, 0]", numberOfSections.toString());
    }

    @Test
    public void P_testSectionSeats() {
        int numberOfSeats = 0;
        List<Section> sections = universityDao.findAllSections();
        for (Section section : sections) {
            numberOfSeats += section.getSeats();
        }
        assertEquals(200, numberOfSeats);
    }
}
