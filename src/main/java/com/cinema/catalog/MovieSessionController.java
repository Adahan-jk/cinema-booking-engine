package com.cinema.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class MovieSessionController {
    private final MovieSessionRepository sessions;

    public MovieSessionController(MovieSessionRepository sessions){
        this.sessions = sessions;
    }

    @GetMapping
    public List<MovieSessionDto> list(@RequestParam(required = false) Long movieId){
        if (movieId != null){
            return sessions.findByMovieId(movieId).stream().map(CatalogMapper::toDto).toList();
        }
        return sessions.findAll().stream().map(CatalogMapper::toDto).toList();
    }
}
