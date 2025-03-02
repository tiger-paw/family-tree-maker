package com.test.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.app.model.Person;
import com.test.app.model.Relationship;
import com.test.app.model.RelationshipType;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

	List<Relationship> findByPerson1(Person person1);

	boolean existsByRelationshipType(RelationshipType relationshipType);

//	TCalendar findByCompositePrimaryKey(@Param("yyyymm")int yyyymm,@Param("dd")int dd);
//	@Query("select r from Relationship r where r.person1Id = :person1Id and r.person2Id = :person2Id")
    @Query(value ="select * from Relationship where person1_id = :person1Id and person2_id = :person2Id", nativeQuery = true)
	Relationship findByCompositePrimaryKey(@Param("person1Id") Long person1Id, @Param("person2Id") Long person2Id);

}
