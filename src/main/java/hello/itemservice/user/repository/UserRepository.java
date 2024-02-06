package hello.itemservice.user.repository;

import hello.itemservice.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    User findById(Long id);
    Optional<User> findByLoginId(String loginId);
    List<User> findAll();

    void authorize(Long id);

    void changePassword(Long id,String password);

    void delete(Long id);

}
