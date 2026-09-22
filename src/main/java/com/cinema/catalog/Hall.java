package com.cinema.catalog;

import jakarta.persistence.*;

@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "total_rows", nullable = false)
    private Integer totalRows;

    @Column(name = "seats_per_row", nullable = false)
    private Integer seatsPerRow;

    protected Hall() {
    }

    public Hall(String name, Integer totalRows, Integer seatsPerRow) {
        this.name = name;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getTotalRows() { return totalRows; }
    public Integer getSeatsPerRow() { return seatsPerRow; }

    public void setName(String name) { this.name = name; }
    public void setTotalRows(Integer totalRows) { this.totalRows = totalRows; }
    public void setSeatsPerRow(Integer seatsPerRow) { this.seatsPerRow = seatsPerRow; }


}