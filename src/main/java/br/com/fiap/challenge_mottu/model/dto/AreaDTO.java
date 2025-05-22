package br.com.fiap.challenge_mottu.model.dto;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AreaDTO {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome não pode estar em branco.")
    private String name;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "patioId") // o nome da coluna FK no banco
    // private Patio patio;

    @OneToMany
    private List<Long> motorcycleIds;

}