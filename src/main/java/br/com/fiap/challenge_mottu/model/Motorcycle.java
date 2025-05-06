package br.com.fiap.challenge_mottu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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

    @Id
    @Pattern(regexp = "^[A-Z]{3}-\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$", message = "Placa inválida")
    private String license_plate;

    @NotBlank
    private String model;

    @NotBlank
    private String status;

}
