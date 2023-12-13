package madasi.controlPanel.service;
import org.springframework.data.repository.CrudRepository;

import madasi.controlPanel.model.Livestock;
//
public interface LivestockRepository extends CrudRepository<Livestock, Integer> {
}