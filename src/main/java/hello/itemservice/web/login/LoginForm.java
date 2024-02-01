package hello.itemservice.web.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data // lombok data => Getter, Setter, ToString ... 등등 종합선물세트.
public class LoginForm {

    //login Form에 들어온 데이터들.. DTO 역할

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
