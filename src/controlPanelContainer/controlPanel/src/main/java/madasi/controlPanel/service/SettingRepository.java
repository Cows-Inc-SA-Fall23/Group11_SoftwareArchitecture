package madasi.controlPanel.service;
import org.springframework.data.repository.CrudRepository;

import madasi.controlPanel.model.Setting;

public interface SettingRepository extends CrudRepository<Setting, Integer> {
}
