package com.kurkureshubham.projects.repositories;

import com.kurkureshubham.projects.models.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends CrudRepository<Section, Integer> {

    @Query("SELECT section FROM Section section WHERE section.title=:title")
    public Section findSectionByTitle(@Param("title") String title);
}
