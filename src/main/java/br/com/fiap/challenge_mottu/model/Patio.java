package br.com.fiap.challenge_mottu.model;

import java.util.List;

import lombok.Data;

@Data
public class Patio {
    
    private Long Id;

    private String Name;

    private Long AdressId;

    private Adress Adress;

    private Long UserId;

    private User User;

    private List<Area> Areas;


}
