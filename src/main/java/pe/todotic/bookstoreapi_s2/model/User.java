package pe.todotic.bookstoreapi_s2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "fullname")
	private String fullName;
	
	private String email;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@JsonManagedReference
	@JsonSerialize
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<SalesOrder> orders;
	@PrePersist
    void iniCreatedAt(){
    	createdAt=LocalDateTime.now();
    }

    @PreUpdate
    void iniUpdatedAt(){
    	updatedAt=LocalDateTime.now();
    }

/*
	public void setOrders(List<SalesOrder> orders){
		this.orders=orders;
		for (SalesOrder order: orders){
			order.setCustomer(this);

		}
	}
*/
	public enum Role{
		ADMIN,
		USER
	}

}
