package eu.przemyslawrutkowski.pokolei.controller;

import eu.przemyslawrutkowski.pokolei.entity.Train;
import eu.przemyslawrutkowski.pokolei.repository.TrainRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TrainController {

    private final TrainRepository trainRepository;

    @GetMapping("/trains")
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @GetMapping("/trains/search")
    public List<Train> searchTrainsByName(@RequestParam(name = "searchTerm") String searchTerm) {

        List<Train> trains = new ArrayList<>();

        if (searchTerm != null) {
            trains.addAll(trainRepository.findByNumberOrName(searchTerm));
        }

        return trains;
    }
}
