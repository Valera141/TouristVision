package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Municipality;

public interface MunicipalityRepository extends JpaRepository<Municipality, Integer> {

    // // municipality for name
    // @Query(value = "SELECT * FROM municipalities WHERE name ILIKE CONCAT('%', :name, '%')", nativeQuery = true)
    // List<Municipality> findByNameLike(@Param("name") String name);

    // // municipalies for ID state
    // @Query(value = "SELECT * FROM municipalities WHERE id_state = :id_state", nativeQuery = true)
    // List<Municipality> findByStateId(@Param("idState") Integer idState);

    // // pagination
    // @Query(value = "SELECT * FROM municipalities ORDER BY id_municipality LIMIT :pageSize OFFSET (:page * :pageSize)", nativeQuery = true)
    // List<Municipality> findAllWithPagination(@Param("page") int page, @Param("pageSize") int pageSize);

}
