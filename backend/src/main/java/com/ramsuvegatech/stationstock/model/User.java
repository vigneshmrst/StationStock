package com.ramsuvegatech.stationstock.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_name",nullable = false, length = 100)
	private String firstName;
	@Column(name = "last_name",nullable = false, length = 100)
	private String lastName;
	@Column(name = "email",nullable = false, length = 100)
	private String email;
	@Column(name = "password",nullable = false, length = 100)
	private String password; 
	@Enumerated(EnumType.STRING)
	@Column(name = "role",nullable = false, length = 50)
	@Builder.Default
	private Role role = Role.EMPLOYEE;
	@Column(name = "created_at", nullable = false)
	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
