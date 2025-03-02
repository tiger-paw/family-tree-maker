package com.test.app.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.form.FamilyTreeForm;
import com.test.app.form.PersonForm;
import com.test.app.form.UserForm;
import com.test.app.helper.PersonHelper;
import com.test.app.model.FamilyTree;
import com.test.app.model.Person;
import com.test.app.model.Relationship;
import com.test.app.model.RelationshipType;
import com.test.app.service.PersonService;
import com.test.app.service.RelationshipService;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private RelationshipService relationshipService;

	// 人物の登録フォーム
	@GetMapping("family_trees/{familyTreeId}/persons/create_form")
	public String createForm(@PathVariable Long familyTreeId, Model model, @ModelAttribute PersonForm personForm) {
		// 家系図に紐づく人物リストを取得する
		List<Person> personList = personService.getPersonForFamilyTree(familyTreeId);
		

		// SELFが存在すれば, isSelf = true
		if (personList.size() == 0) {
			personForm.setIsSelf(false);
		} else {
			personForm.setIsSelf(true);
		}

		// 関係種類をすべて配列で取得する
		RelationshipType[] relationshipTypeArray = RelationshipType.values();

		// 配列[]をListに変換
		List<RelationshipType> relationshipTypeList = Arrays.asList(relationshipTypeArray);

		model.addAttribute("relationshipTypeList", relationshipTypeList);
		model.addAttribute("familyTreeId", familyTreeId);
		model.addAttribute("personList", personList);
		model.addAttribute("relationshipTypeSelf", RelationshipType.SELF);

		// 新規登録画面の設定
		personForm.setIsNew(true);

		return "/persons/person_form";
		
		
		// 本人データが関係種類に登録されているか取得
// 		boolean existsSelf = relationshipService.checkIsSelf();
		

		// 本人が登録されているかを確認する(使用不可)
//		personList.forEach((Person person) -> {
//			// 人物リストから関係リストを取得する
//			Set<Relationship> relationshipList = person.getRelationshipsAsPerson1();
//			// 本人がいない初期設定
//			personForm.setIsSelf(false); // これが最後の人の結果次第になってしまう問題がある
//			relationshipList.forEach((Relationship relationship) -> {
//				// 関係種類の取得
//				RelationshipType relationshipType = relationship.getRelationshipType();
//				// 本人のENUMを取得
//				RelationshipType relationshipTypeSelf = RelationshipType.SELF;
//				// もし本人（SELF）が存在すれば
//				if (relationshipType == relationshipTypeSelf) {
//					personForm.setIsSelf(true);
//					break;
//				} else {
//					personForm.setIsSelf(false);
//				}
//			});
//		});
		
	}

	// 人物登録の実行
	@PostMapping("family_trees/{familyTreeId}/persons/create")
	public String create(@RequestParam Long person2Id, @RequestParam String relationshipTypeString,
			@PathVariable Long familyTreeId, @ModelAttribute PersonForm personForm,
			RedirectAttributes redirectAttributes) {

		// 「PersonForm」から「Personエンティティ」への変換
		Person person = PersonHelper.convertPerson(personForm);

		// 家系図のid(外部キー)をセット
		person.setFamilyTreeId(familyTreeId);

		// Person登録実行
		Person savedPerson1 = personService.save(person);

		// 登録した人物のpersonIdを取得
		Long person1Id = savedPerson1.getPersonId();

//		// 本人データの有無をセットする
//		boolean existsSelf = relationshipService.checkIsSelf();

		// 本人データがDBになければ、本人に対する登録する人物(person2)も本人とする
//		if (!existsSelf) {
//			person2Id = person1Id;
//		}

		// 関係種類があればRelationship登録実行
		if (relationshipTypeString != "") {
			// String -> enum 変換
			RelationshipType relationshipType = RelationshipType.valueOf(relationshipTypeString);
			relationshipService.save(person1Id, person2Id, relationshipType);
		}

		// フラッシュメッセージ
		redirectAttributes.addFlashAttribute("message", "新しい人物が作成されました");

		// リダイレクト先のパス情報としてfamilyTreeIdを格納
		String showPath = "redirect:/family_trees/" + familyTreeId;

		// PRGパターン
		return showPath;
	}

	// 人物詳細の表示
	@GetMapping("family_trees/{familyTreeId}/persons/{personId}")
	public String show(@PathVariable("familyTreeId") Long familyTreeId, @PathVariable("personId") Long personId,
			Model model) {
		// パスパラメータからpersonIdの人物を取得
		Person person = personService.getPersonByPersonId(personId);

		model.addAttribute("familyTreeId", familyTreeId);
		model.addAttribute("person", person);
		return "persons/show";
	}

	// 人物の編集フォーム
	@GetMapping("family_trees/{familyTreeId}/persons/{personId}/edit")
	public String edit(@PathVariable("familyTreeId") Long familyTreeId, @PathVariable("personId") Long personId,
			Model model, RedirectAttributes redirectAttributes) {

		// DBから人物を取得
		Person person = personService.getPersonByPersonId(personId);
		
		// personが保持している関係性を取得する
		Set<Relationship> person1Relationship = person.getRelationshipsAsPerson1();
		
		// entity -> form に変換
		PersonForm personForm = PersonHelper.convertPersonForm(person);
		// 外部キーをセット
		personForm.setFamilyTreeId(familyTreeId);

		// 家系図に紐づく人物リストを取得する
		List<Person> personList = personService.getPersonForFamilyTree(familyTreeId);

		// 関係種類をすべて配列で取得する
		RelationshipType[] relationshipTypeArray = RelationshipType.values();

		// 配列[]をListに変換
		List<RelationshipType> relationshipTypeList = Arrays.asList(relationshipTypeArray);

		// 本人データの有無をセットする => 間違いなので家系図IDに紐づく人物がいない場合という条件に変更したい
//		boolean existsSelf = relationshipService.checkIsSelf();
//		personForm.setIsSelf(existsSelf);
		
		// SELFが存在すれば, isSelf = true
		if (personList.size() == 0) {
			personForm.setIsSelf(false);
		} else {
			personForm.setIsSelf(true);
		}
		
		// この人物が保持する関係性を取得する
		for(Relationship relationship : person1Relationship){
			RelationshipType relationshipType = relationship.relationshipType;
			person.setRelationshipType(relationshipType);
		}
		RelationshipType currentRelationshipType = person.getRelationshipType();
		
		// この人物の関係性を渡す
		model.addAttribute("currentRelationshipType", currentRelationshipType);

		// 人物に関連する関係リストを取得する-----------------------------------
		List<Person> relationshipsAsPerson1 = personService.getPersonWithRelationship(personId);

		// 人物の関係データを渡す
		model.addAttribute("person1Relationship", person1Relationship);
		
		// 人物フォームを渡す
		model.addAttribute("personForm", personForm);

		// 人物リストを渡す
		model.addAttribute("personList", personList);

		// 関係リストを渡す
		model.addAttribute("relationshipTypeList", relationshipTypeList);


		return "persons/person_form";
	}

	// 人物の更新実行
	@PostMapping("persons/update")
	public String update(@RequestParam Long person2Id, @RequestParam String relationshipTypeString,
			@ModelAttribute PersonForm personForm, RedirectAttributes redirectAttribute) {

		// 人物登録処理
		Person person = personService.updatePerson(personForm);

		// 関係登録処理 (RelationshipTypeがあればRelationship登録実行)
		if(relationshipTypeString != "") {
			// String -> enum 変換
			RelationshipType relationshipType = RelationshipType.valueOf(relationshipTypeString);
			Long person1Id = person.getPersonId();
			relationshipService.update(person1Id, person2Id, relationshipType);
		}

		// フラッシュメッセージ
		redirectAttribute.addFlashAttribute("person", person);

		// PRGパターン
		return "redirect:/persons/update_result";
	}

	// 人物の更新結果
	@GetMapping("persons/update_result")
	public String update_result() {
		return "persons/update_result";
	}

	// 家系図詳細（人物一覧）からの削除確認画面
	@GetMapping("family_trees/{familyTreeId}/persons/{personId}/delete_confirm_from_index")
	public String deleteConfirmFromIndex(@PathVariable("familyTreeId") Long familyTreeId,
			@PathVariable("personId") Long personId, Model model) {
		Person person = personService.getPersonByPersonId(personId);
		model.addAttribute("person", person);
		return "/persons/delete_confirm_from_index";
	}

	// 人物詳細からの削除確認画面
	@GetMapping("family_trees/{familyTreeId}/persons/{personId}/delete_confirm_from_show")
	public String deleteConfirmFromShow(@PathVariable("familyTreeId") Long familyTreeId,
			@PathVariable("personId") Long personId, Model model) {

		Person person = personService.getPersonByPersonId(personId);
		model.addAttribute("person", person);

// 前の画面に戻るものを調べる
// パスを渡せば、条件分岐が不要になるかもしれないので調べる
// @PathVariableの使い方

// http://localhost:8080/family_trees/1
// とhttp://localhost:8080/family_trees/1/persons/2
// をmodelで渡すようにして、urlに埋め込めば、フラグの条件分岐を多用せずに済む

		return "/persons/delete_confirm_from_show";
	}

	
	// 人物の削除
	@GetMapping("family_trees/{familyTreeId}/persons/{personId}/delete")
	public String delete(@PathVariable("familyTreeId") Long familyTreeId, @PathVariable("personId") Long personId,
			RedirectAttributes redirectAttributes) {
		personService.delete(personId);
		String message = "人物を削除しました (人物id: " + personId + ")";
		redirectAttributes.addFlashAttribute("message", message);
		// "/family_trees/{familyTreeId}"
		return "redirect:/family_trees/" + familyTreeId;
	}
}
