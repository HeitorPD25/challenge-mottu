package br.com.fiap.challenge_mottu.model;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Patio {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[A-Z].*$")
    private String name;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adressId;

    private Adress adress;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private User user;

    private List<Area> areas;


}
