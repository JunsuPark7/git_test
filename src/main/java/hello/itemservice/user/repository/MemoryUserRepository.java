package hello.itemservice.user.repository;

import hello.itemservice.user.domain.User;
import hello.itemservice.user.domain.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
@Slf4j
public class MemoryUserRepository implements UserRepository{


    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;


    public User save(User user){
        user.setId(++sequence);
        log.info("save: user={}", user);
        store.put(user.getId(),user);
        return user;
    }

    public User findById(Long id){
        return store.get(id);
    }

    public Optional<User> findByLoginId(String loginId){
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<User> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public void authorize(Long id) {
        User user = findById(id);
        user.setType(UserType.ADMIN);
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
