package br.com.fiap.challenge_mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge_mottu.model.Patio;

public interface PatioRepository extends JpaRepository<Patio, Long>{
    
}
