package com.test.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.app.model.FamilyTree;
import com.test.app.model.User;

@Repository
public interface FamilyTreeRepository extends JpaRepository<FamilyTree, Long> {

	FamilyTree findByFamilyTreeId(Long id);

	List<FamilyTree> findByUserId(Integer userId);

}
