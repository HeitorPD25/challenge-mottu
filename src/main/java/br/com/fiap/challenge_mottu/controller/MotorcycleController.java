package br.com.fiap.challenge_mottu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.challenge_mottu.model.Motorcycle;
import br.com.fiap.challenge_mottu.repository.MotorcycleRpository;

@RestController
@RequestMapping("/motorcycles")
public class MotorcycleController {

    @Autowired
    private MotorcycleRpository repository;
    
    @GetMapping // Get All
    public List<Motorcycle> index(){
        return repository.findAll();
    }

    @GetMapping("{license_plate}") // Get By ID (No caso o ID é a Placa do veículo)
    public ResponseEntity<Motorcycle> get(@PathVariable String license_plate){
        System.out.println("Buscando Moto " + license_plate);
        return ResponseEntity.ok(getMoto(license_plate));
    }

    @PostMapping // Criar Moto
    @ResponseStatus(code = HttpStatus.CREATED)
    public Motorcycle create(@RequestBody Motorcycle moto){
        System.out.println("Cadastrando Moto " + moto.getLicense_plate());
        return repository.save(moto);
    }

    @PutMapping("{license_plate}") // Editar moto
    public ResponseEntity<Motorcycle> update(@PathVariable String license_plate, @RequestBody Motorcycle moto){
        System.out.println("Atualizando Moto " + license_plate + " com " + moto);

        getMoto(license_plate);
        repository.save(moto);
        return ResponseEntity.ok(moto);
    }

    @DeleteMapping("{license_plate}") // Deleta Motocicleta
    public ResponseEntity<Motorcycle> delete(@PathVariable String license_plate){
        System.out.println("Deletando a Moto " + license_plate);
        repository.delete(getMoto(license_plate));
        return ResponseEntity.noContent().build();
    }

    // Método que confere se determinada moto existe no banco de dados
    // Os métodos GetById, Put e Delete o utilizam
    private Motorcycle getMoto(String license_plate){
        return repository.findById(license_plate)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada.")
                );
    }



}
