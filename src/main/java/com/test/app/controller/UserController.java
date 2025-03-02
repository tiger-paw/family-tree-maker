package com.test.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.dto.UserDto;
import com.test.app.form.UserForm;
import com.test.app.model.User;
import com.test.app.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users/index")
	public String userIndex(Model model) {
		List<User> userList = userService.getUserAll();
		model.addAttribute("userList", userList);
		return "users/index";
	}

	// パス「/show」を削除、getEditUserメソッド名を修正する
	@GetMapping("/users/{id}/show")
	public String show(@PathVariable Integer id, Model model) {

		model.addAttribute("user", userService.getEditUser(id));

		return "/users/show";
	}

	@GetMapping("/users/{id}/edit")
	public String edit(@PathVariable Integer id, @ModelAttribute UserForm userForm, Model model) {

		model.addAttribute("userForm", userService.getEditUser(id));

		return "users/edit";
	}

	// Userの更新処理
	@PostMapping("/users/update")
	public String update(@RequestParam Integer id, @ModelAttribute UserForm userForm, RedirectAttributes redirectAttribute) {
//	public String update(@RequestParam Integer id, @ModelAttribute UserForm userForm, Model model) {

		User user = userService.updateUserByForm(userForm);

//		// POSTリクエストのまま
//		model.addAttribute("user", user);
//		Post-> Redirect-> Getパターンにすべき？
//		return "users/update_result";

		// GETリダイレクトさせる
		redirectAttribute.addFlashAttribute("user", user);
		return "redirect:/users/update_result";
	}

	@GetMapping("/users/update_result")
	public String update_result(@ModelAttribute User user, Model model) {

		model.addAttribute("user", user);

		return "users/update_result";
	}

	@GetMapping("/users/{id}/delete")
	public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		userService.delete(id);
		String message = "ユーザーを削除しました (id: " + id + ")";
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/users/index";
	}
}
