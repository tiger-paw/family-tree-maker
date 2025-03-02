package com.test.app.form;

import com.test.app.model.Person;
import com.test.app.model.RelationshipType;
import com.test.app.model.User;

import jakarta.validation.constraints.NotBlank;

public class RelationshipForm {

	@NotBlank
	private Long relationshipId;

	@NotBlank
	private Person person1;
	
	@NotBlank
	private Person person2;

	@NotBlank
	private RelationshipType relationshipType;


	// Getter
	public Long getRelationshipId() {return relationshipId;}
	public Person getPerson1() {return person1;}
	public Person getPerson2() {return person2;}
	public RelationshipType getRelationshipType() {return relationshipType;}
	
	// Setter
	public void setRelationshipId(Long relationshipId) {this.relationshipId = relationshipId;}
	public void setPerson1(Person person1) {this.person1 = person1;}
	public void setPerson2(Person person2) {this.person2 = person2;}
	public void setRelationshipType(RelationshipType relationshipType) {this.relationshipType = relationshipType;}

}
