package br.com.fiap.challenge_mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge_mottu.model.entity.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long>{
    
}