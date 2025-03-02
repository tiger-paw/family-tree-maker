package com.test.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.model.Person;
import com.test.app.model.Relationship;
import com.test.app.model.RelationshipType;
import com.test.app.repository.PersonRepository;
import com.test.app.repository.RelationshipRepository;

@Service
public class RelationshipService {

	@Autowired
	private RelationshipRepository relationshipRepository;

	@Autowired
	private PersonRepository personRepository;

	RelationshipType relationshipType = RelationshipType.SELF;

	// Create
	public void save(Long person1Id, Long person2Id, RelationshipType relationshipType) {
		Relationship entity = new Relationship();

		// person1, person2を取得
		Person person1 = personRepository.findByPersonId(person1Id);
		Person person2 = personRepository.findByPersonId(person2Id);

		// relationshipIdを取得する
		Relationship relationship = relationshipRepository.findByCompositePrimaryKey(person1Id, person2Id);

		// entityにセット
		entity.setPerson1(person1);
		entity.setPerson2(person2);
		entity.setRelationshipType(relationshipType);

		// 関係レコードを登録
		relationshipRepository.save(entity);
	}

	public List<Relationship> getRelationshipListByPerson1(Person person1) {
		List<Relationship> relationshipList = relationshipRepository.findByPerson1(person1);
		return relationshipList;
	}

	public boolean checkIsSelf() {
		return relationshipRepository.existsByRelationshipType(relationshipType);
	}

	public void update(Long person1Id, Long person2Id, RelationshipType relationshipType) {
		Relationship entity = new Relationship();

		// person1, person2を取得
		Person person1 = personRepository.findByPersonId(person1Id);
		Person person2 = personRepository.findByPersonId(person2Id);

		// entityにセット
		entity.setPerson1(person1);
		entity.setPerson2(person2);
		entity.setRelationshipType(relationshipType);

		// relationshipIdを取得して、エンティティにセットする
		Relationship relationship = relationshipRepository.findByCompositePrimaryKey(person1Id, person2Id);
		entity.setRelationshipId(relationship.getRelationshipId());

		// 関係レコードを登録
		relationshipRepository.save(entity);
	}

}
