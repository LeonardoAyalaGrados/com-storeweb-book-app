package pe.todotic.bookstoreapi_s2.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// TODO 4: usar la anotación de lombok para generar los getters, setters, el método equals y hashCode,
//  toString y el constructor con argumentos requeridos.
// TODO 5: usar la anotación para especificar la clase como una entidad.
@Data
@Entity
public class Book {
    // TODO 6: usar la anotación para especificar el campo como llave primaria.
    // TODO 7: usar la anotación para generar de forma automática el id usando la estrategia IDENTITY
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String slug;
    
    @Column(name = "description")
    private String desc;
    private Float price;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private String coverPath;

    private String filePath;
    @JsonManagedReference
    @JsonSerialize
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<SalesItem> items;

    @PrePersist//se va ejecutar antes de guardar en la bd
    void iniCreatedAt(){
    	createdAt=LocalDateTime.now();
    }
    
    @PreUpdate//se va actualizar justo antes de actualizar en la bD
    void iniUpdatedAt(){
    	updatedAt=LocalDateTime.now();
    }
}
