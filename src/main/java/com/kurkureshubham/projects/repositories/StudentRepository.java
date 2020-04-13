package com.kurkureshubham.projects.repositories;

import com.kurkureshubham.projects.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query("SELECT person FROM Person person WHERE person.username=:uName")
    public Student findPersonByUsername(@Param("uName") String username);

//    @Query("SELECT person FROM Person person WHERE person.username=:username AND person.password=:password")
//    public Student findPersonByCredentials(@Param("username") String username, @Param("password") String password);
}
