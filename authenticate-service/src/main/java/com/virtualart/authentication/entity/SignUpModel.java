package com.virtualart.authentication.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Pattern(regexp = "[a-zA-Z0-9' ]+",message = "Enter first name in a valid format")
	private String firstName;
	@Pattern(regexp = "[a-zA-Z0-9' ]+",message = "Enter last name in a valid format")
	@NotBlank(message = "Last Name is required")
	private String lastName;
	@Email(regexp = "[a-z0-9_.e+%#]+@[a-z]+.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE,message = "enter a valid email")
	@NotBlank(message = "Email is Required")
	private String email;
	@NotBlank(message = "Password is required")
	private String password;
	private String role;
}
