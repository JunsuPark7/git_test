package hello.itemservice.user.domain;


import hello.itemservice.user.repository.MemoryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemoryUserRepository memoryUserRepository;

    /**
     * @return null이면 로그인 실패.
     */
    public User login(String loginId,String password){
        return memoryUserRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        // optional 객체에 값이 비어 있다면 null 값을 반환함.
    }




}
