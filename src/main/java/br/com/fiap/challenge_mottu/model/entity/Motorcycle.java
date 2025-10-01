package br.com.fiap.challenge_mottu.model.entity;

import br.com.fiap.challenge_mottu.model.enums.Model;
import br.com.fiap.challenge_mottu.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z]{3}-\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$", message = "Placa inválida")
    private String plate;

    @NotNull(message = "Cor é obrigatório")
    private String color;

    @NotNull(message = "Model é obrigatório")
    private Model model;

    @NotNull(message = "Status é obrigatório")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "areaId")
    private Area area;

}
