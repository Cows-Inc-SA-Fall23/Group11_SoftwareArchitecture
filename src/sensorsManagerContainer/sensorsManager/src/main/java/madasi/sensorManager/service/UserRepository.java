package madasi.sensorManager.service;
import org.springframework.data.repository.CrudRepository;

import madasi.sensorManager.model.User;
//
public interface UserRepository extends CrudRepository<User, Integer> {
}