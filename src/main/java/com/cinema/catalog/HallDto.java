package com.cinema.catalog;

public class HallDto {
    private final Long id;
    private final String name;
    private final Integer totalRows;
    private final Integer seatsPerRow;

    public HallDto(Long id, String name, Integer totalRows, Integer seatsPerRow) {
        this.id = id;
        this.name = name;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public Integer getSeatsPerRow() {
        return seatsPerRow;
    }
}
