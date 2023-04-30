package pe.todotic.bookstoreapi_s2.web;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.todotic.bookstoreapi_s2.model.Book;
import pe.todotic.bookstoreapi_s2.model.SalesOrder;
import pe.todotic.bookstoreapi_s2.model.User;
import pe.todotic.bookstoreapi_s2.repository.BookRepository;
import pe.todotic.bookstoreapi_s2.repository.SalesOrderRepository;
import pe.todotic.bookstoreapi_s2.repository.UserRepository;
import pe.todotic.bookstoreapi_s2.web.dto.UserHomeDTO;
import pe.todotic.bookstoreapi_s2.web.dto.UserLoginAndroidStudio;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/last-books")
    public List<Book> list() {
        return bookRepository.findTop6ByOrderByCreatedAtDesc();
    }

    @GetMapping("/books-all")
    public Page<Book> getBooks(@PageableDefault(sort = "title",
            direction = Sort.Direction.ASC, size = 6)
                               Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @GetMapping("/books/{slug}")
    public Book getBook(@PathVariable String slug) {
        return bookRepository.findOneBySlug(slug).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping("/orders/{id}")
    public SalesOrder getSalesOrder(@PathVariable Integer id) {
        return salesOrderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    //registro de usuario


    @PostMapping("/user/create")
    public  User create(@Validated @RequestBody UserHomeDTO userHomeDTO) {
       Optional<User> userOption= userRepository.findOneByEmailIgnoreCase(userHomeDTO.getEmail());
       if (userOption.isPresent()){
           throw  new DataIntegrityViolationException("");
       }
           User user = modelMapper.map(userHomeDTO, User.class);
            user.setFullName(userHomeDTO.getFirstName()+" "+userHomeDTO.getLastName());
           user.setRole(User.Role.USER);
           return userRepository.save(user);


    }

    //login con android studio
    @PostMapping(value = "/loginAndroid")
    public ResponseEntity<User> loginAndroid(@Validated @RequestBody UserLoginAndroidStudio userLoginAndroidStudio){
        Optional<User> credentials=userRepository.findByEmailAndPassword(userLoginAndroidStudio.getEmail(),userLoginAndroidStudio.getPassword());
        if (credentials.isEmpty()){
            throw new RuntimeException("credenciales incorrectas");
        }

        User userCredentials=modelMapper.map(userLoginAndroidStudio,User.class);
        return new ResponseEntity<>(HttpStatus.OK);
    };



    @PostMapping(value = "/login")
    public ResponseEntity<User> loginAndroid(@RequestParam String email, String password){
        Optional<User> credentials=userRepository.findByEmailAndPassword(email,password);
        if (credentials.isEmpty()){
            throw new RuntimeException("credenciales incorrectas");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    };

}
