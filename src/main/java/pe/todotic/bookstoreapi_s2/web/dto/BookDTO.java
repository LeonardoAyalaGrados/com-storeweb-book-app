package pe.todotic.bookstoreapi_s2.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDTO {
	
	@NotNull
	@Size(min = 3,max = 100)
	private String title;
	
	@NotNull
	@Min(0)
	private Float price;
	
	@NotNull
	//@Pattern(regexp = "[A-Z0-9-]+")
	private String slug;

	@NotBlank
	private String coverPath;

	@NotBlank
	private String filePath;

	@NotBlank(message = "La descripcion es obligatoria")
	private String desc;
}
