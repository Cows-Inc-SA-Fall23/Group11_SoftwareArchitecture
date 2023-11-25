package madasi.outboundSystem.service;
import org.springframework.data.repository.CrudRepository;

import madasi.outboundSystem.model.Setting;

public interface SettingRepository extends CrudRepository<Setting, Integer> {
}
