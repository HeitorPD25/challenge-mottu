package br.com.fiap.challenge_mottu.controller.web;

import br.com.fiap.challenge_mottu.model.entity.Motorcycle;
import br.com.fiap.challenge_mottu.model.entity.Area;
import br.com.fiap.challenge_mottu.repository.MotorcycleRepository;
import br.com.fiap.challenge_mottu.repository.AreaRepository;
import br.com.fiap.challenge_mottu.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/web/motorcycles")
public class MotorcycleWebController {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping
    public String listMotorcycles(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long areaId,
            Model model) {
        
        List<Motorcycle> motorcycles;
        
        if (status != null && areaId != null) {
            motorcycles = motorcycleRepository.findByStatusAndAreaId(status, areaId);
        } else if (status != null) {
            motorcycles = motorcycleRepository.findByStatus(status);
        } else if (areaId != null) {
            Optional<Area> area = areaRepository.findById(areaId);
            motorcycles = area.map(Area::getMotorcycles).orElse(List.of());
        } else {
            motorcycles = motorcycleRepository.findAll();
        }

        model.addAttribute("motorcycles", motorcycles);
        model.addAttribute("areas", areaRepository.findAll());
        model.addAttribute("statusValues", Status.values());
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedAreaId", areaId);

        return "motorcycles/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("motorcycle", new Motorcycle());
        model.addAttribute("areas", areaRepository.findAll());
        model.addAttribute("statusValues", Status.values());
        model.addAttribute("modelValues", br.com.fiap.challenge_mottu.model.enums.Model.values());
        return "motorcycles/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Motorcycle> motorcycle = motorcycleRepository.findById(id);
        if (motorcycle.isEmpty()) {
            return "redirect:/motorcycles";
        }
        
        model.addAttribute("motorcycle", motorcycle.get());
        model.addAttribute("areas", areaRepository.findAll());
        model.addAttribute("statusValues", Status.values());
        model.addAttribute("modelValues", br.com.fiap.challenge_mottu.model.enums.Model.values());
        return "motorcycles/form";
    }

    @PostMapping
    public String saveMotorcycle(@ModelAttribute Motorcycle motorcycle, RedirectAttributes redirectAttributes) {
        try {
            motorcycleRepository.save(motorcycle);
            redirectAttributes.addFlashAttribute("successMessage", "Motocicleta salva com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar motocicleta: " + e.getMessage());
        }
        return "redirect:/motorcycles";
    }

    @GetMapping("/{id}/delete")
    public String deleteMotorcycle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            motorcycleRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Motocicleta excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir motocicleta: " + e.getMessage());
        }
        return "redirect:/motorcycles";
    }

    @GetMapping("/{id}")
    public String motorcycleDetails(@PathVariable Long id, Model model) {
        Optional<Motorcycle> motorcycle = motorcycleRepository.findById(id);
        if (motorcycle.isEmpty()) {
            return "redirect:/motorcycles";
        }
        
        model.addAttribute("motorcycle", motorcycle.get());
        return "motorcycles/details";
    }
}
