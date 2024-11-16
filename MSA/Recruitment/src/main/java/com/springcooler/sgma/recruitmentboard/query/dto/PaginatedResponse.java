package com.springcooler.sgma.recruitmentboard.query.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    private List<T> data;
    private int totalPages;
    private long totalElements;
}
