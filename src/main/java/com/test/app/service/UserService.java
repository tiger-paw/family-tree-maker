package com.test.app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.app.dto.UserDto;
import com.test.app.form.UserForm;
import com.test.app.model.User;
import com.test.app.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrincipalService(user);
	}

	public User findByUserName(String userName) {
			return userRepository.findByUserName(userName);
	}

	public void save(UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		userRepository.save(user);
	}

	public List<User> getUserAll() {
		return userRepository.findAll();
	}

	public User updateUserByForm(UserForm userForm) {
		User user = userRepository.findById(userForm.getId());
		user.setUserName(userForm.getUserName());
		user.setEmail(userForm.getEmail());
		userRepository.save(user);
		return user;
	}

	public UserForm getEditUser(Integer id) {
		// データベースからユーザ情報を取得
		Optional<User> userOpt = Optional.of(userRepository.findById(id));
		User entity = userOpt.get();

		// UserFormオブジェクトを指定してプロパティを設定
		UserForm userForm = new UserForm();
		userForm.setId(id);
		userForm.setUserName(entity.getUserName());
		userForm.setEmail(entity.getEmail());

		return userForm;
	}

	@Transactional
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
	
}
