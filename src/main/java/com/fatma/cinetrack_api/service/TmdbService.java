package com.fatma.cinetrack_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.List;

@Service
public class TmdbService {

    //direkt bilgileri vermiyoruz ,
    // application.properties dosyasındaki bilgileri buraya çekiyoruz
    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    @Value("${tmdb.image.base-url}")
    private String imageBaseUrl;

    // Java'nın web tarayıcısı (Dışarıya istek atan araç)
    //Java kendi başına internette gezinemez. RestTemplate, Spring'in bize sunduğu,
    // arka planda bizim yerimize bir web sitesine giden,
    // oradaki veriyi okuyan ve geri getiren sanal bir web tarayıcısıdır (HTTP Client).
    private final RestTemplate restTemplate = new RestTemplate();

    public String getMoviePoster(String movieTitle) {
        try {
            // TMDB'ye soracağımız sorunun linkini hazırlıyoruz (Örn: ...?api_key=seninkod&query=Matrix)
            String url = apiUrl + "?api_key=" + apiKey + "&query=" + movieTitle;

            // Linke gidip gelen JSON (Metin) cevabını yakalıyoruz
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            // Gelen cevabın içinden "poster_path" (Afişin yolu) bilgisini cımbızla çekiyoruz
            //defensive programming
            if (response != null && response.containsKey("results")) {
                List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
                if (!results.isEmpty()) {
                    Map<String, Object> firstResult = results.get(0);
                    String posterPath = (String) firstResult.get("poster_path");

                    if (posterPath != null) {
                        return imageBaseUrl + posterPath; // Tam resim linkini oluşturup geri döndür
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Afiş çekilirken bir hata oluştu: " + e.getMessage());
        }
        return null; // Film bulunamazsa boş döner
    }
}