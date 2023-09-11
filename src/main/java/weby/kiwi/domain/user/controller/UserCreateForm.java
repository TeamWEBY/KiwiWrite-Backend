package weby.kiwi.domain.user.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserCreateForm {
    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 필명은 필수항목입니다.")
    private String userName;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String passwd1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String passwd2;
}
