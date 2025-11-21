package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LegendRequest;
import com.example.demo.dto.LegendResponse;
import com.example.demo.mapper.LegendMapper;
import com.example.demo.model.Legend;
import com.example.demo.model.TouristPlace;
import com.example.demo.repository.LegendRepository;
import com.example.demo.repository.TouristPlaceRepository;

@Service
public class LegendServiceImpl implements LegendService {

    private final LegendRepository legendRepository;
    private final TouristPlaceRepository touristPlaceRepository;
    private final LegendMapper legendMapper;

    // Inyección de dependencias
    public LegendServiceImpl(
            LegendRepository legendRepository,
            TouristPlaceRepository touristPlaceRepository,
            LegendMapper legendMapper) {
        this.legendRepository = legendRepository;
        this.touristPlaceRepository = touristPlaceRepository;
        this.legendMapper = legendMapper;
    }
    
    // --- Métodos CRUD Básicos ---

    @Override
    @Transactional(readOnly = true)
    public List<LegendResponse> getAllLegends() {
        return legendRepository.findAll().stream()
                .map(legendMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Se cambia el tipo de ID a Integer. Se usa NoSuchElementException, que es común
    // para ser manejada por un RestExceptionHandler como 404 Not Found.
    @Override
    @Transactional(readOnly = true)
    public LegendResponse getById(Integer id) {
        Legend legend = legendRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Legend not found with id: " + id));
        return legendMapper.toResponse(legend);
    }

    // Se cambia el tipo de ID a Integer.
    @Override
    @Transactional
    public LegendResponse create(Integer idPlace, LegendRequest legendRequest) {

    TouristPlace place = touristPlaceRepository.findById(idPlace)
            .orElseThrow(() -> new NoSuchElementException("TouristPlace not found"));

    Legend legend = legendMapper.toEntity(legendRequest);
    legend.setPlace(place);

    Legend saved = legendRepository.save(legend);
    return legendMapper.toResponse(saved);
}

    // Se cambia el tipo de ID a Integer.
    @Override
    @Transactional
    public LegendResponse update(Integer id, LegendRequest legendRequest) {
        Legend legend = legendRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Legend not found with id: " + id));
        
        // Copiar propiedades del DTO a la Entidad
        legendMapper.copyToEntity(legendRequest, legend);
        
        Legend updatedLegend = legendRepository.save(legend);
        return legendMapper.toResponse(updatedLegend);
    }

    // ¡MÉTODO DELETE ELIMINADO según tu solicitud!

    // --- Métodos de Búsqueda Derivada ---

    @Override
    @Transactional(readOnly = true)
    public Page<LegendResponse> findByPlaceName(String placeName, Pageable pageable) {
        Page<Legend> legendsPage = legendRepository.findByPlaceName(placeName, pageable);
        return legendsPage.map(legendMapper::toResponse);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<LegendResponse> findByStateName(String stateName, Pageable pageable) {
        Page<Legend> legendsPage = legendRepository.findByPlaceMunicipalityStateName(stateName, pageable);
        return legendsPage.map(legendMapper::toResponse);
    }


    @Override
@Transactional(readOnly = true)
public Page<LegendResponse> findByMunicipalityStateName(String municipalityName, Pageable pageable) {
    Page<Legend> legendsPage = legendRepository.findByPlaceMunicipalityName(municipalityName, pageable);
    return legendsPage.map(legendMapper::toResponse);
}


}