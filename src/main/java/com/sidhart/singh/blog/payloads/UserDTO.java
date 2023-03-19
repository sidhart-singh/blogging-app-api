package com.sidhart.singh.blog.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
//    Properties we can expose to the apis
//    Using ValidationAnnotations to be used by the GlobalExceptionHandler
    private int userId;
    @NotEmpty
    @Size(min = 4, message = "Username must be 4 characters")
    private String name;
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "The password must contain at least one lowercase character," +
                    " one uppercase character, one digit, one special character," +
                    " and a length between 8 to 20")
    private String password;
    @NotEmpty
    private String about;
}
