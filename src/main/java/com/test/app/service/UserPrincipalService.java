package com.test.app.service;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.test.app.model.User;

public class UserPrincipalService implements UserDetails {

	private User user;

	public UserPrincipalService(User user) {
		this.user = user;
	}

	// ユーザーに与えられる権限を返す。ここでは全てのユーザーに"USER"という権限を与える。
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 資格情報（ここではパスワード）が有効期限切れでないことを示すために、常にtrueを返す。
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// アカウントが有効であることを示すために、常にtrueを返す。
	@Override
	public boolean isEnabled() {
		return true;
	}
}