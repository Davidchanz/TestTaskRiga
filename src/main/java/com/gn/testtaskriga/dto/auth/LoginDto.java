package com.gn.testtaskriga.dto.auth;

import com.gn.testtaskriga.validation.annotation.ValidPassword;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginDto {
    @NotNull(message = "Login must not be null!")
    @Size(min = 3, max = 20, message = "Login should be between 3 and 20 symbols!")
    private String login;

    @NotNull(message = "Password must not be null!")
    @ValidPassword
    private String password;
}
