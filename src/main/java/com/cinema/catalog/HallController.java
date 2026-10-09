package com.cinema.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/halls")
public class HallController {
    private final HallRepository halls;

    public HallController(HallRepository halls){
        this.halls = halls;
    }

    @GetMapping
    public List<HallDto> list(){
        return halls.findAll().stream().map(CatalogMapper::toDto).toList();
    }
}
