package br.com.fiap.challenge_mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge_mottu.model.Motorcycle;

public interface MotorcycleRpository extends JpaRepository<Motorcycle, Long> {
    
}
