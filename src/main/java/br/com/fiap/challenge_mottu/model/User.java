package br.com.fiap.challenge_mottu.model;

import java.util.List;

import lombok.Data;

@Data
public class User {
    
    private Long Id;

    private String Name;

    private String Email;

    private String Password;

    private List<Patio> Patios;
}
