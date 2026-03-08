package com.fatma.cinetrack_api.controller;


import com.fatma.cinetrack_api.entity.Movie;
import com.fatma.cinetrack_api.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.fatma.cinetrack_api.service.RecommendationService;

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

    @Autowired//Spring Framework'ün "Dependency Injection" (Bağımlılık Enjeksiyonu) özelliğidir.
    //RecommendationService sınıfından yeni bir nesne (new RecommendationService()) yaratmak yerine,
    // bellekte var olan nesneyi buraya bağlar. Bu, bellek yönetimini optimize eder.
    private RecommendationService recommendationService;



    @GetMapping("/{id}/recommendations")//Bu metoda bir adres (Route) atar. Tarayıcıdan
    public ResponseEntity<List<Movie>> getRecommendations(@PathVariable Long id) {
        List<Movie> recommendedMovies = recommendationService.getRecommendations(id);
        return ResponseEntity.ok(recommendedMovies);
        //ResponseEntity.ok(...): İşlem bittiğinde, bulunan öneri listesini ve "200 OK"
        // (Başarılı) HTTP durum kodunu paketleyip kullanıcıya geri gönderir.
    }
}

