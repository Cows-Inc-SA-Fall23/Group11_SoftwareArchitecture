package madasi.feedSystem.service;
import org.springframework.data.repository.CrudRepository;

import madasi.feedSystem.model.User;
//
public interface UserRepository extends CrudRepository<User, Integer> {
}
