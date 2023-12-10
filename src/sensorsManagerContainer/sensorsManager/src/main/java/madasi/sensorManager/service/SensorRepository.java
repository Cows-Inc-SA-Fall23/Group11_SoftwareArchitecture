package madasi.sensorManager.service;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import madasi.sensorManager.model.Sensor;

public interface SensorRepository extends CrudRepository<Sensor, Integer> {
    Optional<Sensor> findByMac(String mac);
}
