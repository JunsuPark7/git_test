package hello.itemservice.user.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {

    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

}
