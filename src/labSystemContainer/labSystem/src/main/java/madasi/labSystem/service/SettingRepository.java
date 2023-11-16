package madasi.labSystem.service;
import org.springframework.data.repository.CrudRepository;

import madasi.labSystem.model.Setting;

public interface SettingRepository extends CrudRepository<Setting, Integer> {
}
