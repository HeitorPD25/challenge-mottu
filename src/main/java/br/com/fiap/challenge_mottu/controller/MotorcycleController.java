package br.com.fiap.challenge_mottu.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.challenge_mottu.model.dto.MotorcycleDTO;
import br.com.fiap.challenge_mottu.model.entity.Motorcycle;
import br.com.fiap.challenge_mottu.repository.MotorcycleRpository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/motorcycles")
@Slf4j
public class MotorcycleController {

    @Autowired
    private MotorcycleRpository repository;

// ------------------------------ Model Mapper ------------------------------
    @Autowired
    private ModelMapper modelMapper;

    public MotorcycleDTO toDTO(Motorcycle motorcycle) {
        return modelMapper.map(motorcycle, MotorcycleDTO.class);
    }

    public Motorcycle toEntity(MotorcycleDTO dto) {
        return modelMapper.map(dto, Motorcycle.class);
    }
// --------------------------------------------------------------------------

    @GetMapping
    @Operation(summary = "Listar Motos", 
            description = "Retorna um array com todas as Motos")
    @Cacheable("motorcycles")
    public List<Motorcycle> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Listar Motos", 
               description = "Busca uma Moto pelo ID",
               responses = @ApiResponse(responseCode = "404", 
               description = "Moto não encontrada!"))
    @Cacheable("motorcycles")
    public ResponseEntity<Motorcycle> get(@PathVariable Long id) {
        System.out.println("Buscando Moto " + id);
        return ResponseEntity.ok(getMoto(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma Moto", 
            description = "Cadastra uma nova Moto",
            responses = @ApiResponse(responseCode = "400", 
            description = "Erro de Validação!"))
    @CacheEvict(value = "motorcycles", allEntries = true)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Motorcycle create(@Valid @RequestBody Motorcycle moto) {
        System.out.println("Cadastrando Moto " + moto.getLicense_plate());
        return repository.save(moto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar uma Moto", 
            description = "Atualiza uma Moto pelo ID",
            responses = @ApiResponse(responseCode = "404", 
            description = "Moto não encontrada!"))
    public ResponseEntity<Motorcycle> update(@Valid @RequestBody Motorcycle moto, @PathVariable Long id) {
        log.info("Atualizando área " + id + " com " + moto);

        var oldMoto = getMoto(id);
        BeanUtils.copyProperties(moto, oldMoto, "id", "user");
        repository.save(oldMoto);
        return ResponseEntity.ok(oldMoto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar uma Moto", 
            description = "Deleta uma Moto pelo ID",
            responses = @ApiResponse(responseCode = "404", 
            description = "Moto não encontrada!"))
    public ResponseEntity<Motorcycle> delete(@Valid @PathVariable Long id) {
        System.out.println("Deletando a Moto " + id);
        repository.delete(getMoto(id));
        return ResponseEntity.noContent().build();
    }

    // Método que confere se determinada moto existe no banco de dados
    // Os métodos GetById, Put e Delete o utilizam
    private Motorcycle getMoto(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada."));
    }

}
