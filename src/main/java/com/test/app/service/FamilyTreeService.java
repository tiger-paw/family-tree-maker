package com.test.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.form.FamilyTreeForm;
import com.test.app.form.PersonForm;
import com.test.app.form.UserForm;
import com.test.app.model.FamilyTree;
import com.test.app.model.User;
import com.test.app.repository.FamilyTreeRepository;
import com.test.app.repository.UserRepository;

@Service
public class FamilyTreeService {

	@Autowired
	private FamilyTreeRepository familyTreeRepository;

	@Autowired
	private UserRepository userRepository;

	// 家系図の新規作成
	public void save(FamilyTreeForm familyTreeForm) {

		FamilyTree familyTree = new FamilyTree();

		String title = familyTreeForm.getTitle();
		String description = familyTreeForm.getDescription();

		// 値をセット
		familyTree.setTitle(title);
		familyTree.setDescription(description);

		// ログインユーザーからユーザーのidを取得
		Integer userId = this.getLoginUserId();

		// userIdをセット
		familyTree.setId(userId);

		familyTreeRepository.save(familyTree);
	}

	// (未使用) Userが結合されたFamilyTreeがListで全件取得されてしまう
	public List<FamilyTree> getFamilyTreeAll() {
		List<FamilyTree> familyTreeList = familyTreeRepository.findAll();
		return familyTreeList;
	}

	// (使用中) ログインしているUserのFamilyTreeを全件取得
	public List<FamilyTree> getAllOnlyLoginUser(Integer userId) {
		List<FamilyTree> familyTreeListOnlyLoginUser = familyTreeRepository.findByUserId(userId);
		return familyTreeListOnlyLoginUser;
	}

	public FamilyTree getFamilyTreeById(Long id) {
		return familyTreeRepository.findByFamilyTreeId(id);
	}

	@Transactional
	public void delete(Long id) {
		familyTreeRepository.deleteById(id);
	}

	// ログインユーザーのidを取得するヘルパーメソッド
	public Integer getLoginUserId() {
		// Spring Security ログインユーザーからuserNameを取得
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		// ユーザー名からユーザー情報を取得
		User user = userRepository.findByUserName(userName);

		// 家系図作成ユーザーのidを取得
		Integer userId = user.getId();

		return userId;
	}

	public FamilyTreeForm getFamilyTreeForm(Long familyTreeId) {
		// データベースからユーザ情報を取得
		Optional<FamilyTree> familyTreeOpt = familyTreeRepository.findById(familyTreeId);
		FamilyTree entity = familyTreeOpt.get();

		// UserFormオブジェクトを指定してプロパティを設定
		FamilyTreeForm familyTreeForm = new FamilyTreeForm();
		familyTreeForm.setFamilyTreeId(familyTreeId); // エラー Long familyTreeId // 上のentityで既に入っていたので重複したか
		familyTreeForm.setTitle(entity.getTitle());
		familyTreeForm.setDescription(entity.getDescription());
		familyTreeForm.setId(entity.getId()); // user.idをセット

		return familyTreeForm;
	}

	public FamilyTree updateFamilyTreeByFamilyTreeId(FamilyTreeForm familyTreeForm) {
		FamilyTree familyTree = familyTreeRepository.findByFamilyTreeId(familyTreeForm.getFamilyTreeId());
		familyTree.setFamilyTreeId(familyTreeForm.getFamilyTreeId());
		familyTree.setTitle(familyTreeForm.getTitle());
		familyTree.setDescription(familyTreeForm.getDescription());
		familyTreeRepository.save(familyTree);
		return familyTree;
	}

}
