package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LegendRequest;
import com.example.demo.dto.LegendResponse;
import com.example.demo.service.LegendService;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LegendController {

    private final LegendService legendService;



    // --- CRUD Básico ---
    @PostMapping("/place/{placeId}")
    public ResponseEntity<LegendResponse> create(
            @PathVariable Integer placeId,
            @Valid @RequestBody LegendRequest request) {
        LegendResponse newLegend = legendService.create(placeId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLegend);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LegendResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(legendService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LegendResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody LegendRequest request) {
        return ResponseEntity.ok(legendService.update(id, request));
    }
    
       @GetMapping("/legends")
    public ResponseEntity<List<LegendResponse>> getAll() {
        return ResponseEntity.ok(legendService.getAllLegends());
    }

// --- Consultas Personalizadas ---

@GetMapping("/search/place")
public ResponseEntity<Page<LegendResponse>> findByPlaceName(
        @RequestParam(name = "name") String placeName,
        @Parameter(hidden = true) Pageable pageable) {
    return ResponseEntity.ok(legendService.findByPlaceName(placeName, pageable));
}

@GetMapping("/search/municipality")
public ResponseEntity<Page<LegendResponse>> findByMunicipalityStateName(
        @RequestParam(name = "name") String municipalityName,
        @Parameter(hidden = true) Pageable pageable) {
    return ResponseEntity.ok(legendService.findByMunicipalityStateName(municipalityName, pageable));
}

@GetMapping("/search/state")
public ResponseEntity<Page<LegendResponse>> findByStateName(
        @RequestParam(name = "name") String stateName,
        @Parameter(hidden = true) Pageable pageable) {
    return ResponseEntity.ok(legendService.findByStateName(stateName, pageable));
}
}