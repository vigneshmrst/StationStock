package com.ramsuvegatech.stationstock.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public class LoginRequest {

	    @NotBlank(message = "Email is required")
	    @Email(message = "Email must be a valid email address")
	    private String email;

		@NotBlank(message = "Password is required")
	    private String password;
		
		 public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}
	}

