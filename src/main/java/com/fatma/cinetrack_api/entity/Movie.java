package com.fatma.cinetrack_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;


@Entity//veritabanı olduğunu gösterir

//3 lombok şapkası
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//primary key


    //filmin adı kesinlikle boş bırakılamaz kalkanı:
    @NotBlank(message = "Film adı boş bırakılamaz veya sadece boşluklardan oluşamaz!")
    private String title;

    @NotBlank(message = "Yönetmen ismi boş bırakılamaz veya sadece boşluklardan oluşamaz!")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Yönetmen ismi rakam içeremez, sadece harflerden oluşmalıdır!")
    private String director;


    //filmin çıkış yılı
    private Integer releaseYear;

    private Double rating;



}
