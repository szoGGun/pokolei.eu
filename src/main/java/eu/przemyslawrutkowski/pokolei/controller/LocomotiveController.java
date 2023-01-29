package eu.przemyslawrutkowski.pokolei.controller;

import eu.przemyslawrutkowski.pokolei.entity.Locomotive;
import eu.przemyslawrutkowski.pokolei.repository.LocomotiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080/")
public class LocomotiveController {

    private final LocomotiveRepository locomotiveRepository;

    @GetMapping("/locomotives")
    public List<Locomotive> getAllLocomotives() {
        return locomotiveRepository.findAll();
    }
}
