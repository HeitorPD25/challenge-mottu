package br.com.fiap.challenge_mottu.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.challenge_mottu.model.dto.MotorcycleDTO;
import br.com.fiap.challenge_mottu.model.entity.Motorcycle;
import br.com.fiap.challenge_mottu.model.enums.Status;
import br.com.fiap.challenge_mottu.repository.MotorcycleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/motorcycles")
@Slf4j
public class MotorcycleController {

    @Autowired
    private MotorcycleRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public MotorcycleDTO toDTO(Motorcycle motorcycle) {
        return modelMapper.map(motorcycle, MotorcycleDTO.class);
    }

    public Motorcycle toEntity(MotorcycleDTO dto) {
        return modelMapper.map(dto, Motorcycle.class);
    }

    @GetMapping
    @Operation(summary = "Listar Motos", description = "Retorna um array com todas as Motos")
    @Cacheable("motorcycles")
    public List<Motorcycle> index(@RequestParam(required = false) Status status,
                                  @RequestParam(required = false) Long areaId) {
        if (status != null && areaId != null) {
            return repository.findByStatusAndAreaId(status, areaId);
        } else if (status != null) {
            return repository.findByStatus(status);
        } else if (areaId != null) {
            return repository.findByAreaId(areaId);
        }
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Moto pelo ID", responses = @ApiResponse(responseCode = "404", description = "Moto não encontrada!"))
    public ResponseEntity<Motorcycle> get(@PathVariable Long id) {
        return ResponseEntity.ok(getMoto(id));
    }

    @PostMapping
    @CacheEvict(value = "motorcycles", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    public Motorcycle create(@Valid @ModelAttribute Motorcycle moto) {
        return repository.save(moto);
    }

    @PutMapping("{id}")
    @CacheEvict(value = "motorcycles", allEntries = true)
    public ResponseEntity<Motorcycle> update(@PathVariable Long id, @Valid @ModelAttribute Motorcycle moto) {
        Motorcycle existing = getMoto(id);
        BeanUtils.copyProperties(moto, existing, "id");
        repository.save(existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "motorcycles", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.delete(getMoto(id));
        return ResponseEntity.noContent().build();
    }

    private Motorcycle getMoto(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada."));
    }
}
