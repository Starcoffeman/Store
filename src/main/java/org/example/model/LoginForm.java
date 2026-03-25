package org.example.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    @NotEmpty(message = "Логин не может быть пустым")
    private String username;

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;
}
