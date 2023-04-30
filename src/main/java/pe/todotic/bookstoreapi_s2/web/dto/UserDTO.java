package pe.todotic.bookstoreapi_s2.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pe.todotic.bookstoreapi_s2.model.User;

@Data
public class UserDTO {

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min = 6)
	private String password;

	private User.Role role;

}
