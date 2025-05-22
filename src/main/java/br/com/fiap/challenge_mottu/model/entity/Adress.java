package br.com.fiap.challenge_mottu.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adress {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da rua é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome da rua deve ter entre 3 e 100 caractéres.")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\sº°\\-.,]+$",
             message = "O nome da rua deve conter apenas letras, números e caracteres como º, -, . e ,")
    private String street;

    @NotBlank(message = "O endereço não pode estar em branco.")
    @Positive(message = "O número deve ser maior que zero.")
    private Long number;

    @NotBlank(message = "O Bairro não pode estar em branco.")
    private String neighborhood;

    @NotBlank(message = "O Estado não pode estar em branco.")
    @Size(min = 3, max = 100, message = "Escreva o nome completo do estado.")
    private String state;

    @NotBlank(message = "O CEP não pode estar em branco.")
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "O CEP deve estar no formato 12345-678 ou 12345678")
    private String cep;

    @Size(max = 50, message = "O complemento pode ter no máximo 50 caracteres")
    private String complement;

    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Patio patio;

}
