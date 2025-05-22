package br.com.fiap.challenge_mottu.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Area {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome não pode estar em branco.")
    private String name;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "patioId") // o nome da coluna FK no banco
    // private Patio patio;

    @OneToMany
    private List<Motorcycle> motorcycles;

}
