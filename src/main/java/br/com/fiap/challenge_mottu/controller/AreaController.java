package br.com.fiap.challenge_mottu.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.challenge_mottu.model.Area;
import br.com.fiap.challenge_mottu.repository.AreaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("areas")
@Slf4j
public class AreaController {
    
    @Autowired
    private AreaRepository repository;

    @GetMapping
    @Operation(summary = "Listar Áreas", 
               description = "Retorna um array com todas as Áreas")
    @Cacheable("areas")
    public List<Area> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Listar Áreas", 
               description = "Busca uma Área pelo ID",
               responses = @ApiResponse(responseCode = "404", 
               description = "Área não encontrada!"))
    @Cacheable("areas")
    public ResponseEntity<Area> get(@PathVariable Long id) {
        log.info("Buscando categoria " + id);
        return ResponseEntity.ok(getArea(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma Área", 
               description = "Cadastra uma nova Área",
               responses = @ApiResponse(responseCode = "400", 
               description = "Erro de Validação!"))
    @CacheEvict(value = "areas", allEntries = true)
    public Area create(@RequestBody @Valid Area area){
        log.info("Cadastrando Area " + area.getName());
        return repository.save(area);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar uma Área", 
            description = "Atualiza uma Área pelo ID",
            responses = @ApiResponse(responseCode = "404", 
            description = "Área não encontrada!"))
    public ResponseEntity<Area> update(@PathVariable Long id, @RequestBody @Valid Area area){
        log.info("Atualizando categoria " + id + " com " + area);

        var oldArea = getArea(id);
        BeanUtils.copyProperties(area, oldArea, "id", "user");
        repository.save(oldArea);
        return ResponseEntity.ok(oldArea);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar uma Área", 
            description = "Deleta uma Área pelo ID",
            responses = @ApiResponse(responseCode = "404", 
            description = "Moto não encontrada!"))
    public ResponseEntity<Area> delete(@PathVariable Long id){
        log.info("Deletando categoria " + id);
        repository.delete(getArea(id));
        return ResponseEntity.noContent().build();
    }

    private Area getArea(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada."));
    }
        
}
