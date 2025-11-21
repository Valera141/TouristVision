package com.example.demo.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar una página de resultados en GraphQL
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegendPageResponse {
    
    private List<LegendResponse> content;
    private Long totalElements;
    private Integer totalPages;
    private Integer size;
    private Integer number;
    private Boolean first;
    private Boolean last;
    private Boolean empty;

    /**
     * Convierte un Page de Spring Data a LegendPageResponse
     */
    public static LegendPageResponse from(Page<LegendResponse> page) {
        return LegendPageResponse.builder()
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .number(page.getNumber())
                .first(page.isFirst())
                .last(page.isLast())
                .empty(page.isEmpty())
                .build();
    }
}