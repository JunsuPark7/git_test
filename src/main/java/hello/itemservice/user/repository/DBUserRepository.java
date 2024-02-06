package hello.itemservice.user.repository;

import hello.itemservice.user.domain.User;
import hello.itemservice.user.domain.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@Transactional
public class DBUserRepository implements UserRepository{

    private final EntityManager em;

    public DBUserRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        log.info("save(회원가입): user={}", user);
        User savedUser = em.find(User.class, user.getId());
        return savedUser;
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class,id);
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public void authorize(Long id) {
        User findUser = em.find(User.class, id);
        findUser.setType(UserType.USER);
    }

    @Override
    public void changePassword(Long id,String password) {
        User user = em.find(User.class, id);
        user.setPassword(password);
    }

    @Override
    public void delete(Long id) {
        User findUser = em.find(User.class, id);
        em.remove(findUser);
    }
}
