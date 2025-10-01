package br.com.fiap.challenge_mottu.repository;

import br.com.fiap.challenge_mottu.model.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {
    
    // Buscar área pelo nome
    Optional<Area> findByName(String name);
}