package pe.todotic.bookstoreapi_s2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.todotic.bookstoreapi_s2.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.fullName LIKE %?1%")
	List<User> findByName(String fullName);

	Optional<User> findOneByEmailIgnoreCase(String email);

	//consulta para el login  android studio
	@Query("SELECT u FROM User u WHERE u.email=?1 AND u.password=?2")
	Optional<User> findByEmailAndPassword(String email, String password);
}
