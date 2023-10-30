package madasi.controlPanel.service;
import madasi.controlPanel.model.User;
import org.springframework.data.repository.CrudRepository;
//
public interface UserRepository extends CrudRepository<User, Integer> {
}
