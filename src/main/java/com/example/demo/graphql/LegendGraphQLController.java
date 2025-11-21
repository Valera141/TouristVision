package com.example.demo.graphql;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.LegendPageResponse;
import com.example.demo.dto.LegendRequest;
import com.example.demo.dto.LegendResponse;
import com.example.demo.service.LegendService;

import lombok.RequiredArgsConstructor;

/**
 * Controller GraphQL para gestionar leyendas
 * Versión compatible con Spring Boot 3.x
 */
@Controller
@RequiredArgsConstructor
public class LegendGraphQLController {

    private final LegendService legendService;

    // ==================== QUERIES ====================
    
    /**
     * Obtiene todas las leyendas
     */
    public List<LegendResponse> getAllLegends() {
        return legendService.getAllLegends();
    }

    /**
     * Obtiene una leyenda por ID
     */
    public LegendResponse getLegendById(Integer id) {
        return legendService.getById(id);
    }

    /**
     * Busca leyendas por nombre del lugar
     */
    public LegendPageResponse findByPlaceName(String name, PageableInput pageable) {
        Pageable pageRequest = createPageable(pageable);
        Page<LegendResponse> page = legendService.findByPlaceName(name, pageRequest);
        return LegendPageResponse.from(page);
    }

    /**
     * Busca leyendas por nombre del municipio
     */
    public LegendPageResponse findByMunicipalityName(String name, PageableInput pageable) {
        Pageable pageRequest = createPageable(pageable);
        Page<LegendResponse> page = legendService.findByMunicipalityStateName(name, pageRequest);
        return LegendPageResponse.from(page);
    }

    /**
     * Busca leyendas por nombre del estado
     */
    public LegendPageResponse findByStateName(String name, PageableInput pageable) {
        Pageable pageRequest = createPageable(pageable);
        Page<LegendResponse> page = legendService.findByStateName(name, pageRequest);
        return LegendPageResponse.from(page);
    }

    // ==================== MUTATIONS ====================
    
    /**
     * Crea una nueva leyenda
     */
    public LegendResponse createLegend(Integer placeId, LegendRequest request) {
        return legendService.create(placeId, request);
    }

    /**
     * Actualiza una leyenda existente
     */
    public LegendResponse updateLegend(Integer id, LegendRequest request) {
        return legendService.update(id, request);
    }

    // ==================== HELPER METHODS ====================
    
    /**
     * Convierte PageableInput a Pageable de Spring Data
     */
    private Pageable createPageable(PageableInput input) {
        if (input == null) {
            return PageRequest.of(0, 10);
        }
        
        int page = input.getPage() != null ? input.getPage() : 0;
        int size = input.getSize() != null ? input.getSize() : 10;
        
        // Validación de límites
        page = Math.max(0, page);
        size = Math.min(100, Math.max(1, size));
        
        if (input.getSort() != null && !input.getSort().isEmpty()) {
            Sort sort = Sort.by(input.getSort().stream()
                    .map(this::parseSort)
                    .toArray(Sort.Order[]::new));
            return PageRequest.of(page, size, sort);
        }
        
        return PageRequest.of(page, size);
    }
    
    /**
     * Parsea un string de ordenamiento
     */
    private Sort.Order parseSort(String sortString) {
        String[] parts = sortString.split(",");
        String property = parts[0];
        
        if (parts.length > 1 && "desc".equalsIgnoreCase(parts[1])) {
            return Sort.Order.desc(property);
        }
        
        return Sort.Order.asc(property);
    }

    /**
     * Clase interna para recibir parámetros de paginación
     */
    public static class PageableInput {
        private Integer page;
        private Integer size;
        private List<String> sort;

        public Integer getPage() { return page; }
        public void setPage(Integer page) { this.page = page; }
        
        public Integer getSize() { return size; }
        public void setSize(Integer size) { this.size = size; }
        
        public List<String> getSort() { return sort; }
        public void setSort(List<String> sort) { this.sort = sort; }
    }
}