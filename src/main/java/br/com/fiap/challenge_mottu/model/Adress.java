package br.com.fiap.challenge_mottu.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Adress {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String Street;

    private Long Number;

    private String Neighborhood;

    private String State;

    private String Cep;

    private String Complement;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdPatio;

}
