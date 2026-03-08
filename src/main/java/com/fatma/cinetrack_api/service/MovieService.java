package com.fatma.cinetrack_api.service;

import com.fatma.cinetrack_api.entity.Movie;
import com.fatma.cinetrack_api.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service //servis katmanındayız
@RequiredArgsConstructor //enjeksiyon şapkası
//Neden @Autowired yerine bunu seçtik?: @Autowired eski ve esnek bir yöntemdir.
// Ancak modern Java dünyasında  "Constructor Injection"
// (Yapıcı Metot Enjeksiyonu) altın standarttır.

public class MovieService {

    //depo görevlisi
    //Neden private final?:
    // final kelimesi Java'ya şunu söyler: "Bu depo görevlisi (movieRepository)
    // uygulama ayağa kalkarken bir kere tanımlanacak ve uygulama kapanana kadar asla değiştirilemeyecek.
    // " Bu, uygulamanı hafıza sızıntılarına ve güvenlik açıklarına karşı zırhlı hale getirir.
    // @RequiredArgsConstructor şapkası da arka planda o final değişkenler için otomatik bir bağlantı kablosu (Constructor) yazar.
    // Kodun çok daha temiz ve profesyonel görünmesini sağlar.
    private final MovieRepository movieRepository;

    // Dış dünyadan afiş getiren ajanımız
    private final TmdbService tmdbService;


    // 1. Veri Ekleme (TMDB Zekası Eklenmiş Hali)
    public Movie addMovie(Movie movie){
        // a. Önce film veritabanımızda var mı diye kontrol et
        if(movieRepository.existsByTitle(movie.getTitle())){
            throw new RuntimeException("Bu film zaten var");
        }

        // b. Film yoksa kaydetmeden HEMEN ÖNCE TMDB Ajanını gönder ve afişi bul!
        String posterUrl = tmdbService.getMoviePoster(movie.getTitle());

        if (posterUrl != null) {
            movie.setPosterUrl(posterUrl); // TMDB'den afiş bulduysa linki yapıştır
        } else {
            // Eğer internette bulamazsa, varsayılan havalı bir sinema arka planı koy
            movie.setPosterUrl("https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
        }

        // c. Kapağı da eklenmiş tam haliyle veritabanına kaydet!
        return movieRepository.save(movie);
    }

    // 2. Tüm verileri getirme yeteneği
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    // 3. Silme işlemi
    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }
}













