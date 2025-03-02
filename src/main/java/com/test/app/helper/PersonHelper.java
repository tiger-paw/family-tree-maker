package com.test.app.helper;

import java.time.LocalDate;

import com.test.app.form.PersonForm;
import com.test.app.model.Gender;
import com.test.app.model.Person;

public class PersonHelper {
	
	// Personへの変換
	public static Person convertPerson(PersonForm personForm) {
		
		Person person = new Person();
		
		person.setPersonId(personForm.getPersonId());
		person.setFamilyTreeId(personForm.getFamilyTreeId());
		person.setLastName(personForm.getLastName());
		person.setLastNameKana(personForm.getLastNameKana());
		person.setFirstName(personForm.getFirstName());
		person.setFirstNameKana(personForm.getFirstNameKana());
		person.setBirthDate(personForm.getBirthDate());
		person.setGender(personForm.getGender());
		person.setPersonImage(personForm.getPersonImage());
		person.setDescription(personForm.getDescription());
		person.setPhoneNumber(personForm.getPhoneNumber());
		person.setAddress(personForm.getAddress());
		
		return person;
	}

	// PersonFormへの変換
	public static PersonForm convertPersonForm(Person person) {
		PersonForm personForm = new PersonForm();

		personForm.setPersonId(person.getPersonId());
		personForm.setFamilyTreeId(person.getFamilyTreeId());
		personForm.setLastName(person.getLastName());
		personForm.setLastNameKana(person.getLastNameKana());
		personForm.setFirstName(person.getFirstName());
		personForm.setFirstNameKana(person.getFirstNameKana());
		personForm.setBirthDate(person.getBirthDate());
		personForm.setGender(person.getGender());
		personForm.setPersonImage(person.getPersonImage());
		personForm.setDescription(person.getDescription());
		personForm.setPhoneNumber(person.getPhoneNumber());
		personForm.setAddress(person.getAddress());
		
		// 更新画面設定
		personForm.setIsNew(false);

		return personForm;
	}
	
}
