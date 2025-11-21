package com.example.demo.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegendResponse {

    private Integer idLegend;
    private Integer idPlace;
    private String title;
    private String story;
    private String origin;
}
