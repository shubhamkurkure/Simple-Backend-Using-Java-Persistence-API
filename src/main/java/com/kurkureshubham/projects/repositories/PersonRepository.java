package com.kurkureshubham.projects.repositories;

import com.kurkureshubham.projects.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
