package br.com.fiap.challenge_mottu.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String license_plate;
    
    private String color;
    
    private LocalDate manufacture_year; //Definir entre LocalDate ou Integer
    
    private String model;
    
    private String status;
    
}
