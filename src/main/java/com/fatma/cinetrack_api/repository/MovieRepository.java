package com.fatma.cinetrack_api.repository;

import com.fatma.cinetrack_api.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring Boot'a "Bu sınıf bizim Depo Görevlimizdir" der.
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(@NotBlank(message = "Film adı boş bırakılamaz veya sadece boşluklardan oluşamaz!") String title);
    //depocuya filmin adına göre arama yap var mı yok mu (boolean ) bana söyle diyen öethod

    // Şimdilik içinin bomboş kalması normal, sakın korkma!
    // JpaRepository bizim için save(), findById(), deleteById() gibi tüm metotları arka planda otomatik yazdı bile.

}