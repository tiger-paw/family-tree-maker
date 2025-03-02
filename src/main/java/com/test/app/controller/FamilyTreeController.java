package com.test.app.controller;

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
import com.test.app.model.FamilyTree;
import com.test.app.model.Person;
import com.test.app.model.Relationship;
import com.test.app.model.RelationshipType;
import com.test.app.service.FamilyTreeService;
import com.test.app.service.PersonService;
import com.test.app.service.RelationshipService;

import jakarta.transaction.Transactional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class FamilyTreeController {
	
	@Autowired
	private FamilyTreeService familyTreeService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private RelationshipService relationshipService;

	// 家系図の一覧表示（ログインユーザーの作成したもののみ）
	@GetMapping("/family_trees/index")
	public String index(Model model) {
		
		// ログインユーザーのidを取得
		Integer userId = familyTreeService.getLoginUserId();

		// userIdを持つ家系図をDBから取得する
		List<FamilyTree> familyTreeListOnlyLoginUser = familyTreeService.getAllOnlyLoginUser(userId);
		
		model.addAttribute("familyTreeList", familyTreeListOnlyLoginUser);
		model.addAttribute("userId", userId);
		return "family_trees/index";
	}

	// 家系図の詳細表示
	@GetMapping("/family_trees/{familyTreeId}")
	public String show(@PathVariable Long familyTreeId, Model model) {
		
		// ログインユーザーのidを取得
		Integer userId = familyTreeService.getLoginUserId();
		
		// 家系図内のすべての人物を取得
		List<Person> personList = personService.getPersonForFamilyTree(familyTreeId);
		
		// 人物リストから関連を取得する (来週ここから)
//		for (Person person : personList){
//			// 人物リストから関係リストを取得する
//		    Set<Relationship> relationshipList = person.getRelationshipsAsPerson1();
//		}

		
		// person1Idが「1」である関係レコードを取得してperson2のrelationTypeを表示する(RelationType=SELFであるレコードのperson1Idを代入するように修正したい)
		Long person1Id = (long) 1;
		
//		Person person1 = personService.getPersonByPersonId(person1Id);
//		List<Relationship> relationshipList = relationshipService.getRelationshipListByPerson1(person1);
		
		// 家系図内の人物に各々の関係を結合して全て取得
		List<Person> personListWithRelationships = personService.getPersonWithRelationships(person1Id);
						// personListWithRelationships > relationshipsAsPerson1 > person2 > lastName(その他 personId など)
						// personListWithRelationships > relationshipsAsPerson1 > relationshipType(=OLDER_BROTHRE) 
		model.addAttribute("personList", personList); // relationshipAsPerson1 と relationshipAsPerson2 が関連して取得されている
		model.addAttribute("familyTreeId", familyTreeId); // 人物追加時に必要となる家系図idを渡すため、パスパラメータとして使用する
		model.addAttribute("userId", userId);
		model.addAttribute("personListWithRelationships", personListWithRelationships);

		return "family_trees/show";
	}

	// 家系図の作成フォーム
	@GetMapping("/family_trees/create_form")
	public String createForm() {
		return "family_trees/create_form";
	}
	
	// 家系図の新規作成の実行
	@PostMapping("/family_trees/create")
	public String create(/* @AuthenticationPrincipal(expression = "account") Account account, */
			@ModelAttribute FamilyTreeForm familyTreeForm) {
		
		familyTreeService.save(familyTreeForm);
		
		return "redirect:/family_trees/index";
	}
	
	// 家系図情報の編集フォーム
	@GetMapping("/family_trees/{familyTreeId}/edit")
	public String edit(@PathVariable Long familyTreeId, @ModelAttribute FamilyTreeForm familyTreeForm, Model model) {
		FamilyTreeForm existingFamilyTreeForm = familyTreeService.getFamilyTreeForm(familyTreeId);
		model.addAttribute("familyTreeForm", existingFamilyTreeForm);
		return "family_trees/edit";
	}

	// 家系図情報の更新処理
	@PostMapping("/family_trees/update")
	public String update(
			/* @RequestParam Long familyTreeId, */ @ModelAttribute FamilyTreeForm familyTreeForm, RedirectAttributes redirectAttribute) {
		
		FamilyTree familyTree = familyTreeService.updateFamilyTreeByFamilyTreeId(familyTreeForm);
		
		redirectAttribute.addFlashAttribute("familyTree", familyTree);
		
		return "redirect:/family_trees/update_result";
	}

	// 家系図情報の更新結果
	@GetMapping("/family_trees/update_result")
	public String update_result() {
		return "/family_trees/update_result";
	}
	
	// 家系図一覧からの削除確認画面
	@GetMapping("/family_trees/{id}/delete_confirm_from_index")
	public String deleteConfirmFromIndex(@PathVariable Long id, Model model) {
		FamilyTree familyTree = familyTreeService.getFamilyTreeById(id);
		model.addAttribute("familyTree", familyTree);
		return "/family_trees/delete_confirm";
	}
	

	// 家系図一覧からの削除実行
	@GetMapping("/family_trees/{id}/delete")
	public String delete(@PathVariable Long id, /* Model model, */ RedirectAttributes redirectAttributes) {
		familyTreeService.delete(id);
		String message = "家系図を削除しました (id: " + id + ")";
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/family_trees/index";
	}
	
	
}
