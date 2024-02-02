package hello.itemservice.user.repository;

import hello.itemservice.member.domain.Member;
import hello.itemservice.member.repository.DBMemberRepository;
import hello.itemservice.user.domain.User;
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
        log.info("save: user={}", user);
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
}
