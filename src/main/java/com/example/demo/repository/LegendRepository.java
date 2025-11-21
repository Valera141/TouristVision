package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Legend;

public interface LegendRepository extends JpaRepository<Legend, Integer> {

 @Query(value = "SELECT * FROM legends ORDER BY id_legend LIMIT :pageSize OFFSET (:page * :pageSize)", nativeQuery = true)
   List<Legend> findAllWithPagination(@Param("page") int page, @Param("pageSize") int pageSize);

   @Query("""
      SELECT l 
      FROM Legend l 
      WHERE LOWER(l.place.name) LIKE LOWER(CONCAT('%', :placeName, '%'))
      """)
 Page<Legend> findByPlaceName(String placeName, Pageable pageable);

    @Query("""
       SELECT l 
       FROM Legend l 
       WHERE LOWER(l.place.municipality.name) LIKE LOWER(CONCAT('%', :municipalityName, '%'))
       """)
    Page<Legend> findByPlaceMunicipalityName(String municipalityName, Pageable pageable);

    @Query("""
       SELECT l 
       FROM Legend l 
       WHERE LOWER(l.place.municipality.state.name) LIKE LOWER(CONCAT('%', :stateName, '%'))
       """)
    Page<Legend> findByPlaceMunicipalityStateName(String stateName, Pageable pageable);

}
