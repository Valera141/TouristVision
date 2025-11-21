// com.example.demo.mapper.LegendMapper.java
package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.LegendRequest;
import com.example.demo.dto.LegendResponse;
import com.example.demo.model.Legend;

@Component
public final class LegendMapper {

    public LegendResponse toResponse(Legend legend) {
    return LegendResponse.builder()
        .idLegend(legend.getId())
        .idPlace(legend.getPlace() != null ? legend.getPlace().getId() : null)
        .title(legend.getTitle())
        .story(legend.getStory())
        .origin(legend.getOrigin())
        .build();
}

    public Legend toEntity(LegendRequest dto) {
        if (dto == null) return null;
        return Legend.builder()
                .title(dto.getTitle())
                .story(dto.getStory())
                .origin(dto.getOrigin())
                .build();
    }

    public void copyToEntity(LegendRequest dto, Legend entity) {
        if (dto == null || entity == null) return;
        entity.setTitle(dto.getTitle());
        entity.setStory(dto.getStory());
        entity.setOrigin(dto.getOrigin());
    }
}