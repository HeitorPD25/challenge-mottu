package br.com.fiap.challenge_mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge_mottu.model.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Long>{
    
}
