package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.LegendRequest;
import com.example.demo.dto.LegendResponse;

public interface LegendService {

    List<LegendResponse> getAllLegends();
    LegendResponse getById(Integer id);
    LegendResponse create(Integer idPlace, LegendRequest request);
    LegendResponse update(Integer id, LegendRequest request);

    Page<LegendResponse> findByPlaceName(String placeName, Pageable pageable);
    Page<LegendResponse> findByStateName(String stateName, Pageable pageable);
    Page<LegendResponse> findByMunicipalityStateName(String municipalityName, Pageable pageable);

}
