package pe.todotic.bookstoreapi_s2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SalesItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Float price;
	
	@Column(name = "downs_ava")
	private Integer downsAva;

	@JsonBackReference
	@JsonSerialize
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private SalesOrder order;

	@JsonSerialize
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
}
