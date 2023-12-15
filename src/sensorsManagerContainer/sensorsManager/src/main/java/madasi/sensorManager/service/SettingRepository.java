package madasi.sensorManager.service;
import org.springframework.data.repository.CrudRepository;

import madasi.sensorManager.model.Setting;

public interface SettingRepository extends CrudRepository<Setting, Integer> {
}
