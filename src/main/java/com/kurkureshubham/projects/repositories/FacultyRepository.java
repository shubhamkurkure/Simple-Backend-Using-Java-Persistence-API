package com.kurkureshubham.projects.repositories;

import com.kurkureshubham.projects.models.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FacultyRepository extends CrudRepository<Faculty, Integer> {

    @Query("SELECT person FROM Person person WHERE person.username=:username")
    public Faculty findPersonByUsername(@Param("username") String username);

//    @Query("SELECT person FROM Person person WHERE person.username=:username AND person.password=:password")
//    public Faculty findPersonByCredentials(@Param("username") String username, @Param("password") String password);
}
