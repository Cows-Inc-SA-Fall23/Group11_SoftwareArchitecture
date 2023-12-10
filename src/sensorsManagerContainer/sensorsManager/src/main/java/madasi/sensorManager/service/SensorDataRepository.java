package madasi.sensorManager.service;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import madasi.sensorManager.model.SensorData;

public interface SensorDataRepository extends CrudRepository<SensorData, Integer> {

	List<SensorData> findByTimestampBefore(Timestamp currentTime);
}
