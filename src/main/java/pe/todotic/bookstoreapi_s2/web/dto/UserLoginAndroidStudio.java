package pe.todotic.bookstoreapi_s2.web.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginAndroidStudio {
    @NotNull
    @Email
    private String email;
    //@NotBlank
    @NotNull
    @Size(min = 6)
    private String password;
}
