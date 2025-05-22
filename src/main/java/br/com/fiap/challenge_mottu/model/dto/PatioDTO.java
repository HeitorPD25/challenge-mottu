package br.com.fiap.challenge_mottu.model.dto;

import java.util.List;

import br.com.fiap.challenge_mottu.model.entity.Adress;
import br.com.fiap.challenge_mottu.model.entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PatioDTO {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome não pode estar em branco.")
    @Pattern(regexp = "^[A-Z].*$")
    private String name;

    @OneToOne
    @JoinColumn(name = "adressId")
    private Adress adress;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany
    private List<Long> areaIds;


}
