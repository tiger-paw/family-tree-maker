package com.test.app.form;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.test.app.model.FamilyTree;
import com.test.app.model.Gender;
import com.test.app.model.Person;
import com.test.app.model.Relationship;
import com.test.app.model.RelationshipType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

public class PersonForm {
	
	@NotBlank	
	private Long personId;
	@NotBlank
	private Long familyTreeId;
	@NotBlank
	private String lastName;
	@NotBlank
	private String lastNameKana;
	@NotBlank
	private String firstName;
	@NotBlank
	private String firstNameKana;

	private LocalDate birthDate;

//	@Enumerated(EnumType.STRING)
	@NotBlank
	private Gender gender;
	
	private String personImage;
	
	private String description;
	
	private String phoneNumber;

	private String address;
	
	private FamilyTree familyTree;
	
	// 登録かと更新かを判別する新規判定
	private boolean isNew;
	
	// SELFが人物データベースに存在するかを判別する判定
	private boolean isSelf;
	
//	// 関係相手の人物ID
//	private Long person2Id;
//	
	// 関係相手の関係種類
	private RelationshipType relationshipType;
	
	// Personが「person1」となる、Relationshipの一覧を取得
	private List<Relationship> relationshipsAsPerson1;
	
	// Personが「person2」となる、Relationshipの一覧を取得
	private List<Relationship> relationshipsAsPerson2;

	
	// 新規作成フォームで"gender.MALE"の記述でエラーのためコンストラクタを追加してみる
	// Constructor
	public PersonForm() {}
	
	public PersonForm(Long personId, Long familyTreeId, String lastName, String lastNameKana, String firstName, String firstNameKana, LocalDate birthDate, Gender gender, String personImage, String description, String phoneNumber, String address, FamilyTree familyTree, Boolean isNew) {
		this.personId = personId;
		this.familyTreeId = familyTreeId;
		this.lastName = lastName;
		this.lastNameKana = lastNameKana;
		this.firstName = firstName;
		this.firstNameKana = firstNameKana;
		this.birthDate = birthDate;
		this.gender = gender;
		this.personImage = personImage;
		this.description = description;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.familyTree = familyTree;
		this.isNew = isNew;
		this.isSelf = isSelf;
		this.relationshipType = relationshipType;
	}
	
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
	public boolean getIsNew() {return isNew;}
	public boolean getIsSelf() {return isSelf;}
	public RelationshipType getRelationType() {return relationshipType;}
	public List<Relationship> getRelationshipsAsPerson1() {return relationshipsAsPerson1;}
	public List<Relationship> getRelationshipsAsPerson2() {return relationshipsAsPerson2;}

	
//	public Long getPerson2Id() {return person2Id;}
//	public RelationshipType relationshipType() {return relationshipType;}
 
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
	public void setIsNew(boolean isNew) {this.isNew = isNew;}
	public void setIsSelf(boolean isSelf) {this.isSelf = isSelf;}
	public void setRelationshipType(RelationshipType relationshipType) {this.relationshipType = relationshipType;}
	public void setRelationshipsAsPerson1(List<Relationship> relationshipsAsPerson1)  {this.relationshipsAsPerson1 = relationshipsAsPerson1;}
	public void setRelationshipsAsPerson2(List<Relationship> relationshipsAsPerson2)  {this.relationshipsAsPerson2 = relationshipsAsPerson2;}
	
}
