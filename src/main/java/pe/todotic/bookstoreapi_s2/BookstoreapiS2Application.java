package pe.todotic.bookstoreapi_s2;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.todotic.bookstoreapi_s2.model.SalesOrder;
import pe.todotic.bookstoreapi_s2.model.User;

import java.util.List;

@SpringBootApplication
public class BookstoreapiS2Application {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreapiS2Application.class, args);
	}

}
