package madasi.feedSystem.service;
import org.springframework.data.repository.CrudRepository;

import madasi.feedSystem.model.Setting;

public interface SettingRepository extends CrudRepository<Setting, Integer> {
}
