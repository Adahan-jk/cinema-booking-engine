package com.cinema.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {
    List<MovieSession> findByMovieId(Long movieId);
}
