package eu.przemyslawrutkowski.pokolei;

import eu.przemyslawrutkowski.pokolei.controller.LocomotiveController;
import eu.przemyslawrutkowski.pokolei.entity.Locomotive;
import eu.przemyslawrutkowski.pokolei.repository.LocomotiveRepository;
import eu.przemyslawrutkowski.pokolei.repository.TrainLocomotiveOrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocomotiveControllerTest {

    @Mock
    private LocomotiveRepository locomotiveRepository;

    @Mock
    private TrainLocomotiveOrderRepository trainLocomotiveOrderRepository;

    @InjectMocks
    private LocomotiveController locomotiveController;

    private Locomotive locomotive;
    private List<Locomotive> locomotives;

    @Before
    public void setUp() {
        locomotive = new Locomotive();
        locomotive.setLocomotiveId(1L);
        locomotive.setName("Test Locomotive");
        locomotive.setDrivingSpeed("200 km/h");
        locomotive.setWeight("400 kg");

        locomotives = new ArrayList<>();
        locomotives.add(locomotive);
    }

    @Test
    public void testGetAllLocomotives() {
        when(locomotiveRepository.findAll()).thenReturn(locomotives);

        List<Locomotive> result = locomotiveController.getAllLocomotives();

        assertEquals(locomotives, result);
    }

    @Test
    public void testGetLocomotiveById() {
        when(locomotiveRepository.findById(1L)).thenReturn(Optional.of(locomotive));

        ResponseEntity<Locomotive> result = locomotiveController.getLocomotiveById(1L);

        assertEquals(ResponseEntity.ok().body(locomotive), result);
    }

    @Test
    public void testUpdateLocomotive() {
        when(locomotiveRepository.findById(1L)).thenReturn(Optional.of(locomotive));
        when(locomotiveRepository.save(locomotive)).thenReturn(locomotive);

        ResponseEntity<Locomotive> result = locomotiveController.updateLocomotive(1L, locomotive);

        assertEquals(ResponseEntity.ok(locomotive), result);
    }

    @Test
    public void testDeleteLocomotives() {
        when(locomotiveRepository.findById(1L)).thenReturn(Optional.of(locomotive));

        ResponseEntity<Void> result = locomotiveController.deleteLocomotive(1L);

        verify(trainLocomotiveOrderRepository).deleteAllInBatch(locomotive.getTrainLocomotiveOrders());
        verify(locomotiveRepository).deleteById(1L);
        assertEquals(ResponseEntity.ok().build(), result);
    }
}
