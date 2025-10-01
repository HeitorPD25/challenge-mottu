package br.com.fiap.challenge_mottu.controller.web;

import br.com.fiap.challenge_mottu.repository.MotorcycleRepository;
import br.com.fiap.challenge_mottu.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        long freeMotorcycles = motorcycleRepository.countByStatus(br.com.fiap.challenge_mottu.model.enums.Status.Livre);
        long maintenanceMotorcycles = motorcycleRepository.countByStatus(br.com.fiap.challenge_mottu.model.enums.Status.Manutenção);
        long rentedMotorcycles = motorcycleRepository.countByStatus(br.com.fiap.challenge_mottu.model.enums.Status.Alugado);

        long totalMotorcycles = motorcycleRepository.count();
        long totalAreas = areaRepository.count();

        double utilizationRate = 0;
        if (totalMotorcycles > 0) {
            utilizationRate = ((double) rentedMotorcycles / totalMotorcycles) * 100;
        }

        model.addAttribute("freeMotorcycles", freeMotorcycles);
        model.addAttribute("maintenanceMotorcycles", maintenanceMotorcycles);
        model.addAttribute("rentedMotorcycles", rentedMotorcycles);
        model.addAttribute("totalMotorcycles", totalMotorcycles);
        model.addAttribute("totalAreas", totalAreas);
        model.addAttribute("utilizationRate", String.format("%.2f", utilizationRate) + "%");

        return "dashboard";
    }
}
