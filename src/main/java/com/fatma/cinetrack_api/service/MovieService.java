package com.fatma.cinetrack_api.service;

import com.fatma.cinetrack_api.entity.Movie;
import com.fatma.cinetrack_api.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service //servis katmanındayız
@RequiredArgsConstructor //enjeksiyon şapkası

public class MovieService {

    //depo görevlisi
    private final MovieRepository movieRepository;

    //1. veri ekleme
    public Movie addMovie(Movie movie){
        //eğer bu film zaten varsa hata fırlat
        if(movieRepository.existsByTitle(movie.getTitle())){

            throw new RuntimeException("bu film zaten var");
        }
        //yoksa kaydedebilirsin
        return movieRepository.save(movie);
    }

    //2. tüm verileri getirme yetemneği
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    //3. silme işlemi
    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }
}













