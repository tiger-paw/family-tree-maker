package com.test.app.form;

import com.test.app.model.User;

import jakarta.validation.constraints.NotBlank;


public class FamilyTreeForm {

	@NotBlank
	private Long familyTreeId;

	@NotBlank
	private String title;
	
	private String description;

	@NotBlank
	private Integer id; // userテーブルのid

	private User user;

	// Getter, Setter
	public Long getFamilyTreeId() {return familyTreeId;}
	public String getTitle() {return title;}
	public String getDescription() {return description;}
	public Integer getId() {return id;}
	public void setFamilyTreeId(Long familyTreeId) {this.familyTreeId = familyTreeId;}
	public void setTitle(String title) {this.title = title;}
	public void setDescription(String description) {this.description = description;}
	public void setId(Integer id) {this.id = id;}
}
