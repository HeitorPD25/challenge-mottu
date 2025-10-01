package br.com.fiap.challenge_mottu.controller;

import br.com.fiap.challenge_mottu.model.entity.Area;
import br.com.fiap.challenge_mottu.repository.AreaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaRepository repository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("areas", repository.findAll());
        return "area-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("area", new Area());
        return "area-form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Area area) {
        repository.save(area);
        return "redirect:/areas";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Area area = getArea(id);
        model.addAttribute("area", area);
        return "area-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Area area) {
        Area existing = getArea(id);
        BeanUtils.copyProperties(area, existing, "id");
        repository.save(existing);
        return "redirect:/areas";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repository.delete(getArea(id));
        return "redirect:/areas";
    }

    private Area getArea(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Área não encontrada"));
    }
}
