package com.store.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.store.entities.RoleE;
import com.store.entities.UserE;
import com.store.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserE> isUser = userRepository.findByEmailId(username);
		System.out.println(" Username " + username);
		if(isUser.isPresent()) {
			UserE user = isUser.get();
			return new User(user.getEmailId(), user.getPassword(), getAuthorities(user.getRoles()));
		}else {
			throw new UsernameNotFoundException("Cannot Find user by id " + username);
		}
		
	}
	
	
	private Collection<? extends GrantedAuthority> getAuthorities(
			  Collection<RoleE> roles) {
			    List<GrantedAuthority> authorities
			      = new ArrayList<>();
			    for (RoleE role: roles) {
			        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			        role.getPermissions().stream()
			         .map(p -> new SimpleGrantedAuthority(p.getPermissionName()))
			         .forEach(authorities::add);
			    }
			    
			    return authorities;
			}

}
