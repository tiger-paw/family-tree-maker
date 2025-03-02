package com.test.app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Long personId;
	
	@Column(name = "family_tree_id", nullable = false)
	private Long familyTreeId;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "last_name_kana", nullable = false)
	private String lastNameKana;

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "first_name_kana", nullable = false)
	private String firstNameKana;

	@Column(name = "birth_date", nullable = true)
	private LocalDate birthDate;

	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name = "person_image", nullable = true, columnDefinition = "TEXT")
	private String personImage;
	
	@Column(name = "description", nullable = true, columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;

	@Column(name = "address", nullable = true, columnDefinition = "TEXT")
	private String address;
	
	// FamilyTreesテーブルとのリレーションを追加
	@ManyToOne
	@JoinColumn(name = "family_tree_id", insertable=false, updatable=false)
	private FamilyTree familyTree;

	// Relationshipsテーブルとのリレーション用
	@OneToMany(mappedBy="person1", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Relationship> relationshipsAsPerson1;

	@OneToMany(mappedBy="person2", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Relationship> relationshipsAsPerson2;

	// relationshipType
	private RelationshipType relationshipType;

	// Getter
	public Long getPersonId() {return personId;}
	public Long getFamilyTreeId() {return familyTreeId;}
	public String getLastName() {return lastName;}
	public String getLastNameKana() {return lastNameKana;}
	public String getFirstName() {return firstName;}
	public String getFirstNameKana() {return firstNameKana;}
	public LocalDate getBirthDate() {return birthDate;}
	public Gender getGender() {return gender;}
	public String getPersonImage() {return personImage;}
	public String getDescription() {return description;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getAddress() {return address;}
	public Set<Relationship> getRelationshipsAsPerson1() {return relationshipsAsPerson1;}
	public Set<Relationship> getRelationshipsAsPerson2() {return relationshipsAsPerson2;}
	public RelationshipType getRelationshipType() {return relationshipType;}
 
	// Setter
	public void setPersonId(Long personId) {this.personId = personId;}
	public void setFamilyTreeId(Long familyTreeId) {this.familyTreeId = familyTreeId;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setLastNameKana(String lastNameKana) {this.lastNameKana = lastNameKana;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setFirstNameKana(String firstNameKana) {this.firstNameKana = firstNameKana;}
	public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}
	public void setGender(Gender gender) {this.gender = gender;}
	public void setPersonImage(String personImage) {this.personImage = personImage;}
	public void setDescription(String description) {this.description = description;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setAddress(String address) {this.address = address;}
	public void setRelationshipsAsPerson1(Set<Relationship> relationshipsAsPerson1)  {this.relationshipsAsPerson1 = relationshipsAsPerson1;}
	public void setRelationshipsAsPerson2(Set<Relationship> relationshipsAsPerson2)  {this.relationshipsAsPerson2 = relationshipsAsPerson2;}
	public void setRelationshipType(RelationshipType relationshipType) {this.relationshipType = relationshipType;}
}
