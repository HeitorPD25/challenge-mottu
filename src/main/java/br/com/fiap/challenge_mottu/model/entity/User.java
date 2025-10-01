package br.com.fiap.challenge_mottu.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome não pode estar em branco.")
    // @Pattern(regexp = "^([A-Z][a-z]*)(\s[A-Z][a-z]*)*$",
    //          message = "O Nome deve começar com letra maiúscula, e conter apenas letras.")
    private String name;

    @NotBlank(message = "O Email não pode estar em branco.")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "A Senha não pode estar em branco.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&\\-+(){}\\[\\]^~#_.,;:])[A-Za-z\\d@$!%*?&\\-+(){}\\[\\]^~#_.,;:]{8,}$",
             message = "Senha deve ter no mínimo 8 caracteres, com ao menos 1 maiúscula, 1 minúscula, 1 número e 1 caractere especial")
    private String password;

    @Builder.Default
    @Column(nullable = false)
    private String role = "USER";

    @OneToMany
    private List<Patio> patios;
}
