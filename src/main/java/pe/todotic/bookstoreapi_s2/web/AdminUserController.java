package pe.todotic.bookstoreapi_s2.web;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import pe.todotic.bookstoreapi_s2.model.Book;
import pe.todotic.bookstoreapi_s2.web.dto.UserDTO;
import pe.todotic.bookstoreapi_s2.model.User;
import pe.todotic.bookstoreapi_s2.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class AdminUserController {

	private final ModelMapper modelMapper=new ModelMapper();
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/list")
	public List<User> list(){
		List<User> users= userRepository.findAll().stream().collect(Collectors.toList());
		return users;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User create(@Validated @RequestBody UserDTO userDTO) {
		User user=modelMapper.map(userDTO, User.class);
		user.setFullName(userDTO.getFirstName()+" "+userDTO.getLastName());
		return userRepository.save(user);
	}
	@GetMapping("{id}")
	public User finByID(@PathVariable Integer id){

		return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("{id}")
	public User update( @PathVariable Integer id,
						@Validated @RequestBody UserDTO userDTO) {
	
		User user=userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		user.setFullName(userDTO.getFirstName()+" "+userDTO.getLastName());
		modelMapper.map(userDTO,user);
		userRepository.save(user);
	return user;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        userRepository.deleteById(user.getId());
    }
	
	@GetMapping("/find")
	public List<User> findByName(@RequestParam String fullName){
		return userRepository.findByName(fullName);
	}

	@GetMapping
	Page<User> paginate(
			/* TODO 18: sobreescribir la config. por defecto:
			 *   ordenar por el título de forma ascendente
			 *   y con un tamaño de 5 elementos por página. */
			@PageableDefault(sort = "title",direction = Sort.Direction.ASC, size = 5) Pageable pageable
	) {
		// TODO 17: retornar la lista de USUARIOS de forma paginada
		return userRepository.findAll(pageable);
	}
}
