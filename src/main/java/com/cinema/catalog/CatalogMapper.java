package com.cinema.catalog;

public final class CatalogMapper {
    private CatalogMapper(){

    }

    public static MovieDto toDto(Movie movie){
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getDurationMinutes(), movie.getRating());
    }

    public static HallDto toDto(Hall hall){
        return new HallDto(hall.getId(), hall.getName(), hall.getTotalRows(), hall.getSeatsPerRow());
    }

    public static MovieSessionDto toDto(MovieSession session){
        return new MovieSessionDto(session.getId(), session.getMovie().getId(),
                session.getMovie().getTitle(), session.getHall().getId(),
                session.getHall().getName(), session.getStartTime(), session.getPrice());
    }
}
