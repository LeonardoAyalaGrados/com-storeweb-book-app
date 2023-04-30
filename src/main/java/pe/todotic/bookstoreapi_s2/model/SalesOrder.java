package pe.todotic.bookstoreapi_s2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class SalesOrder {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY )
		private Integer id;
		
		private Float total;
		private LocalDateTime createdAt;

		@Enumerated(EnumType.STRING)
		private PaymentStatus paymentStatus;
		
		@ManyToOne
		@JsonSerialize
		@JoinColumn(name = "customer_id",referencedColumnName = "id")
		@JsonBackReference
		private User customer;

		@PrePersist
		public void createdAt(){
			createdAt=LocalDateTime.now();
		}
		@JsonManagedReference
		@JsonSerialize
		@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
		private List<SalesItem> items;
		public enum PaymentStatus{
			PENDING,
			PAID
		}
}
