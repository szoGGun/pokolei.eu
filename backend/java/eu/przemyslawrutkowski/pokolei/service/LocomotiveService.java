package eu.przemyslawrutkowski.pokolei.service;

import eu.przemyslawrutkowski.pokolei.entity.Car;
import eu.przemyslawrutkowski.pokolei.entity.Locomotive;
import eu.przemyslawrutkowski.pokolei.repository.LocomotiveRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LocomotiveService{

    private LocomotiveRepository locomotiveRepository;

    public LocomotiveService(LocomotiveRepository locomotiveRepository){
        this.locomotiveRepository = locomotiveRepository;
    }

    public Locomotive getLocomotiveById(long id) {
        return locomotiveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " not found"));
    }
}
