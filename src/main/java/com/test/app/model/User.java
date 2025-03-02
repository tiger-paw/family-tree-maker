package com.test.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	 // 最終追加
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<FamilyTree> familyTrees;

	// Getter
	public Integer getId() {return id;}
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	public String getEmail() {return email;}
	
	// Setter
	public void setId(Integer id) {this.id = id;}
	public void setUserName(String userName) {this.userName = userName;}
	public void setPassword(String password) {this.password = password;}
	public void setEmail(String email) {this.email = email;}
}