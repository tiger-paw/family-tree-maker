package com.test.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "family_trees")
public class FamilyTree {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "family_tree_id")
	private Long familyTreeId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = true, columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
	private User user;
	
	@OneToMany(mappedBy="familyTree", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Person> persons;

	// Getter
	public Long getFamilyTreeId() {return familyTreeId;}
	public String getTitle() {return title;}
	public String getDescription() {return description;}
	public Integer getId() {return id;}
	public User getUser() { return user; }
 
	// Setter
	public void setFamilyTreeId(Long familyTreeId) {this.familyTreeId = familyTreeId;}
	public void setTitle(String title) {this.title = title;}
	public void setDescription(String description) {this.description = description;}
	public void setId(Integer id) {this.id = id;}
	public void setUser(User user) { this.user = user; }
}
