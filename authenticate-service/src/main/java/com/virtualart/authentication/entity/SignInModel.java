package com.virtualart.authentication.entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class SignInModel {
	@Email(regexp = "[a-z0-9_.e+%#]+@[a-z]+.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE,message = "enter a valid email")
	private String userName;
	@NotBlank(message = "Password is required")
	private String password;
}
