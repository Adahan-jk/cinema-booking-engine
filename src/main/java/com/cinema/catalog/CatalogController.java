package com.cinema.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class CatalogController {
    private final MovieRepository movies;

    public CatalogController(MovieRepository movies){
        this.movies = movies;
    }

    @GetMapping
    public List<MovieDto> list() {
        return movies.findAll().stream().map(CatalogMapper::toDto).toList();
    }
}
