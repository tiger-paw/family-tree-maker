package com.test.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Relationship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "relationship_id")
	private Long relationshipId;
	
	@ManyToOne
	@JoinColumn(name = "person1_id", nullable = false/*, insertable = false, updatable = false*/)
	private Person person1;
	
	@ManyToOne
	@JoinColumn(name = "person2_id", nullable = false/*, insertable = false, updatable = false*/)
	private Person person2;

	@Column(name = "relationship_type")
	@Enumerated(EnumType.STRING)
	public RelationshipType relationshipType;

	
	// Constructor
	public Relationship() {}
	
	public Relationship(Long relationshipId, Person person1, Person person2, RelationshipType relationshipType) {
		this.relationshipId = relationshipId;
		this.person1 = person1;
		this.person2 = person2;
		this.relationshipType = relationshipType;
	}
	
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
