package hello.itemservice.user.repository;

import hello.itemservice.user.domain.User;
import hello.itemservice.user.domain.UserType;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

//사용 x
//@Repository
@Slf4j
public class MemoryUserRepository{


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


    public void authorize(Long id) {
        User user = findById(id);
        user.setType(UserType.USER);
    }


    public void delete(Long id) {
        store.remove(id);
    }
}
