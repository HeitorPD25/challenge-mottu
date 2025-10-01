package br.com.fiap.challenge_mottu.repository;

import br.com.fiap.challenge_mottu.model.entity.Motorcycle;
import br.com.fiap.challenge_mottu.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    
    // Filtra por status
    List<Motorcycle> findByStatus(Status status);

    // Filtra por área
    List<Motorcycle> findByAreaId(Long areaId);

    // Filtra por status e área
    List<Motorcycle> findByStatusAndAreaId(Status status, Long areaId);

    // Contar motos por status
    long countByStatus(Status status);
    
    // Buscar moto pela placa
    Optional<Motorcycle> findByPlate(String plate);
    
    // Buscar motos disponíveis em uma área específica
    @Query("SELECT m FROM Motorcycle m WHERE m.area.id = :areaId AND m.status = br.com.fiap.challenge_mottu.model.enums.Status.Livre")
    List<Motorcycle> findAvailableByAreaId(@Param("areaId") Long areaId);
}
