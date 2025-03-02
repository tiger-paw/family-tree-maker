package com.test.app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.form.FamilyTreeForm;
import com.test.app.form.PersonForm;
import com.test.app.helper.PersonHelper;
import com.test.app.model.Person;
import com.test.app.model.Relationship;
import com.test.app.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person save(Person person) {
		return personRepository.save(person);
	}

	// 家系図idに紐づく人物の取得
	public List<Person> getPersonForFamilyTree(Long familyTreeId) {
		List<Person> personForFamilyTree = personRepository.findByFamilyTreeId(familyTreeId);
		return personForFamilyTree;
	}

	public void delete(Long personId) {
		personRepository.deleteById(personId);
	}

	public Person getPersonByPersonId(Long personId) {
		Person person = personRepository.findByPersonId(personId);
		return person;
	}

	// 人物の更新
	public Person updatePerson(PersonForm personForm) {
		// エンティティへの変換
		Person person = PersonHelper.convertPerson(personForm);

		// データベースから更新する人物を取得
		Person oldPerson = personRepository.findByPersonId(person.getPersonId());
		
		// personFormにRelationshipsAsPerson1がないとき、personにnewしてセットする
		if (person.getRelationshipsAsPerson1() == null) {
			Set<Relationship> newRelationshipsAsPerson1 = new HashSet<>();
			person.setRelationshipsAsPerson1(newRelationshipsAsPerson1);
		}
		// personFormにRelationshipsAsPerson2がないとき、personにnewしてセットする
		if (person.getRelationshipsAsPerson2() == null) {
			Set<Relationship> newRelationshipsAsPerson2 = new HashSet<>();
			person.setRelationshipsAsPerson2(newRelationshipsAsPerson2);
		}

		// 取得したPersonのrelationshipsAsPerson1,2をフォーム情報で更新
		Set<Relationship> oldRelationship1 = oldPerson.getRelationshipsAsPerson1();
		person.setRelationshipsAsPerson1(oldRelationship1);
		Set<Relationship> oldRelationship2 = oldPerson.getRelationshipsAsPerson1();
		person.setRelationshipsAsPerson1(oldRelationship2); /////////////

//		// 子のクリアと再追加
//		person.getRelationshipsAsPerson1().clear();
//		person.getRelationshipsAsPerson1().addAll(newRelationshipsAsPerson1);

		return personRepository.save(person);
	}

	public List<Person> getPersonWithRelationships(Long person1Id) {
		return personRepository.findPersonWithRelationships(person1Id);
	}

	public List<Person> getPersonWithRelationship(Long personId) {
		List<Person> personList = personRepository.findPersonWithRelationships(personId);
		return personList;
	}

}
