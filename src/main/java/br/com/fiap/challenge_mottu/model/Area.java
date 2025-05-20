package br.com.fiap.challenge_mottu.model;

import java.util.List;

import lombok.Data;

@Data
public class Area {
    
    private Long Id;

    private String Name;

    private Long PatioId;

    private Patio Patio;

    private List<Motorcycle> Motorcycles;

}
