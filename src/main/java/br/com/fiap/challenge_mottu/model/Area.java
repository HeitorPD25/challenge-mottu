package br.com.fiap.challenge_mottu.model;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Area {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patioId;

    private Patio patio;

    private List<Motorcycle> motorcycles;

}
