package pe.todotic.bookstoreapi_s2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.todotic.bookstoreapi_s2.model.Book;

import java.util.List;
import java.util.Optional;

// TODO 8: convertir la interface en un repositorio para la entidad Book
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    List<Book> findTop6ByOrderByCreatedAtDesc();
    List<Book> findLast6ByOrderByCreatedAt();

    Optional<Book> findOneBySlug(String slug);
    @Query("SELECT b FROM Book b WHERE b.slug = ?1")
    Optional<Book> fbs(String s);
}
