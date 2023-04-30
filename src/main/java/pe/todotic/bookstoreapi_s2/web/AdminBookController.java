package pe.todotic.bookstoreapi_s2.web;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import pe.todotic.bookstoreapi_s2.web.dto.BookDTO;
import pe.todotic.bookstoreapi_s2.model.Book;
import pe.todotic.bookstoreapi_s2.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class AdminBookController {

	private final ModelMapper modelMapper=new ModelMapper();
	
    // TODO 9: inyectar la dependencia mediante una @
	@Autowired
    private BookRepository bookRepository;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/list")
    List<Book> list() {
        
        return bookRepository.findAll();

    }
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/id/{id}")
    Book get(@PathVariable Integer id) {
			return bookRepository.findById(id)
						.orElseThrow( EntityNotFoundException::new);
		
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Book create(@Validated @RequestBody BookDTO bookDTO) {
    	  //book.setCreatedAt(LocalDateTime.now());
    	/*Book book=new Book();
    		ModelMapper mapper=new ModelMapper();
    		mapper.map(bookDTO, book);*/
    	
    	Book book=modelMapper.map(bookDTO, Book.class);
          return bookRepository.save(book);
         
    }
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/{id}")
    Book update(
            @PathVariable Integer id,
            @Validated @RequestBody BookDTO bookDTO
    ) {
        // TODO 13: buscar un libro por su id, en caso que no lo encuentre lanzar EntityNotFoundException.
        Book book =  bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);;
/*
        	book.setTitle(form.getTitle());
        	book.setSlug(form.getSlug());
        	book.setPrice(form.getPrice());
        	book.setDesc(form.getDesc());*/
        	//book.setUpdatedAt(LocalDateTime.now());

        // TODO 14: actualizar el libro en la BD
        
        modelMapper.map(bookDTO, book);
        	 bookRepository.save(book);

        return book;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        bookRepository.deleteById(book.getId());
    }

    @GetMapping
    Page<Book> paginate(

    		@PageableDefault(sort = "title",direction = Direction.ASC, size = 5) Pageable pageable
    ) {
        return bookRepository.findAll(pageable);
    }

}
