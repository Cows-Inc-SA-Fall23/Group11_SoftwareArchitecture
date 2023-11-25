package madasi.inboundSystem.service;
import org.springframework.data.repository.CrudRepository;

import madasi.inboundSystem.model.Setting;

public interface SettingRepository extends CrudRepository<Setting, Integer> {
}
