package madasi.feedSystem.controller;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import madasi.feedSystem.model.Silo;
import madasi.feedSystem.service.SiloRepository;

@RestController
@RequestMapping("/silosModel")
public class SiloController {
	Logger logger = LoggerFactory.getLogger(SiloController.class);

	@Autowired
    private SiloRepository siloRepository;
	
	@GetMapping
    public ResponseEntity<List<Silo>> getAllSilos() {
        List<Silo> silos = new ArrayList<>();
        
        for(Silo s : siloRepository.findAll()) {
			logger.info(s.getName());
			silos.add(s);
		}
        
        return ResponseEntity.ok(silos);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSilo(@RequestBody Silo silo) {
        // Here, we call the service method to create a new silo
        Silo createdSilo = siloRepository.save(silo);
        return ResponseEntity.ok(createdSilo);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSilo(@PathVariable Integer id) {
        // Call the service method to delete the silo
        siloRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
