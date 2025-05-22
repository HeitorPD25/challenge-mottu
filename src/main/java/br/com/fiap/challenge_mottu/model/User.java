package br.com.fiap.challenge_mottu.model;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^([A-Z][a-z]*)(\s[A-Z][a-z]*)*$",
             message = "O Nome deve começar com letra maiúscula, e conter apenas letras.")
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&\\-+(){}\\[\\]^~#_.,;:])[A-Za-z\\d@$!%*?&\\-+(){}\\[\\]^~#_.,;:]{8,}$",
             message = "Senha deve ter no mínimo 8 caracteres, com ao menos 1 maiúscula, 1 minúscula, 1 número e 1 caractere especial")
    private String password;

    private List<Patio> patios;
}
