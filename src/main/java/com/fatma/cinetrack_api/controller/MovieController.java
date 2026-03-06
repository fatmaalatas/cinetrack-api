package com.fatma.cinetrack_api.controller;


import com.fatma.cinetrack_api.entity.Movie;
import com.fatma.cinetrack_api.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")//bu kapının internet adresi URL
@RequiredArgsConstructor//servis katmanını enjekte eder

public class MovieController {

    //beyin katmanını "service" içeri alıyoruz ki kapıdan girenleri ona yönlendirelim
    private final MovieService movieService;


    //1. yeni veri ekleme POST
    @PostMapping
    public Movie addMovie(@Valid @RequestBody Movie movie){

        //@valid: kapıdan girmeden önce entity deki regex ve notblank kurallarına uyuyor mu diye kontrol et
        return movieService.addMovie(movie);
    }

    //2. tüm verileri listeleme 'GET'
    @GetMapping
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    //3.veri silme DELETE
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }
}

