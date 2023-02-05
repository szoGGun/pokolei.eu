package eu.przemyslawrutkowski.pokolei.controller;

import eu.przemyslawrutkowski.pokolei.entity.Locomotive;
import eu.przemyslawrutkowski.pokolei.errors.ResourceNotFoundException;
import eu.przemyslawrutkowski.pokolei.repository.LocomotiveRepository;
import eu.przemyslawrutkowski.pokolei.repository.TrainLocomotiveOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LocomotiveController {

    private final LocomotiveRepository locomotiveRepository;
    private final TrainLocomotiveOrderRepository trainLocomotiveOrderRepository;

    @GetMapping("/locomotives")
    public List<Locomotive> getAllLocomotives() {
        return locomotiveRepository.findAll();
    }

    @GetMapping("/locomotives/get/{id}")
    public ResponseEntity<Locomotive> getLocomotiveById(@PathVariable(value = "id") Long locomotiveId) {
        Locomotive locomotive = locomotiveRepository.findById(locomotiveId)
                .orElseThrow(() -> new ResourceNotFoundException("Locomotive not found with id: " + locomotiveId));
        return ResponseEntity.ok().body(locomotive);
    }

    @PutMapping("/locomotives/update/{id}")
    public ResponseEntity<Locomotive> updateLocomotive(@PathVariable Long id,
                                         @RequestBody Locomotive editLocomotiveData) {
        Locomotive locomotive = locomotiveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locomotive not found with id: " + id));

        locomotive.setName(editLocomotiveData.getName());
        locomotive.setWeight(editLocomotiveData.getWeight());
        locomotive.setDrivingSpeed(editLocomotiveData.getDrivingSpeed());

        Locomotive updatedLocomotive = locomotiveRepository.save(locomotive);
        return ResponseEntity.ok(updatedLocomotive);
    }

    @DeleteMapping("/locomotives/delete/{id}")
    public ResponseEntity<Void> deleteLocomotive(@PathVariable Long id) {
        Locomotive locomotive = locomotiveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locomotive not found with id: " + id));

        trainLocomotiveOrderRepository.deleteAllInBatch(locomotive.getTrainLocomotiveOrders());
        locomotiveRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
