package hello.itemservice.user.domain;


import hello.itemservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * @return null이면 로그인 실패.
     */
    public User login(String loginId,String password) {
        return userRepository.findByLoginId(loginId)
                .filter(m -> passwordEncoder.matches(password, m.getPassword()))
                .orElse(null);
        // optional 객체에 값이 비어 있다면 null 값을 반환함.
    }

}
