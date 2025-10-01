package br.com.fiap.challenge_mottu.controller.web;

import br.com.fiap.challenge_mottu.model.entity.Area;
import br.com.fiap.challenge_mottu.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/web/areas")
public class AreaWebController {

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping
    public String listAreas(Model model) {
        model.addAttribute("areas", areaRepository.findAll());
        return "areas/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("area", new Area());
        return "areas/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Area> area = areaRepository.findById(id);
        if (area.isEmpty()) {
            return "redirect:/areas";
        }
        model.addAttribute("area", area.get());
        return "areas/form";
    }

    @PostMapping
    public String saveArea(@ModelAttribute Area area, RedirectAttributes redirectAttributes) {
        try {
            areaRepository.save(area);
            redirectAttributes.addFlashAttribute("successMessage", "Área salva com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar área: " + e.getMessage());
        }
        return "redirect:/areas";
    }

    @GetMapping("/{id}/delete")
    public String deleteArea(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            areaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Área excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir área: " + e.getMessage());
        }
        return "redirect:/areas";
    }
}