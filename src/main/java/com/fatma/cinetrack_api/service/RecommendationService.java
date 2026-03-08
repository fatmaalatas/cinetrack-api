package com.fatma.cinetrack_api.service;

import com.fatma.cinetrack_api.entity.Movie;
import com.fatma.cinetrack_api.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//beyin:service
@Service
@RequiredArgsConstructor//constructor enjection (daha güvenli)
public class RecommendationService {


    private final MovieRepository movieRepository;//servisimizn veritabanına bağlanabilmesi için buna ihtiyacı vardır
    //sınıfları birbirine sıkı sıkıya bağlamıyoruz
    //bana hafızadaki hazır repository nesnesini buraya tak/enjecte et diyoruz

    //güvenli veri çekme fail-fast prensibi
    public List<Movie> getRecommendations(Long watchedMovieId) {
        // 1. Kullanıcının izlediği filmi bul
        //veritabanına gidip , kullanıcının gönderdiği ID numarasına sahip filmi ararız
        Movie watchedMovie = movieRepository.findById(watchedMovieId)
                .orElseThrow(() -> new RuntimeException("Film bulunamadı!"));


        //Neden orElseThrow Kullanıyoruz?:
        // Ya kullanıcı (veya arayüzdeki bir hata) veritabanında olmayan "999" numaralı
        // bir ID gönderirse ne olur? Veritabanı boş (Null) döner.
        // Eğer biz bu boşluğun önlemini almazsak, bir alt satırda NullPointerException
        // hatası alırız ve tüm sunucu çökebilir.
        // orElseThrow bize şunu sağlar:
        // "Eğer film yoksa hiç aşağı satırlara inip vakit kaybetme,
        // anında istisna (Exception) fırlat ve işlemi güvenli bir şekilde iptal et."
        // Buna yazılımda Fail-Fast (Hızlı Hata Ver) prensibi denir.


        // 2. Bu filmin türünü (genre) öğren
        String targetGenre = watchedMovie.getGenre();

        // 3. Aynı türdeki diğer tüm filmleri veritabanından çek
        List<Movie> allMoviesInGenre = movieRepository.findByGenre(targetGenre);


        // 4. ML Mantığı: Kullanıcının zaten izlediği filmi listeden çıkar ve kalanları öner
        //Stream API  (Declarative Programming)

        return allMoviesInGenre.stream()//listeyi veri akışına dönüştür
                .filter(movie -> !movie.getId().equals(watchedMovieId))//banttan geçen filmle kullanıcının tıkladığı aynı mı?
                //eşit değilse bandı geçmesine izin ver
                //eşitse kullanıcı bu filmi zaten izlemiş demektir
                .collect(Collectors.toList());//kutuya koyup paketleme, işimiz bitti biz de tekrar listeye cevirip return ettik
    }
}