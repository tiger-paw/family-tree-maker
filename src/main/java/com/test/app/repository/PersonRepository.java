package com.test.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.app.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	// 家系図に紐づく人物の取得
	List<Person> findByFamilyTreeId(Long familyTreeId);

	Person findByPersonId(Long personId);

//	@Query("SELECT p from Persons　p LEFT JOIN relationshipsAsPerson1 p ON p.person.personId  LEFT JOIN relationshipsAsPerson2 WHERE p.person1Id = person1Id")
//	@Query("SELECT p from Persons　p  WHERE p.person1Id = person1Id")
//	Person findByPersonIdWithRelationships(@Param(person1Id));

//	@Query("SELECT p from Person WHERE p.personId = person1Id")
	
//	@Query("SELECT p FROM Person p WHERE p.personId = :person1Id")
//	List<Person> findByPersonIdWithRelationships(@Param("person1Id") Long person1Id);
	
	@Query("SELECT p FROM Person p " +
			"LEFT JOIN FETCH p.relationshipsAsPerson1 " +
			"LEFT JOIN FETCH p.relationshipsAsPerson2 " +
			"WHERE p.id = :personId")
	List<Person> findPersonWithRelationships(@Param("personId") Long person1Id);
}
