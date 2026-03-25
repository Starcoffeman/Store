package org.example.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterForm {
    @NotEmpty(message = "Логин не может быть пустым")
    private String username;

    @NotEmpty(message = "Имя не может быть пустым")
    private String name;

    @NotEmpty(message = "Фамилия не может быть пустым")
    private String surname;

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @NotEmpty(message = "Телефон не может быть пустым")
    private String phone;

    @NotEmpty(message = "Адрес не может быть пустым")
    private String address;

    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Некорректный email")
    private String email;
}
